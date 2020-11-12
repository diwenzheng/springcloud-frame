package com.redteamobile.generator.utils;

import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 作者：郑迪文
 * @version 创建时间：2020/2/7 14:22
 * @description 描述：xml文档解析
 * @notion 注意：
 */
@Slf4j
public final class XMLUtils {

    private XMLUtils()  {
    }

    private static final Map<List<Class<?>>, JAXBContext> JAXB_CONTEXT_MAP = new ConcurrentHashMap<>();




    public static final JAXBContext getJAXBContext(Class<?> ...classes) throws JAXBException {
        List<Class<?>> classList = new ArrayList<>(Arrays.asList(classes));
        if (!JAXB_CONTEXT_MAP.containsKey(classList)) {
            JAXBContext jaxbContext = JAXBContext.newInstance(classes);
            JAXB_CONTEXT_MAP.put(classList, jaxbContext);
            return jaxbContext;
        }
        return JAXB_CONTEXT_MAP.get(classList);
    }

    /**
         * xml文件配置转换为对象
         * @param file  文件
         * @param load    java对象.Class
         * @return    java对象
         * @throws JAXBException
     */
    public static final Object xmlToBean(File file, Class<?> load) throws JAXBException {
        JAXBContext context = getJAXBContext(load);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return unmarshaller.unmarshal(file);
    }

    /**
     * 将对象根据路径转换成xml文件
     *
     * @param obj
     * @param path
     * @return
     */
    public static void convertToXml(Object obj, String path) {
        try {
            // 利用jdk中自带的转换类实现
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            // 格式化xml输出的格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                    Boolean.TRUE);
            // 将对象转换成输出流形式的xml
            // 创建输出流
            FileWriter fw = null;
            try {
                fw = new FileWriter(path);
            } catch (IOException e) {
                log.error("convertToXml error IOException ",e);
            }
            marshaller.marshal(obj, fw);
        } catch (JAXBException e) {
            log.error("convertToXml error JAXBException ",e);
        }
    }


}
