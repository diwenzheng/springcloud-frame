package com.redteamobile.generator.utils;

/**
 *  @author: ericzhao
 *  @mail: zijian.zhao@redteamobile.com
 *  @date: 2020/6/1 15:43
 *  @description: 
 */ 
public class CamelUnderLineConvertor {

    private CamelUnderLineConvertor(){}

    private static final char UNDERLINE = '_';


    /**
     * @param param
     * @param separator
     * @param charType
     * @return a
     * @description 驼峰转下划线
     * @author erizhao
     * @date 200/6/1 15:45
     */
    public static String camelToUnderline(String param, Character separator, Integer charType) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        separator = separator == null ? UNDERLINE : separator;
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(separator);
            }
            if (charType == 2) {
                //统一都转大写
                sb.append(Character.toUpperCase(c));
            } else {
                //统一都转小写
                sb.append(Character.toLowerCase(c));
            }


        }
        return sb.toString();
    }


    /**
     * @param param
     * @param separator
     * @return a
     * @description 下划线转驼峰
     * @author erizhao
     * @date 200/6/1 15:45
     */
    public static String underlineToCamel(String param, Character separator) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        separator = separator == null ? UNDERLINE : separator;
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        // "_" 后转大写标志,默认字符前面没有"_"
        boolean flag = false;
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == separator) {
                flag = true;
                continue;
            }
            if (flag) {
                //表示当前字符前面是"_" ,当前字符转大写
                sb.append(Character.toUpperCase(param.charAt(i)));
                //重置标识
                flag = false;
            } else {
                sb.append(Character.toLowerCase(param.charAt(i)));
            }
        }
        return sb.toString();
    }


}