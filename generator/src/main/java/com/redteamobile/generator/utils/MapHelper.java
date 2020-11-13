package com.redteamobile.generator.utils;

import org.apache.commons.collections4.MapUtils;

import java.util.HashMap;
import java.util.Map;

/**
 *  @author: ericzhao
 *  @mail: zijian.zhao@redteamobile.com
 *  @date: 2020/6/24 19:38
 *  @description:
 */
public class MapHelper {

    public static Map<String, Object> getMapByPath(Map<String, Object> map, String... keys)
    {
        Map<String, Object> result = map;
        for (String key : keys) {
            result = (Map<String, Object>)result.getOrDefault(key, new HashMap<>());
        }
        return result;
    }

    public static String getMapStringValueByPath(Map<String, Object> map, String... keys)
    {
        if (keys.length == 1)
            return MapUtils.getString(map, keys[0]);
        Map<String, Object> result = map;
        for (int i = 0; i < keys.length - 1; i++) {
            result = (Map<String, Object>)result.getOrDefault(keys[i], new HashMap<>());
        }
        return MapUtils.getString(result, keys[keys.length - 1]);
    }
}
