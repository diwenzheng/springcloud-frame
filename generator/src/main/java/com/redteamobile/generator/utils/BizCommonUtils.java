package com.redteamobile.generator.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Base64;

public class BizCommonUtils {

    /**
     * 从subscriber-id中解析出imsi
     * @param subscriberId
     * @return
     */
    public static String getImsiBySubscriberId(String subscriberId)
    {
        try
        {
            if (StringUtils.isEmpty(subscriberId))
            {
                return null;
            }
            // ;0463333333@coxx.com
            String realSubscriberId = new String(Base64.getDecoder().decode(subscriberId));
            realSubscriberId = realSubscriberId.replaceAll("[^a-zA-Z0-9\\.@]", "");
            String[] imsiAndSuffixArr = realSubscriberId.split("@");//
            if (imsiAndSuffixArr.length != 2)
            {
                return null;
            }
            // TODO 可以考虑 对imsi加个正则校验
            String imsi = imsiAndSuffixArr[0];

            //针对ios手机适配 首先处理 ;逻辑
            //TODO 从@ 往前取15位
            if (imsi != null && imsi.startsWith(";"))
            {
                imsi = imsi.substring(1);

                if (imsi != null && imsi.startsWith("0"))
                {
                    return imsi.substring(1);
                }
            }


            if (imsi != null && imsi.startsWith("0"))
            {
                return imsi.substring(1);
            }
            return imsi;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * 从subscriber-id中解析出imsi
     * @param subscriberId
     * @return
     */
    public static String getImsiByRawSubscriberId(String subscriberId)
    {
        try
        {
            if (StringUtils.isEmpty(subscriberId))
            {
                return null;
            }
            subscriberId = subscriberId.replaceAll("[^a-zA-z0-9\\.@]", "");
            String[] imsiAndSuffixArr = subscriberId.split("@");
            if (imsiAndSuffixArr.length != 2)
            {
                return null;
            }
            // TODO 可以考虑 对imsi加个正则校验
            String imsi = imsiAndSuffixArr[0];

            //针对ios手机适配 首先处理 ;逻辑
            if (imsi != null && imsi.startsWith(";"))
            {
                imsi = imsi.substring(1);

                if (imsi != null && imsi.startsWith("0"))
                {
                    return imsi.substring(1);
                }
            }

            if (imsi != null && imsi.startsWith("0"))
            {
                return imsi.substring(1);
            }

            return imsi;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public static String normalizeMSISDN(String msisdn, String prefix)
    {
        if (StringUtils.isNotEmpty(msisdn) && StringUtils.isNotEmpty(prefix) && msisdn.startsWith(prefix))
        {
            return msisdn.substring(prefix.length());
        }
        return msisdn;
    }

    public static String addMSISDNPrefixIfExist(String msisdn, String prefix)
    {
        if (StringUtils.isNotEmpty(msisdn) && StringUtils.isNotEmpty(prefix) && !msisdn.startsWith(prefix))
        {
            return prefix + msisdn;
        }
        return msisdn;
    }

//    public static void main(String[] args) {
//        System.out.println(BizCommonUtils.getImsiBySubscriberId("MDk4MzQ5MzQzMzMyMzIzMjNAbmFpLmVwYy5tbmMwMDEubWNjNDYwLjNncHBuZXR3b3JrLm9yZw=="));;
//    }


    public static void main(String[] args) {
         System.out.println(getImsiBySubscriberId("AgAAOwEwNDYwMDEyNTYwNjE3MjMyQG5haS5lcGMubW5jMDAxLm1jYzQ2MC4zZ3BwbmV0d29yay5vcmc="));
    }

}
