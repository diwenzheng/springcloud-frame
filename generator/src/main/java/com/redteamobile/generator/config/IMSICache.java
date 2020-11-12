package com.redteamobile.generator.config;

import com.redteamobile.generator.dto.IMSI;
import com.redteamobile.generator.utils.PathUtils;
import com.redteamobile.generator.utils.XMLUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName IMSICache
 * @Description TODO
 * @Author 郑迪文
 * @Date 2020/11/10 11:37 上午
 */
@Component
public class IMSICache {

    public static final Map<String, String> IMSI_MAP = new HashMap<String, String>();

    @PostConstruct
    public void init() throws JAXBException {
        IMSI o = (IMSI) XMLUtils.xmlToBean(new File(PathUtils.getPath("/imsi2msisdn.xml")), IMSI.class);
        System.out.println(o);
    }


}
