package com.redteamobile.generator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * @ClassName IMSI
 * @Description TODO
 * @Author 郑迪文
 * @Date 2020/11/10 11:09 上午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement(name = "IMSI_PROVINCE")
@XmlType(propOrder = {
        "imsiInfos"
})
public class IMSI {
    private List<IMSIData> imsiInfos;
}
