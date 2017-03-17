package com.herongtech.rc.draft.msgProcessor.histInfoProcessorFor034;

import org.xmlbean.ecds034.CommercialDraftBusiness1;

import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
/**
 * 历史信息处理器
 *
 */
public abstract class HistoryInfoProcessor{
	

	/**
	 * 解析历史信息
	 * 因为每一次背书记录都可以分成交易的申请方、签收方已经交易数据，所以返回DraftInfoVo对象
	 * @param comrclDrftBiz
	 * @param billInfo ：传入票据中心对象是为了完善票面信息 比如承兑人评级，组织机构代码什么的
	 * @return
	 */
	public abstract DraftInfoVo parseHistoryInfo(CommercialDraftBusiness1 comrclDrftBiz,  RgctBillInfo billInfo);

	
}
