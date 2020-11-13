package com.redteamobile.generator.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @ClassName GzipUtils
 * @Description
 * @Author zijian zhao
 * @Date 2020/2/20 14:08
 */
@Slf4j
public class GzipUtils {

    private GzipUtils(){}

    public static final int BUFFER = 2 * 1024 * 1024;


    public static byte[] compress(String str, String encoding) {
        if (str == null || str.length() == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes(encoding));
            gzip.close();
        } catch ( Exception e) {
            log.error("compress error");
        }
        return out.toByteArray();
    }

    public static String gzip(String primStr) {
        if (primStr == null || primStr.length() == 0)
        {
            return primStr;
        }
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             GZIPOutputStream gzip = new GZIPOutputStream(out))
        {

            gzip.write(primStr.getBytes());
            return Base64.getEncoder().encodeToString(out.toByteArray());
        }
        catch (IOException e)
        {
            log.error("gzip error");
            return null;
        }
    }

    /**
     *
     * <p>Description:使用gzip进行解压缩</p >
     * @param compressed
     * @return
     */
    public static String gunzip(byte[] compressed){
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             ByteArrayInputStream in = new ByteArrayInputStream(compressed);
             GZIPInputStream ginzip=new GZIPInputStream(in))
        {
            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = ginzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            return out.toString();
        }
        catch (IOException e)
        {
            log.error("gzip error ");
            return new String(compressed, StandardCharsets.UTF_8);
        }
    }
}