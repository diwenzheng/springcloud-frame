package com.redteamobile.generator.utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Date;

/**
 * @ClassName CertHelper
 * @Description
 * @Author zijian zhao
 * @Date 2020/1/8 15:21
 */
public class CertHelper {

    private static Logger logger = LoggerFactory.getLogger("CMS SIGN");

    private static String ksType = "PKCS12";

    public CertHelper() {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }

    static{
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }

    /**
     * 获取证书到期时间
     * @param certPath 证书路径
     * @param certPwd 证书密码
     * @return
     */
    public static Date getCertExpireDate(String certPath, String certPwd) {
        String priKeyName = null;
        char passphrase[] = certPwd.toCharArray();

        try {
            Provider provider = new BouncyCastleProvider();
            // 添加BouncyCastle作为安全提供
            Security.addProvider(provider);

            // 加载证书
            KeyStore ks = KeyStore.getInstance(ksType);
            ks.load(new FileInputStream(certPath), passphrase);

            if (ks.aliases().hasMoreElements()) {
                priKeyName = ks.aliases().nextElement();
            }

            Certificate cert = ks.getCertificate(priKeyName);

            // 获取私钥
            PrivateKey prikey = (PrivateKey) ks.getKey(priKeyName, passphrase);

            X509Certificate cerx509 = (X509Certificate) cert;

            return cerx509.getNotAfter();

        } catch (Exception e) {
            logger.error("parse cert failed", e);
            return null;
        }
    }
}
