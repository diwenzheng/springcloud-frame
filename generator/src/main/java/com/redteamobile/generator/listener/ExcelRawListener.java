package com.redteamobile.generator.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.redteamobile.generator.po.IMSIInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 作者：郑迪文
 * @version 创建时间：2020/2/18 0018 18:48
 * @description 描述：excel解析功能
 * @notion 注意：
 */
public class ExcelRawListener extends AnalysisEventListener<IMSIInfo> {

	/** 数据 **/
	private List<IMSIInfo> list = new ArrayList<>();


	@Override
	public void invoke(IMSIInfo imsiInfo, AnalysisContext analysisContext) {
		list.add(imsiInfo);
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext analysisContext) {

	}

	public List<IMSIInfo> getList() {
		return list;
	}

	public void setList(List<IMSIInfo> list) {
		this.list = list;
	}
}