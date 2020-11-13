package com.redteamobile.generator.utils;

import com.redteamobile.generator.config.BizConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @ClassName AESEncryptHelper
 * @Description
 * @Author zijian zhao
 * @Date 2019/12/26 10:11
 */
public class AESEncryptHelper {

    public static final String AES_ENCRYPT_ALGORITHM = "AES/CBC/PKCS5PADDING";

    public static final String ENCRYPT_METHOD = "AES";

    private AESEncryptHelper(){}

    private static Logger logger = LoggerFactory.getLogger(AESEncryptHelper.class);

    public static String encryptWithDefaultPwd(String value)
    {
        return encrypt(value, BizConfig.DEFAULT_POST_DATA_AES_SECRET_KEY);
    }

    public static String decryptWithDefaultPwd(String value)
    {
        return decrypt(value, BizConfig.DEFAULT_POST_DATA_AES_SECRET_KEY);
    }

    /**
     * 使用随机生成的加密向量，单密码方式(sha256(password)取前16字节) 加解密
     */
    public static String decrypt(String content, String password) {
        try
        {
            Cipher cipher = Cipher.getInstance(AES_ENCRYPT_ALGORITHM);
            byte[] encyptBytes = Base64.getDecoder().decode(content);
            byte[] iv = new byte[16];
            byte[] encValue = new byte[encyptBytes.length - 16];
            System.arraycopy(encyptBytes, 0, iv, 0, 16);
            System.arraycopy(encyptBytes, 16, encValue, 0, encValue.length);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            byte[] encodeFormat = SHA256(password.getBytes(StandardCharsets.UTF_8));
            byte[] key = new byte[16];
            System.arraycopy(encodeFormat, 0, key, 0, 16);
            SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);
            byte[] plainBytes = cipher.doFinal(encValue);
            return new String(plainBytes, StandardCharsets.UTF_8);
        }
        catch (Exception e)
        {
            return null;
        }
    }
    //
    public static String encrypt(String content, String password) {
        try
        {
            Cipher cipher = Cipher.getInstance(AES_ENCRYPT_ALGORITHM);
            byte[] iv = generateRandomSecureIV(cipher);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

            byte[] encodeFormat = SHA256(password.getBytes(StandardCharsets.UTF_8));
            byte[] key = new byte[16];
            System.arraycopy(encodeFormat, 0, key, 0, 16);
            SecretKeySpec keySpec = new SecretKeySpec(key, ENCRYPT_METHOD);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec);
            byte[] encrypted = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));

            byte[] result = new byte[16 + encrypted.length];
            System.arraycopy(iv, 0, result, 0, 16);
            System.arraycopy(encrypted, 0, result, 16, encrypted.length);
            return Base64.getEncoder().encodeToString(result);
        }
        catch(Exception e)
        {
            return null;
        }
    }

    private static byte[] generateRandomSecureIV(Cipher cipher)
    {
        SecureRandom randomSecureRandom = new SecureRandom();
        byte[] iv = new byte[cipher.getBlockSize()];
        randomSecureRandom.nextBytes(iv);
        return iv;
    }

    public static byte[] SHA256(byte[] source){
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(source);
            return messageDigest.digest();
        } catch (Exception e) {
            return new byte[256];
        }
    }


    /* 自行指定加密向量IV
     */
    public static String encrypt(String key, String initVector, String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ENCRYPT_METHOD);
            Cipher cipher = Cipher.getInstance(AES_ENCRYPT_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encrypted);
        }
        catch (Exception ex) {
            logger.error("AES/CBC/128/PKCS5Padding encrypt failed", ex);
        }

        return null;
    }

    /* 自行指定加密向量IV
     */
    public static String decrypt(String key, String initVector, String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance(AES_ENCRYPT_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
            return new String(original, StandardCharsets.UTF_8);
        }
        catch (Exception ex) {
            logger.error("AES/CBC/128/PKCS5Padding decrypt failed", ex);
        }

        return null;
    }

}
