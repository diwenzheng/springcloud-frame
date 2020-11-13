package com.redteamobile.generator.utils;

import org.apache.commons.net.telnet.TelnetClient;

import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TelnetUtil {

    public TelnetClient telnet = new TelnetClient();

    public java.io.InputStream in;//输入流

    public PrintStream out;//输出流

    public static final String DEFAULT_AIX_PROMPT = ">";//截断标识符
    public static final String PAGE_BREAK_SIGN = "---- More ----";//分页符


    /**
     * telnet 端口
     */
    public String port;

    /**
     * 用户名
     */
    public String user;

    /**
     * 密码
     */
    public String password;

    /**
     * IP 地址
     */
    public String ip;

    /**
     * 缺省端口
     */
    public static final int DEFAULT_TELNET_PORT = 23;

    public TelnetUtil(String ip, String user, String password) {
        this.ip = ip;
        this.port = String.valueOf(TelnetUtil.DEFAULT_TELNET_PORT);
        this.user = user;
        this.password = password;
    }

    public TelnetUtil(String ip, String port, String user, String password) {
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.password = password;
    }

    /**
     * 连接设备
     *
     * @return boolean 连接成功返回true，否则返回false
     */
    public boolean connect() {
        boolean isConnect = true;

        try {
            telnet.connect(this.ip, Integer.parseInt(this.port));

            this.in = telnet.getInputStream();
            this.out = new PrintStream(telnet.getOutputStream());

            /** Log the user on* */
            this.readUntil("login: ");
            this.write(user);

            this.readUntil("Password: ");
            this.write(password);

            /** Advance to a prompt */
            this.readUntil(DEFAULT_AIX_PROMPT);

        } catch (Exception e) {
            isConnect = false;
            e.printStackTrace();
            return isConnect;
        }
        return isConnect;
    }

    /**
     * 切换root用户方法
     *
     * @param user
     * @param password
     */
    public void su(String user, String password) {
        try {
            write("su" + " - " + user);
            readUntil("Password:");
            write(password);
            readUntil(DEFAULT_AIX_PROMPT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理返回流
     *
     * @param pattern
     * @return
     */
    public String readUntil(String pattern) {
        try {
            char lastChar = pattern.charAt(pattern.length() - 1);//获取截断标识符
            StringBuffer sb = new StringBuffer();
            char ch = (char) in.read();//获取窗口输入
            while (true) {
//                System.out.print("-"+ch);// ---需要注释掉
                sb.append(ch);
                if (ch == lastChar) { //如果含有截断标识进行返回
                    if (sb.toString().endsWith(pattern)) {
                        sb.toString().replace(PAGE_BREAK_SIGN, "");
                        return sb.toString();
                    }
                }
                if (sb.toString().endsWith(PAGE_BREAK_SIGN)) { //设备开启分页检查
                    write((char) 32 + "");//获取到分页符时，输入空格继续读取数据，(char)32在ASCII中表示空格，
//                    return sb.toString();
                }
                ch = (char) in.read();//获取输入流
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 输入命令窗口，println 方法会在输入命令后自动添加换行符
     *
     * @param value
     */
    public void write(String value) {
        try {
            this.out.println(value);
            this.out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送命令
     *
     * @param command
     * @return
     */
    public String sendCommand(String command) {
        try {
            this.write(command);
            return this.readUntil(DEFAULT_AIX_PROMPT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭连接
     */
    public void disconnect() {
        try {
            this.telnet.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送自定义命令，目前只支持：dis int brief，因为调用方法进行过业务处理，不能满足其他命令
     *
     * @param str
     * @return
     */
    public String sendStr(String str) {
        boolean c = this.connect();
        if (!c) {
            throw new NumberFormatException("连接不到目标设备");
        }
        String res = this.sendCommand(str);
        this.disconnect();
        return res;
    }

    /**
     * 发送自定义命令，目前只支持：dis ip routing-table protocol static
     * 特殊处理结束符
     *
     * @param str
     * @return
     */
    public String sendIpRouting(String str) {
        boolean c = this.connect();
        if (!c) {
            throw new NumberFormatException("连接不到目标设备");
        }
        this.write(str);
        String res = this.readUntilRewrite("Summary count : 0");

        this.disconnect();
        return res;
    }

    /**
     * 重写断言方法，支持自定义断言
     *
     * @param pattern
     * @return
     */
    public String readUntilRewrite(String pattern) {
        try {
            StringBuffer sb = new StringBuffer();
            char ch = (char) in.read();//获取窗口输入

            while (true) {
                sb.append(ch);
                if (sb.toString().endsWith(PAGE_BREAK_SIGN)) { //设备开启分页检查
                    write((char) 32 + "");//获取到分页符时，输入空格继续读取数据，(char)32在ASCII中表示空格，
                }
                if (sb.toString().endsWith(pattern)) {
                    sb.toString().replace(PAGE_BREAK_SIGN, "");
                    return sb.toString();
                }
                ch = (char) in.read();//获取输入流
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 结果处理，去掉分页符，制表符，不同系统下的换行符
     *
     * @param str
     * @return
     */
    public String handleStr(String str) {
        str = str.replace(PAGE_BREAK_SIGN, "");
        Pattern p = Pattern.compile("\t|\r|\n");
        Matcher m = p.matcher(str);
        return m.replaceAll("");
    }
}