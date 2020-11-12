package com.redteamobile.generator.service;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.redteamobile.generator.config.IMSICache;
import com.redteamobile.generator.contants.GeneratorConstants;
import com.redteamobile.generator.listener.ExcelRawListener;
import com.redteamobile.generator.mapper.IMSIMapper;
import com.redteamobile.generator.po.IMSIInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName GeneratorService
 * @Description TODO
 * @Author 郑迪文
 * @Date 2020/11/10 2:43 下午
 */
@Slf4j
public class GeneratorService {

    @Autowired
    private IMSIMapper imsiMapper;

    @Transactional
    public ResponseEntity<String> uploadXlsFileToData(MultipartFile file)
    {
        try {
            // 解析每行结果在listener中处理
            ExcelRawListener listener = new ExcelRawListener();
            ExcelReader excelReader = new ExcelReader(file.getInputStream(), null, listener);
            //Sheet 构造函数参数 * sheetNo 是第几个sheet 默认从1开始 * headLineMun 表头占几行 * 导入excel的实体地址
            excelReader.read(new Sheet(GeneratorConstants.SHEET_NO, GeneratorConstants.HEAD_LINE_MUN, IMSIInfo.class));
            file.getInputStream().close();
            List<IMSIInfo> list = listener.getList();
            //先删除并插入数据库 后续 到底什么逻辑 再看
            imsiMapper.deleteInfo();
            IMSICache.IMSI_MAP.putAll(list.stream()
                    .collect(Collectors.toMap(IMSIInfo::getImsi, IMSIInfo::getProvince)));
            imsiMapper.insertInfos(list);
            return ResponseEntity.ok("success");
        }catch (IOException e){
            log.error("uploadXlsFileToData ",e);
            throw new RuntimeException(e);
        }
    }

}
