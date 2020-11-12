package com.redteamobile.generator.utils;

import java.io.File;

public class PathUtils {

    private PathUtils(){

    }

    public static String getPath (String path){
        String basePath = PathUtils.class.getResource("/").getPath();
        if (BasePathUtils.isStartupFromJar(PathUtils.class))
        {
            basePath = "/es";
        }
        if (basePath.endsWith(File.separator))
        {
            basePath = basePath.substring(0, basePath.length() - 1);
        }
        return basePath + path;
    }


}