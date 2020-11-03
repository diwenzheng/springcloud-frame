package com.redteamobile.redislock.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AnnotationResolver {

    public String resolver(JoinPoint joinpoint, String toResolverStr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (StringUtils.isEmpty(toResolverStr)) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        String pattern = "#\\{(.+?)\\}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(toResolverStr);
        while (m.find()) {
            String key = m.group().replace("#\\{", "").replace("\\}", "");
            if (key.contains(".")) {
                m.appendReplacement(sb, Objects.requireNonNull(complexResolver(joinpoint, key)));
            } else {
                m.appendReplacement(sb, Objects.requireNonNull(simpleResolver(joinpoint, key)));
            }
        }
        return sb.toString();
    }

    private String simpleResolver(JoinPoint joinPoint, String str) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] names = methodSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < names.length; i++) {
            if (str.equals(names[i])) {
                return args[i].toString();
            }
        }
        return null;
    }

    private String complexResolver(JoinPoint joinPoint, String str) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] names = methodSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();
        String[] strs = str.split("\\.");
        for (int i = 0; i < names.length; i++) {
            if (strs[0].equals(names[i])) {
                Object obj = args[i];
                Method dmethod = obj.getClass().getDeclaredMethod(getMethodName(strs[1]), (Class<?>) null);
                Object value = dmethod.invoke(args[i]);
                return Objects.requireNonNull(getValue(value, 1, strs)).toString();
            }
        }
        return null;
    }

    private Object getValue(Object obj, int index, String[] strs) {
        try {
            if (obj != null && index < strs.length - 1) {
                Method method = obj.getClass().getDeclaredMethod(getMethodName(strs[index + 1]), (Class<?>) null);
                obj = method.invoke(obj);
                getValue(obj, index + 1, strs);
            }
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getMethodName(String name) {
        return "get" + name.replaceFirst(name.substring(0, 1), name.substring(0, 1).toUpperCase());
    }
}