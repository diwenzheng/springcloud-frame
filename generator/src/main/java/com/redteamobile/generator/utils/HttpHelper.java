package com.redteamobile.generator.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Service
public class HttpHelper {

    /**
     * 从httprequest中获取指定field的cookie值
     * @param request HttpServletRequest
     * @param key field key
     * @return
     */
    public String getCookieFieldValue(HttpServletRequest request, String key)
    {
        if (request == null)
        {
            return null;
        }
        Cookie[] cookies =  request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(StringUtils.equals(cookie.getName(), key)){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 提取HTTP HEADER 到map中
     * @param request
     * @return
     */
    public Map<String, String> getHeaderMap(HttpServletRequest request)
    {
        if (request == null)
        {
            return null;
        }
        Map<String, String> headerMap = new HashMap<>();
        Enumeration<String> e = request.getHeaderNames();
        while(e.hasMoreElements()){
            String headerName = e.nextElement();
            Enumeration<String> headerValues = request.getHeaders(headerName);
            while(headerValues.hasMoreElements()){
//                System.out.println(headerName+":"+headerValues.nextElement());
                headerMap.put(headerName, headerValues.nextElement());
            }
        }
        return headerMap;
    }
}
