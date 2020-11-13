package com.redteamobile.generator.utils;

import com.redteamobile.es.foundation.constants.action.SlaveSimActivationStatus;
import com.redteamobile.es.foundation.constants.common.CommonFields;
import com.redteamobile.es.foundation.constants.soap.HWAttachUserStatus;
import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName ActivationStatusConvertor
 * @Description
 * @Author zijian zhao
 * @Date 2019/12/19 17:52
 */
public class ActivationStatusConvertor {

    /**
     * 将联通接口返回的副卡状态转换为苹果接口定义的状态
     * @param attachSimStatus
     * @return
     */
    public static String convert(String attachSimStatus)
    {
        if (StringUtils.equals(attachSimStatus, CommonFields.ATTACH_DEVICE_STATUS_BIND))
        {
            return SlaveSimActivationStatus.ACTIVE.getValue();
        }else if (StringUtils.equals(attachSimStatus, CommonFields.ATTACH_DEVICE_STATUS_UNBIND))
        {
            return SlaveSimActivationStatus.UN_USABLE.getValue();
        }else if (StringUtils.equals(attachSimStatus, CommonFields.ATTACH_DEVICE_STATUS_PROCESSING))
        {
            return SlaveSimActivationStatus.IN_PROGRESS.getValue();
        }else
        {
            return null;
        }
    }

    /**
     * 将联通接口返回的副卡状态转换为苹果接口定义的状态 - 适配华为接口定义
     * @param attachSimStatus
     * @return
     */
    public static String convertForHW(String attachSimStatus)
    {
        if (StringUtils.equals(attachSimStatus, HWAttachUserStatus.ACTIVE))
        {
            return SlaveSimActivationStatus.ACTIVE.getValue();
        }else if (StringUtils.equals(attachSimStatus, HWAttachUserStatus.UNUSABLE))
        {
            return SlaveSimActivationStatus.UN_USABLE.getValue();
        }else if (StringUtils.equals(attachSimStatus, HWAttachUserStatus.PROGRESSING))
        {
            return SlaveSimActivationStatus.IN_PROGRESS.getValue();
        }else if (StringUtils.equals(attachSimStatus, HWAttachUserStatus.INACTIVE))
        {
            return SlaveSimActivationStatus.IN_ACTIVE.getValue();
        }
        else if (StringUtils.equals(attachSimStatus, HWAttachUserStatus.NEW))
        {
            return SlaveSimActivationStatus.NEW.getValue();
        }else
        {
            return null;
        }
    }


    public static String convertToBindStatus(String attachSimStatus)
    {
        if (StringUtils.equals(attachSimStatus, SlaveSimActivationStatus.ACTIVE.getValue()))
        {
            return "0";
        }else if (StringUtils.equals(attachSimStatus, SlaveSimActivationStatus.UN_USABLE.getValue()))
        {
            return "1";
        }else if (StringUtils.equals(attachSimStatus, SlaveSimActivationStatus.IN_PROGRESS.getValue()))
        {
            return "2";
        }else
        {
            return null;
        }
    }
}
