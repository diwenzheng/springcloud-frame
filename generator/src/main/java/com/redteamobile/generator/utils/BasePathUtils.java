package com.redteamobile.generator.utils;

import java.io.File;

public class BasePathUtils {

    public static final String DEFAULT_DATA_DIR = "/es";

    public static final String DEFAULT_CERT_DIR = "/es/certificates/";

    public static<T> boolean isStartupFromJar(Class<T> clazz) {
        File file = new File(clazz.getProtectionDomain().getCodeSource().getLocation().getPath());
        return file.isFile();
    }
}