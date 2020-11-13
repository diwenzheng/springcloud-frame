package com.redteamobile.generator;

import ch.ethz.ssh2.Connection;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.redteamobile.generator.utils.SshUtil;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

/**
 * @ClassName TestSSH
 * @Description TODO
 * @Author 郑迪文
 * @Date 2020/11/12 2:08 下午
 */
public class TestSSH {

    /**
     * cmd执行多个操作使用 && 区分
     */
    @Test
    public void testSSH(){
        String ip = "132.96.79.136";   // 此处根据实际情况，换成自己需要访问的主机IP
        String userName = "ubuntu";
        String password = "passwd@ubts";
        Connection conn =  SshUtil.login(ip, userName, password);

        String cmd = "ls && pwd";
        String result = SshUtil.execute(conn, cmd);
        System.out.println(result);
    }

    /**
     * 测试本地shell脚本在别处执行
     */
    @Test
    public void testSSHLocalShell() throws IOException {
        String ip = "132.96.79.136";   // 此处根据实际情况，换成自己需要访问的主机IP
        String userName = "ubuntu";
        String password = "passwd@ubts";
        Connection conn =  SshUtil.login(ip, userName, password);
        String s = FileUtils.readFileToString(new File("/Users/zhengdiwen/workspace/projects/ES/geo/src/test/resources/ss.sh"));
        String result = SshUtil.execute(conn, s);
        System.out.println(result);
    }

    /**
     * 按顺序连接ssh服务器，并执行远程命令
     * @param sshHosts ssh服务器列表，最后一个为最终的目标服务器
     * @param command 需要执行的命令
     * @param callback 输出回调，格式: (stdout, stderr) -> {}
     */
    public static void execute(List<SshHost> sshHosts, String command, BiConsumer<InputStream, InputStream> callback) {
        try {
            JSch jsch = new JSch();
//            jsch.addIdentity("~/.ssh/id_rsa");
            Session[] sessions = new Session[sshHosts.size()];
            Session session = null;

            for (int i = 0; i < sshHosts.size(); i++) {
                SshHost sshHost = sshHosts.get(i);
                if (i == 0) {
                    sessions[i] = session = jsch.getSession(sshHost.user, sshHost.host, sshHost.port);
                } else {
                    int assignedPort = session.setPortForwardingL(0, sshHost.host, sshHost.port);
                    sessions[i] = session = jsch.getSession(sshHost.user, "127.0.0.1", assignedPort);
                }
                session.setConfig("StrictHostKeyChecking", "no");
                session.setPassword("passwd@ubts");
                session.connect();
            }

            ChannelExec channel = (ChannelExec)session.openChannel("exec");
            channel.setCommand(command);
            channel.connect();
            callback.accept(channel.getInputStream(), channel.getErrStream());

            for (int i = sessions.length - 1; i >= 0; i--) {
                sessions[i].disconnect();
            }
        } catch (JSchException | IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void execute() {
        List<SshHost> sshHosts = new ArrayList<>();
        sshHosts.add(new SshHost("ubuntu", "132.96.79.198", 22));
        sshHosts.add(new SshHost("ubuntu", "192.168.100.200", 22));
        sshHosts.add(new SshHost("ubuntu", "132.97.66.136", 22));
        execute(sshHosts, "./ ss.sh", (stdout, stderr) -> {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stdout));
            String line;
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static class SshHost {
        String user;
        String host;
        Integer port;

        public SshHost(String user, String host) {
            this(user, host, 22);
        }

        public SshHost(String user, String host, Integer port) {
            this.user = user;
            this.host = host;
            this.port = Optional.ofNullable(port).orElse(22);
        }
    }
}
