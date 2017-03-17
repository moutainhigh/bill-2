/********************************************
 * 文件名称: IRcEndorseService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: zcz
 * 开发时间: 2016-8-10 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.rc.service.rcservice;
import java.util.List;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctEndoHist;

/**
 * 登记中心服务：票据背书流程
 * 
 * @author 
 */
public interface IRcEndorseService  extends IRcBaseService {
	/**
	 * 背书人查询待背书票据
	 * 
	 * @param sb SearchBean
	 * @param pg Page
	 * @return 登记中心票据列表 String&lt;RgctBill&gt;或String&lt;RgctBillInfo&gt;
	 * @throws BizAppException
	 */
	public String queryEndorseFrom(RcBaseSearchBean sb) throws BizAppException;
	
	/**
	 * 背书转让申请
	 * @param bill 票据信息
	 * @throws BizAppException
	 */
	public void regEndorse(RgctBill bill) throws BizAppException;
	
	/**
	 * 背书人背书撤销
	 * 
	 * @param bill 登记中心信息bean
	 * @throws BizAppException
	 */
	public void cancelEndorse(RgctBill bill) throws BizAppException;
	
	/**
	 * 被背书人查询待背书票据
	 * 
	 * @param sb SearchBean
	 * @param pg Page
	 * @return 登记中心票据列表 String&lt;RgctBill&gt;或String&lt;RgctBillInfo&gt;
	 * @throws BizAppException
	 */
	public String queryEndorseTo(RcBaseSearchBean sb) throws BizAppException;
	
	/**
	 * 被背书人签收
	 * @param bill 票据信息
	 * @throws BizAppException
	 */
	public void signEndorse(RgctBill bill) throws BizAppException;
	
	/**
	 * 被背书人拒绝签收
	 * @param bill 票据信息
	 * @throws BizAppException
	 */
	public void rejectEndorse(RgctBill bill) throws BizAppException;
	
	/**
	 * 查询背书历史信息
	 * @param rgctId 登记中心Id
	 * @throws BizAppException
	 */
	public List<RgctEndoHist> getRgctEndoList(String rgctId) throws BizAppException;
}