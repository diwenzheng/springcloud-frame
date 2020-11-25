package com.redteamobile.es.config;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author zhengdiwen
 */
@Component
@Lazy(false)
public class DataSourceContextHolder {

    /** 采用ThreadLocal 保存本地多数据源*/
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    /** 设置数据源类型*/
    public static void setDbType(String dbType) {
        CONTEXT_HOLDER.set(dbType);
    }

    public static String getDbType() {
        return CONTEXT_HOLDER.get();
    }

    public static void clearDbType() {
        CONTEXT_HOLDER.remove();
    }

}