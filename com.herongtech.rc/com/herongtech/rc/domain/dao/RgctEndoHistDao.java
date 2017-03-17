/********************************************
* 文件名称: RgctEndoHistDao.java
* 系统名称: 合融基础技术平台V2.0
* 模块名称:
* 软件版权: 北京合融科技有限公司
* 功能说明: 
* 系统版本: 2.0.0.1
* 开发人员: 技术平台组
* 开发时间:  
* 审核人员:
* 相关文档:
* 修改记录: 修改日期    修改人员    修改说明
*********************************************/
package com.herongtech.rc.domain.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.domain.bean.RgctEndoHist;
public class RgctEndoHistDao{

public int addRgctEndoHist(RgctEndoHist obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into trgct_endo_hist(id,rgct_id,endo_type,from_name,from_acct_no,from_bank_no,from_org_code,to_name,to_acct_no,to_bank_no,to_org_code,endorse_dt,endorse_time,sign_date,sign_time,rpd_open_date,rpd_due_date,forbid_flag,is_reject_prsnt,reject_prsnt_rsn,recourse_type,guarntee_adr,remark,create_time,create_dt)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getId(),obj.getRgctId(),obj.getEndoType(),obj.getFromName(),obj.getFromAcctNo(),
	obj.getFromBankNo(),obj.getFromOrgCode(),obj.getToName(),obj.getToAcctNo(),
	obj.getToBankNo(),obj.getToOrgCode(),obj.getEndorseDt(),obj.getEndorseTime(),
	obj.getSignDate(),obj.getSignTime(),obj.getRpdOpenDate(),obj.getRpdDueDate(),
	obj.getForbidFlag(),obj.getIsRejectPrsnt(),obj.getRejectPrsntRsn(),obj.getRecourseType(),
	obj.getGuarnteeAdr(),obj.getRemark(),obj.getCreateTime(),obj.getCreateDt());
}



public int deleteRgctEndoHist(String rgct_id) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from trgct_endo_hist where rgct_id=?",rgct_id);
}


public int modifyRgctEndoHist(RgctEndoHist obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update trgct_endo_hist set rgct_id=?,endo_type=?,from_name=?,from_acct_no=?,from_bank_no=?,from_org_code=?,to_name=?,to_acct_no=?,to_bank_no=?,to_org_code=?,endorse_dt=?,endorse_time=?,sign_date=?,sign_time=?,rpd_open_date=?,rpd_due_date=?,forbid_flag=?,is_reject_prsnt=?,reject_prsnt_rsn=?,recourse_type=?,guarntee_adr=?,remark=?,create_time=?,create_dt=? where id=?",
	obj.getRgctId(),obj.getEndoType(),obj.getFromName(),obj.getFromAcctNo(),
	obj.getFromBankNo(),obj.getFromOrgCode(),obj.getToName(),obj.getToAcctNo(),
	obj.getToBankNo(),obj.getToOrgCode(),obj.getEndorseDt(),obj.getEndorseTime(),
	obj.getSignDate(),obj.getSignTime(),obj.getRpdOpenDate(),obj.getRpdDueDate(),
	obj.getForbidFlag(),obj.getIsRejectPrsnt(),obj.getRejectPrsntRsn(),obj.getRecourseType(),
	obj.getGuarnteeAdr(),obj.getRemark(),obj.getCreateTime(),obj.getCreateDt(),
	obj.getId());
}

