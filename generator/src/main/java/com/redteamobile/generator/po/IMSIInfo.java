package com.redteamobile.generator.po;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 作者：郑迪文
 * @version 创建时间：2020/2/18 0018 18:49
 * @description 描述：
 * @notion 注意：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IMSIInfo extends BaseRowModel implements Serializable {

    @ExcelProperty(index = 0)
    //imis
    private String imsi;
    @ExcelProperty(index = 1)
    //省份
    private String province;

}
