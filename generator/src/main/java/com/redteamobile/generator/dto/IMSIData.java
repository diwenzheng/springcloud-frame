package com.redteamobile.generator.dto;

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
public class IMSIData  implements Serializable {

    private String imsi;
    //省份
    private String province;

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
