package com.redteamobile.generator.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName CommonStrUtils
 * @Description
 * @Author zijian zhao
 * @Date 2020/3/16 18:10
 */
public class CommonStrUtils {

    public static String choose(String str1, String str2)
    {
        if (StringUtils.isEmpty(str1))
        {
            return StringUtils.isEmpty(str2) ? null : str2;
        }
        return str1;
    }

}


