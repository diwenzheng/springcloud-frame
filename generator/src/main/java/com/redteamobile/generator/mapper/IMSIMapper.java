package com.redteamobile.generator.mapper;

import com.redteamobile.generator.po.IMSIInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 作者：郑迪文
 * @version 创建时间：2020/2/18 0018 18:48
 * @description 描述：
 * @notion 注意：
 */
@Repository
@Mapper
public interface IMSIMapper {
     List<IMSIInfo> queryAllInfo();
     Integer insertInfos(@Param("imsiInfos") List<IMSIInfo> imsiInfos);
     Integer deleteInfo();
}
