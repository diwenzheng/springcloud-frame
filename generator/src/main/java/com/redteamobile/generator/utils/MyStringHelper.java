package com.redteamobile.generator.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName StringHelper
 * @Description
 * @Author zijian zhao
 * @Date 2019/12/19 18:23
 */
public class MyStringHelper {

    public static String defaultToEmptyString(String value){
        return value == null ? StringUtils.EMPTY : value;
    }
}