public int modifyRgctEndoHist(RgctEndoHist obj,Long id) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update trgct_endo_hist set rgct_id=?,endo_type=?,from_name=?,from_acct_no=?,from_bank_no=?,from_org_code=?,to_name=?,to_acct_no=?,to_bank_no=?,to_org_code=?,endorse_dt=?,endorse_time=?,sign_date=?,sign_time=?,rpd_open_date=?,rpd_due_date=?,forbid_flag=?,is_reject_prsnt=?,reject_prsnt_rsn=?,recourse_type=?,guarntee_adr=?,remark=?,create_time=?,create_dt=? where id=?",
	obj.getRgctId(),obj.getEndoType(),obj.getFromName(),obj.getFromAcctNo(),
	obj.getFromBankNo(),obj.getFromOrgCode(),obj.getToName(),obj.getToAcctNo(),
	obj.getToBankNo(),obj.getToOrgCode(),obj.getEndorseDt(),obj.getEndorseTime(),
	obj.getSignDate(),obj.getSignTime(),obj.getRpdOpenDate(),obj.getRpdDueDate(),
	obj.getForbidFlag(),obj.getIsRejectPrsnt(),obj.getRejectPrsntRsn(),obj.getRecourseType(),
	obj.getGuarnteeAdr(),obj.getRemark(),obj.getCreateTime(),obj.getCreateDt(),
	id);
}

public RgctEndoHist getRgctEndoHist(Long id) throws SQLException {
	IDB session = DBFactory.getDB();
	RgctEndoHist obj = (RgctEndoHist)session.getObject("select * from trgct_endo_hist where id=?",RgctEndoHist.class,id);
	return obj;
}

public RgctEndoHist getRgctEndoHist(Long rgctId,String endoType) throws SQLException {
	String sql = "select * from trgct_endo_hist where rgct_Id=? and endo_Type=?  order by id desc";
	IDB session = DBFactory.getDB();
	RgctEndoHist endo = session.getObject(sql, RgctEndoHist.class, rgctId,endoType);
	return endo;
}


