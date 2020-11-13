package com.redteamobile.generator.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class TokenUtils {

    private static final SecureRandom random = new SecureRandom();

    private static final String PERFIX_TOKEN = "ES_UE_TOKEN";

    private static final String PERFIX_IDENTITY = "ES_WS_IDENTITY";

    private static final String PERFIX_OPPO_TOKEN = "OPPO_ES_TOKEN";


    /**
     * 生成一个新token 生成方式为： imsi 随机数 当前时间毫秒数 三个field取bytes拼接后进行sha256，将结果转为16进制标识的字符串
     * @return
     */
    public static String generateOppo(String imsi) {
        return generate(PERFIX_OPPO_TOKEN, imsi);
    }



    /**
     * 生成一个新token 生成方式为： imsi 随机数 当前时间毫秒数 三个field取bytes拼接后进行sha256，将结果转为16进制标识的字符串
     * @return
     */
    public static String generateToken(String imsi) {

        return generate(PERFIX_TOKEN, imsi);

    }

    /**
     * 生成一个新identity 生成方式为： imsi 随机数 当前时间毫秒数 三个field取bytes拼接后进行sha256，将结果转为16进制标识的字符串
     * @return
     */
    public static String generateIdentity(String imsi) {

        return generate(PERFIX_IDENTITY, imsi);

    }

    /**
     * 生成一个新token 生成方式为： imsi 随机数 当前时间毫秒数 三个field取bytes拼接后进行sha256，将结果转为16进制标识的字符串
     * @return
     */
    private static String generate(String prefix, String imsi) {

        byte[] prefixBytes = prefix.getBytes(StandardCharsets.US_ASCII);
        byte[] imsiBytes = imsi.getBytes(StandardCharsets.US_ASCII);
        byte[] randomBytes = new byte[32];
        random.nextBytes(randomBytes);
        byte[] systemMillis = String.valueOf(System.currentTimeMillis()).getBytes(StandardCharsets.US_ASCII);
        byte[] input = new byte[prefixBytes.length + randomBytes.length + imsiBytes.length + systemMillis.length];
        System.arraycopy(prefixBytes, 0, input, 0, prefixBytes.length);
        System.arraycopy(imsiBytes, 0, input, prefixBytes.length, imsiBytes.length);
        System.arraycopy(randomBytes, 0, input, imsiBytes.length + prefixBytes.length, randomBytes.length);
        System.arraycopy(systemMillis, 0, input, imsiBytes.length + prefixBytes.length + randomBytes.length, systemMillis.length);
        byte[] result = DigestUtils.getSha256Digest().digest(input);
        return Base64.getEncoder().encodeToString(result);
    }
}