public void addRgctEndo(RgctBillHist hist) throws SQLException {
	RgctEndoHist endoHist = null;
	/** 如果是解质押,只需要补充解质押日期即可 **/
	if (hist.getCurStatus().startsWith("P_")) {
		endoHist = getRgctEndoHist(Long.valueOf(hist.getRgctId()),"9");
		if(endoHist!=null){
			endoHist.setSignDate(hist.getSignDt());
			this.modifyRgctEndoHist(endoHist);
		}
		return;
	}
	/**
	 * 背书类型 0-背书、1-买断式贴现、 1_1-回购式贴现、 2-回购式贴现赎回、3-买断式转贴现、3_1-回购式转贴现、
	 * 4-回购式转贴现赎回、5-买断再贴现、5_1-回购式再贴现、6-回购式再贴现赎回、7-央行卖票、8-保证、9-质押、10-提示付款、11-追索
	 */
	
	endoHist = new RgctEndoHist();
	try {
		String id = ServiceFactory.getSequenceService().getBillEndoHist();
		endoHist.setId(id);
	} catch (BizAppException e1) {
		e1.printStackTrace();
	}
	/** 质押的时候没有签收日期,在解质押的时候补充,因为质押与解质押使用同一条背书记录 */
	if (hist.getCurStatus().startsWith("O_")) {
		endoHist.setEndorseDt(hist.getSignDt());
		endoHist.setEndoType(RcConstants.ENDO_Nine);
	}/** 电子票据池增加背书历史 */
	else if (hist.getCurStatus().startsWith("BPC_")) {
		endoHist.setEndorseDt(hist.getSignDt());
		endoHist.setEndoType(RcConstants.ENDO_Nine);
	}/** 提示付款需要记录提示付款日期 */
	else if (hist.getCurStatus().startsWith("R_") || hist.getCurStatus().startsWith("S_")) {
		endoHist.setEndorseDt(hist.getEndorseDt());
		endoHist.setEndoType(RcConstants.ENDO_Ten);
	}/** 追索清偿背书有追索日期和清偿日期,追索日期是发起追索通知的日期,清偿日期是清偿签收的日期 **/
	else if (hist.getCurStatus().startsWith("U_") || hist.getCurStatus().startsWith("V_")) {
		// T:拒付追索， W:非拒付追索 TODO:如何取到追索日期？？？--GAOHENG
		// RecourseFactory.getRecorseService().q
		endoHist.setEndorseDt(hist.getEndorseDt());
		endoHist.setSignDate(hist.getSignDt());
		endoHist.setEndoType(RcConstants.ENDO_Eleven);
	}/** 0-背书 **/
	else if (hist.getCurStatus().startsWith("E_")) {
		endoHist.setEndorseDt(hist.getSignDt());
		endoHist.setEndoType(RcConstants.ENDO_ZERO);
	}/**1-买断式贴现 **/
	else if (hist.getCurStatus().startsWith("F_")) {
		endoHist.setEndorseDt(hist.getSignDt());
		endoHist.setEndoType(RcConstants.ENDO_One);
	}/** 1_1-回购式贴现 **/
	else if (hist.getCurStatus().startsWith("G_")) {
		endoHist.setEndorseDt(hist.getSignDt());
		endoHist.setEndoType(RcConstants.ENDO_One_1);
	}/** 2-回购式贴现赎回 **/
	else if (hist.getCurStatus().startsWith("H_")) {
		endoHist.setEndorseDt(hist.getSignDt());
		endoHist.setEndoType(RcConstants.ENDO_Two);
	}/** 3-买断式转贴现 **/
	else if (hist.getCurStatus().startsWith("I_")) {
		endoHist.setEndorseDt(hist.getSignDt());
		endoHist.setEndoType(RcConstants.ENDO_Three);
	}/** 3_1-回购式转贴现 **/
	else if (hist.getCurStatus().startsWith("J_")) {
		endoHist.setEndorseDt(hist.getSignDt());
		endoHist.setEndoType(RcConstants.ENDO_Three_1);
	}/** 4-回购式转贴现赎回 **/
	else if (hist.getCurStatus().startsWith("K_")) {
		endoHist.setEndorseDt(hist.getSignDt());
		endoHist.setEndoType(RcConstants.ENDO_Four);
	}/** 5-买断再贴现 **/
	else if (hist.getCurStatus().startsWith("L_")) {
		endoHist.setSignDate(hist.getSignDt());
		endoHist.setEndoType(RcConstants.ENDO_Five);
	}/** 5_1-回购式再贴现 **/
	else if (hist.getCurStatus().startsWith("M_")) {
		endoHist.setEndorseDt(hist.getSignDt());
		endoHist.setEndoType(RcConstants.ENDO_Five_1);
	}/** 6-回购式再贴现赎回 **/
	else if (hist.getCurStatus().startsWith("N_")) {
		endoHist.setEndorseDt(hist.getSignDt());
		endoHist.setEndoType(RcConstants.ENDO_Six);
	}/** 7-央行卖票 **/
	else if (hist.getCurStatus().startsWith("X_")) {
		endoHist.setEndorseDt(hist.getSignDt());
		endoHist.setEndoType(RcConstants.ENDO_Seven);
	}/** 8-保证 **/
	else if (hist.getCurStatus().startsWith("Q_")) {
		endoHist.setEndorseDt(hist.getSignDt());
		endoHist.setEndoType(RcConstants.ENDO_Eight);
		//增加保证人地址信息
//		RgctBillAssu assu = this.getAssuInfoByRgctIdOrderByDesc(hist.getRgctId());
//		if(assu!=null)endoHist.setGuarnteeAdr(assu.getGuarntrAddr());
	}
	/** 其他的都是签收日期 **/
	else
	{
		endoHist.setSignDate(hist.getSignDt());
	}
	endoHist.setRgctId(hist.getRgctId());
	endoHist.setFromName(hist.getFromName());
	endoHist.setFromBankNo(hist.getFromBankNo());
	endoHist.setFromAcctNo(hist.getFromAcctNo());
	endoHist.setFromOrgCode(hist.getFromOrgcode());
	
	endoHist.setToName(hist.getToName());
	endoHist.setToAcctNo(hist.getToAcctNo());
	endoHist.setToBankNo(hist.getToBankNo());
	endoHist.setToOrgCode(hist.getToOrgcode());
	try {
		endoHist.setCreateTime(DateTimeUtil.getWorkdayString());
	} catch (BizAppException e) {
//		endoHist.setCreateTime(Calendar.getInstance().getTime().toString());
		e.printStackTrace();
	}
	//endoHist.setDelFlag(EndoConst.DELETE_NO);
	//endoHist.setHistId(hist.getId());

	endoHist.setForbidFlag(hist.getForbidFlag());
	/** 回购的交易需要增加回购开放日和回购截止日 回购式贴现,回购式转贴现,回购式再贴现 */
	if (hist.getCurStatus().startsWith("G_") || hist.getCurStatus().startsWith("J_") || hist.getCurStatus().startsWith("M_")) {
		endoHist.setRpdOpenDate(hist.getBackOpenDt());
		endoHist.setRpdDueDate(hist.getBackEndDt());
	}

	/** 提示付款签收的特殊处理 **/
	if ("R_22".equals(hist.getCurStatus()) || "R_23".equals(hist.getCurStatus()) || "R_24".equals(hist.getCurStatus())
			||"S_22".equals(hist.getCurStatus()) || "S_23".equals(hist.getCurStatus()) || "S_24".equals(hist.getCurStatus())) {
		endoHist.setIsRejectPrsnt(RcConstants.YES);
		endoHist.setRejectPrsntRsn(hist.getRejectReason() != null ? hist.getRejectReason() : DraftConstants.REFUSE_REASON_MAP.get(hist
				.getRejectCode()));
		endoHist.setSignDate(hist.getSignDt());
	} else if("R_16".equals(hist.getCurStatus()) || "S_16".equals(hist.getCurStatus()) )  {
		endoHist.setIsRejectPrsnt(RcConstants.NO);
		endoHist.setSignDate(hist.getSignDt());
	}

	/** 如果是追索,需要增加追索类型 , 判断使用清偿,因为清偿后才会增加背书 */
	if (hist.getCurStatus().startsWith("U_") || hist.getCurStatus().startsWith("V_")) {
//		待实现
		/*RgctRecourse recourse = null;
		if (hist.getRecourseId() != null) {
			recourse = (RgctRecourse) this.getHibernateTemplate().get(RgctRecourse.class, hist.getRecourseId());
		}
		if (recourse != null) {
			endoHist.setEndorseDt(recourse.getRecourseDt());//追索通知日期(非清偿申请日期)
			endoHist.setRecourseType(recourse.getRecourseType());
			//清偿回复签收后补充背书历史，从hist表记录中获取值对于追索业务是反的，该处改为从RgctRecourse表中取值
			endoHist.setFromName(recourse.getRecourseName());
			endoHist.setFromBankNo(recourse.getRecourseBankNo());
			endoHist.setFromAcctNo(recourse.getRecourseAcctNo());
			endoHist.setFromOrgCode(recourse.getRecourseOrgCode());
			
			endoHist.setToName(recourse.getRcvgName());
			endoHist.setToAcctNo(recourse.getRcvgAcctNo());
			endoHist.setToBankNo(recourse.getRcvgBankNo());
			endoHist.setToOrgCode(recourse.getRcvgOrgCode());
		}
		
		//将临时状态U_16、V_16置为结清（出票人或承兑人同意清偿回复签收后，更改为结清状态）
		if (RcConstants.RECOURSE_U16.equals(hist.getCurStatus()) || RcConstants.RECOURSE_V16.equals(hist.getCurStatus())) {
			hist.setCurStatus("R_16");
		}*/
	}
	this.addRgctEndoHist(endoHist);
}

public List<RgctEndoHist> getRgctEndoHistList(String rgctId) throws SQLException {
	List<Object> param = null;
	String sql = "select * from trgct_endo_hist where rgct_id="+rgctId+" and endo_type is not null order by id desc";
	IDB session = DBFactory.getDB();
	List<RgctEndoHist> list = session.getObjectListByList(sql, RgctEndoHist.class, param);
	return list;
}
}
