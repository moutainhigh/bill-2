/********************************************
* 文件名称: SubcollBillInfoDao.java
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
package com.herongtech.console.domain.subcoll.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.QueryCondition;
import com.herongtech.console.domain.bean.Dict;
import com.herongtech.console.domain.saleback.bean.SalebackBillInfo;
import com.herongtech.console.domain.subcoll.bean.SubcollBillInfo;
import com.herongtech.console.domain.subcoll.bean.SubcollQueryCondition;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 托收清单 DAO 
 * @author Administrator
 *
 */
public class SubcollBillInfoDao{

/**
 * 添加SubcollBillInfo(托收清单)信息
 * @param obj
 * @return
 * @throws SQLException
 */
public int addSubcollBillInfo(SubcollBillInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tsubcoll_bill_info(subcollmx_id,rgct_id,subcoll_id,bill_no,bill_type,bill_class,bill_source,issue_dt,due_dt,bill_money,acceptor,payee,payee_acct,province,payee_bank_name,payee_bank_no,oper_status,drawee_bank_no,drawee_bank_name,drawee_addr,forbid_flag,remitter,remitter_acct,remitter_bank_name,remitter_bank_no,bill_before_owner,belong_cust_no,belong_cust_acct,branch_no,gale_date,cust_name,is_overdue,acceptor_acct,acceptor_bank_no,subcollno,yz_source,oper_no,endorsnum,in_account,ex_serial,is_online,bill_id,impawn_bail_account,create_dt,create_time,status,source_project_no,coll_money,reparation_money,deal_branch_no,gath_oper_no,gath_audit_oper_no,apply_oper_no,audit_oper_no,gath_date,remark,pes_prod_no,pes_busi_no,pes_flow_no,coll_date,coll_payee_acct,coll_payee,coll_payee_bank_no,coll_payee_bank,acct_flow_no,order_id,relation_flag,position,buy_type,disc_dt,redeem_open_dt,redeem_end_dt,interest_days,rate,pay_money,interest,acceptor_bank_name,acct_branch_no,file_no,cur_status,prod_attr,suspd_order_id,force_gathering_flag)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getSubcollmxId(),obj.getRgctId(),obj.getSubcollId(),obj.getBillNo(),obj.getBillType(),
	obj.getBillClass(),obj.getBillSource(),obj.getIssueDt(),obj.getDueDt(),
	obj.getBillMoney(),obj.getAcceptor(),obj.getPayee(),obj.getPayeeAcct(),
	obj.getProvince(),obj.getPayeeBankName(),obj.getPayeeBankNo(),obj.getOperStatus(),
	obj.getDraweeBankNo(),obj.getDraweeBankName(),obj.getDraweeAddr(),obj.getForbidFlag(),
	obj.getRemitter(),obj.getRemitterAcct(),obj.getRemitterBankName(),obj.getRemitterBankNo(),
	obj.getBillBeforeOwner(),obj.getBelongCustNo(),obj.getBelongCustAcct(),obj.getBranchNo(),
	obj.getGaleDate(),obj.getCustName(),obj.getIsOverdue(),obj.getAcceptorAcct(),
	obj.getAcceptorBankNo(),obj.getSubcollno(),obj.getYzSource(),obj.getOperNo(),
	obj.getEndorsnum(),obj.getInAccount(),obj.getExSerial(),obj.getIsOnline(),
	obj.getBillId(),obj.getImpawnBailAccount(),obj.getCreateDt(),obj.getCreateTime(),
	obj.getStatus(),obj.getSourceProjectNo(),obj.getCollMoney(),obj.getReparationMoney(),
	obj.getDealBranchNo(),obj.getGathOperNo(),obj.getGathAuditOperNo(),obj.getApplyOperNo(),
	obj.getAuditOperNo(),obj.getGathDate(),obj.getRemark(),obj.getPesProdNo(),
	obj.getPesBusiNo(),obj.getPesFlowNo(),obj.getCollDate(),obj.getCollPayeeAcct(),
	obj.getCollPayee(),obj.getCollPayeeBankNo(),obj.getCollPayeeBank(),obj.getAcctFlowNo(),
	obj.getOrderId(),obj.getRelationFlag(),obj.getPosition(),obj.getBuyType(),
	obj.getDiscDt(),obj.getRedeemOpenDt(),obj.getRedeemEndDt(),obj.getInterestDays(),
	obj.getRate(),obj.getPayMoney(),obj.getInterest(),obj.getAcceptorBankName(),
	obj.getAcctBranchNo(),obj.getFileNo(),obj.getCurStatus(),obj.getProdAttr(),
	obj.getSuspdOrderId(),obj.getForceGatheringFlag());
}

public int deleteSubcollBillInfo(SubcollBillInfo obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tsubcoll_bill_info where subcollmx_id=?",obj.getSubcollmxId());
}

public int deleteSubcollBillInfo(String subcollmxId) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tsubcoll_bill_info where subcollmx_id=?",subcollmxId);
}

/**
 * 更新SubcollBillInfo实体信息
 * @param obj
 * @return
 * @throws SQLException
 */
public int modifySubcollBillInfo(SubcollBillInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tsubcoll_bill_info set rgct_id=?,subcoll_id=?,bill_no=?,bill_type=?,bill_class=?,bill_source=?,issue_dt=?,due_dt=?,bill_money=?,acceptor=?,payee=?,payee_acct=?,province=?,payee_bank_name=?,payee_bank_no=?,oper_status=?,drawee_bank_no=?,drawee_bank_name=?,drawee_addr=?,forbid_flag=?,remitter=?,remitter_acct=?,remitter_bank_name=?,remitter_bank_no=?,bill_before_owner=?,belong_cust_no=?,belong_cust_acct=?,branch_no=?,gale_date=?,cust_name=?,is_overdue=?,acceptor_acct=?,acceptor_bank_no=?,subcollno=?,yz_source=?,oper_no=?,endorsnum=?,in_account=?,ex_serial=?,is_online=?,bill_id=?,impawn_bail_account=?,create_dt=?,create_time=?,status=?,source_project_no=?,coll_money=?,reparation_money=?,deal_branch_no=?,gath_oper_no=?,gath_audit_oper_no=?,apply_oper_no=?,audit_oper_no=?,gath_date=?,remark=?,pes_prod_no=?,pes_busi_no=?,pes_flow_no=?,coll_date=?,coll_payee_acct=?,coll_payee=?,coll_payee_bank_no=?,coll_payee_bank=?,acct_flow_no=?,order_id=?,relation_flag=?,position=?,buy_type=?,disc_dt=?,redeem_open_dt=?,redeem_end_dt=?,interest_days=?,rate=?,pay_money=?,interest=?,acceptor_bank_name=?,acct_branch_no=?,file_no=?,cur_status=?,prod_attr=?,suspd_order_id=?,force_gathering_flag=? where subcollmx_id=?",
	obj.getRgctId(),obj.getSubcollId(),obj.getBillNo(),obj.getBillType(),
	obj.getBillClass(),obj.getBillSource(),obj.getIssueDt(),obj.getDueDt(),
	obj.getBillMoney(),obj.getAcceptor(),obj.getPayee(),obj.getPayeeAcct(),
	obj.getProvince(),obj.getPayeeBankName(),obj.getPayeeBankNo(),obj.getOperStatus(),
	obj.getDraweeBankNo(),obj.getDraweeBankName(),obj.getDraweeAddr(),obj.getForbidFlag(),
	obj.getRemitter(),obj.getRemitterAcct(),obj.getRemitterBankName(),obj.getRemitterBankNo(),
	obj.getBillBeforeOwner(),obj.getBelongCustNo(),obj.getBelongCustAcct(),obj.getBranchNo(),
	obj.getGaleDate(),obj.getCustName(),obj.getIsOverdue(),obj.getAcceptorAcct(),
	obj.getAcceptorBankNo(),obj.getSubcollno(),obj.getYzSource(),obj.getOperNo(),
	obj.getEndorsnum(),obj.getInAccount(),obj.getExSerial(),obj.getIsOnline(),
	obj.getBillId(),obj.getImpawnBailAccount(),obj.getCreateDt(),obj.getCreateTime(),
	obj.getStatus(),obj.getSourceProjectNo(),obj.getCollMoney(),obj.getReparationMoney(),
	obj.getDealBranchNo(),obj.getGathOperNo(),obj.getGathAuditOperNo(),obj.getApplyOperNo(),
	obj.getAuditOperNo(),obj.getGathDate(),obj.getRemark(),obj.getPesProdNo(),
	obj.getPesBusiNo(),obj.getPesFlowNo(),obj.getCollDate(),obj.getCollPayeeAcct(),
	obj.getCollPayee(),obj.getCollPayeeBankNo(),obj.getCollPayeeBank(),obj.getAcctFlowNo(),
	obj.getOrderId(),obj.getRelationFlag(),obj.getPosition(),obj.getBuyType(),
	obj.getDiscDt(),obj.getRedeemOpenDt(),obj.getRedeemEndDt(),obj.getInterestDays(),
	obj.getRate(),obj.getPayMoney(),obj.getInterest(),obj.getAcceptorBankName(),
	obj.getAcctBranchNo(),obj.getFileNo(),obj.getCurStatus(),obj.getProdAttr(),
	obj.getSuspdOrderId(),obj.getForceGatheringFlag(),obj.getSubcollmxId());
}

public int modifySubcollBillInfo(SubcollBillInfo obj,String subcollmxId) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tsubcoll_bill_info set rgct_id=?,subcoll_id=?,bill_no=?,bill_type=?,bill_class=?,bill_source=?,issue_dt=?,due_dt=?,bill_money=?,acceptor=?,payee=?,payee_acct=?,province=?,payee_bank_name=?,payee_bank_no=?,oper_status=?,drawee_bank_no=?,drawee_bank_name=?,drawee_addr=?,forbid_flag=?,remitter=?,remitter_acct=?,remitter_bank_name=?,remitter_bank_no=?,bill_before_owner=?,belong_cust_no=?,belong_cust_acct=?,branch_no=?,gale_date=?,cust_name=?,is_overdue=?,acceptor_acct=?,acceptor_bank_no=?,subcollno=?,yz_source=?,oper_no=?,endorsnum=?,in_account=?,ex_serial=?,is_online=?,bill_id=?,impawn_bail_account=?,create_dt=?,create_time=?,status=?,source_project_no=?,coll_money=?,reparation_money=?,deal_branch_no=?,gath_oper_no=?,gath_audit_oper_no=?,apply_oper_no=?,audit_oper_no=?,gath_date=?,remark=?,pes_prod_no=?,pes_busi_no=?,pes_flow_no=?,coll_date=?,coll_payee_acct=?,coll_payee=?,coll_payee_bank_no=?,coll_payee_bank=?,acct_flow_no=?,order_id=?,relation_flag=?,position=?,buy_type=?,disc_dt=?,redeem_open_dt=?,redeem_end_dt=?,interest_days=?,rate=?,pay_money=?,interest=?,acceptor_bank_name=?,acct_branch_no=?,file_no=?,cur_status=?,prod_attr=?,suspd_order_id=?,force_gathering_flag=? where subcollmx_id=?",
	obj.getRgctId(),obj.getSubcollId(),obj.getBillNo(),obj.getBillType(),
	obj.getBillClass(),obj.getBillSource(),obj.getIssueDt(),obj.getDueDt(),
	obj.getBillMoney(),obj.getAcceptor(),obj.getPayee(),obj.getPayeeAcct(),
	obj.getProvince(),obj.getPayeeBankName(),obj.getPayeeBankNo(),obj.getOperStatus(),
	obj.getDraweeBankNo(),obj.getDraweeBankName(),obj.getDraweeAddr(),obj.getForbidFlag(),
	obj.getRemitter(),obj.getRemitterAcct(),obj.getRemitterBankName(),obj.getRemitterBankNo(),
	obj.getBillBeforeOwner(),obj.getBelongCustNo(),obj.getBelongCustAcct(),obj.getBranchNo(),
	obj.getGaleDate(),obj.getCustName(),obj.getIsOverdue(),obj.getAcceptorAcct(),
	obj.getAcceptorBankNo(),obj.getSubcollno(),obj.getYzSource(),obj.getOperNo(),
	obj.getEndorsnum(),obj.getInAccount(),obj.getExSerial(),obj.getIsOnline(),
	obj.getBillId(),obj.getImpawnBailAccount(),obj.getCreateDt(),obj.getCreateTime(),
	obj.getStatus(),obj.getSourceProjectNo(),obj.getCollMoney(),obj.getReparationMoney(),
	obj.getDealBranchNo(),obj.getGathOperNo(),obj.getGathAuditOperNo(),obj.getApplyOperNo(),
	obj.getAuditOperNo(),obj.getGathDate(),obj.getRemark(),obj.getPesProdNo(),
	obj.getPesBusiNo(),obj.getPesFlowNo(),obj.getCollDate(),obj.getCollPayeeAcct(),
	obj.getCollPayee(),obj.getCollPayeeBankNo(),obj.getCollPayeeBank(),obj.getAcctFlowNo(),
	obj.getOrderId(),obj.getRelationFlag(),obj.getPosition(),obj.getBuyType(),
	obj.getDiscDt(),obj.getRedeemOpenDt(),obj.getRedeemEndDt(),obj.getInterestDays(),
	obj.getRate(),obj.getPayMoney(),obj.getInterest(),obj.getAcceptorBankName(),
	obj.getAcctBranchNo(),obj.getFileNo(),obj.getCurStatus(),obj.getProdAttr(),
	obj.getSuspdOrderId(),obj.getForceGatheringFlag(),subcollmxId);
}
/**
 * 通过明细主键数组 查询托收明细
 * @param ids
 * @return
 * @throws SQLException
 */
public List<SubcollBillInfo> getSubcollBillInfoByIds(String ids) throws SQLException {
    StringBuffer sql = new StringBuffer("select * from tsubcoll_bill_info where subcollmx_id in(");
    String idArr[] = ids.split(",");
    List<Object> param = new ArrayList<Object>(idArr.length);
	for (String id : idArr) {
		sql.append("?,");
		param.add(id);
	}
	sql.deleteCharAt(sql.length()-1);
	sql.append(')');
	IDB session = DBFactory.getDB();
	return session.getObjectListByList(sql.toString(), SubcollBillInfo.class, param);
}

/**
 *  功能描述：根据批次id查询清单
 * @param page
 * @param query
 * @return
 * @throws SQLException
 */
public List<SubcollBillInfo> getSubcollBillsBySearchBean(Page page,SubcollQueryCondition query) throws SQLException{
	IDB session = DBFactory.getDB();
	String baseSql="select bill.* from tsubcoll_bill_info bill";
	//分页开始位置
	int startIndex = page.getCurrentResult();
	 QueryCondition qc=new QueryCondition();
	 try {
         qc.initFromSearchBean(query);
     } catch (Exception e) {
         e.printStackTrace();
     }
	 String sql=qc.getAllSqlString(baseSql);
     System.out.println(sql);
	// 获得并返回本次查询的总条数
	int count = session.accountByList(qc.getCountSql("bill.subcollmx_Id"), qc.getParameterValues());
	page.setTotalResult(count);
	return session.getObjectListByListForPage(sql,SubcollBillInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
}

public int updateSubcollBillInfoStatus(String ids, String[] queryStatus,
		String afterStatus) throws SQLException,BizAppException {
	if(queryStatus==null || queryStatus.length == 0 || StringUtils.isBlank(afterStatus)) 
			throw new BizAppException(ISysErrorNo.PARAM_VALIDATE_EXCEPTION_CODE, "前置状态与后置状态均不能为空");
	List<Object> param = new ArrayList<Object>();
	param.add(afterStatus);
	StringBuffer sql = new StringBuffer("update tsubcoll_bill_info set oper_status = ?");
	
	sql.append(" where subcollmx_id in (");
	String idArr[] = ids.split(",");
	for (String id : idArr) {
		sql.append("?,");
		param.add(id);
	}
	sql.deleteCharAt(sql.length()-1);
	sql.append(") and oper_status in (");
	for (String status : queryStatus) {
		sql.append("?,");
		param.add(status);
	}
	sql.deleteCharAt(sql.length()-1);
	sql.append(')');
	IDB session = DBFactory.getDB();
	return session.executeByList(sql.toString(), param);
}

/**
 * 获得SubcollBillInfo bean 通过rgctId
 * @param rgctId
 * @throws BizAppException
 */
public SubcollBillInfo getSubcollBillForRgctId(String rgctId) throws SQLException {
	SubcollBillInfo billInfo = new SubcollBillInfo();
	IDB session = DBFactory.getDB(); // 获取数据库连接
	String sqls = "select * from tsubcoll_bill_info where rgct_id = ? order by subcollmx_id desc";
	try {
		billInfo = session.getObject(sqls, SubcollBillInfo.class, rgctId);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return billInfo;
}

/**
 * 获得SubcollBillInfo bean 通过rgctId 和 operstatus
 * @param rgctId
 * @param operstatus
 * @return
 * @throws BizAppException
 */
public SubcollBillInfo getSubcollBillForRgctIdAndOperStatus(String rgctId,String operstatus) throws SQLException {
	SubcollBillInfo billInfo = new SubcollBillInfo();
	IDB session = DBFactory.getDB(); // 获取数据库连接
	String sqls ="select * from tsubcoll_bill_info where oper_status='"+operstatus+"'and rgct_id in(?)";
	try {
		billInfo = session.getObject(sqls, SubcollBillInfo.class, rgctId);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return billInfo;
}

/**
 * 获得List<SubcollBillInfo>  通过subcollId
 * @param subcollId
 * @return
 * @throws BizAppException
 */
public List<SubcollBillInfo> getSubcollBillForSubcollId(String subcollId) throws SQLException {
	List<SubcollBillInfo> list = null;
	IDB session = DBFactory.getDB(); // 获取数据库连接
	String sqls = "select * from tsubcoll_bill_info where subcoll_id = ?";
	try {
		list = session.getObjectList(sqls, SubcollBillInfo.class, subcollId);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return list;
}

///**根据批次id查询该批次下的清单id（新增发拖保存之后的oper_status=BS310）*/
//public List<SubcollBillInfo> getSubcollBillInfolistbypiciid(String id) throws BizAppException{
//	List<SubcollBillInfo> list = null;
//	IDB session = DBFactory.getDB(); // 获取数据库连接
//	String sqls = "select * from tsubcoll_bill_info where oper_status='BS310' and rgct_id = ?";
//	try {
//		list = session.getObjectList(sqls, SubcollBillInfo.class, id);
//	} catch (SQLException e) {
//		e.printStackTrace();
//	}
//	return list;
//}
	/**根据票据品种的标识获得字符串
	根据票据类型的标识获得字符串
	票据来源字符串  1:代保管，2票据池,3质押,4贴现；5转入，6理财，7分行带总行代保管 */
	public String getBillSourceStringbyItemCode(String billsource, String keyNo)throws BizAppException{
		IDB session = DBFactory.getDB();
		Dict objs = null;
		try {
			objs = session.getObject("select * from vdict where key_no='"+keyNo+"' and item_code=?",Dict.class,billsource);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return objs.getItemValue();
	}
	
//	/**根据清单ids(逗号分隔开的清单id)和OPER_STATUS=BS310查出清单list  （该list集合是托收增加保存完（OPER_STATUS=BS310）之后的清单）*/
//	public List<SubcollBillInfo> getSubcollBillInfolistbyqingdanids(String ids)throws BizAppException{
//		List<SubcollBillInfo> billInfos = null;
//		IDB session = DBFactory.getDB(); // 获取数据库连接
//		String sqls = "select * from tsubcoll_bill_info where oper_status!='BS300' and rgct_id in (?)";
//		try {
//			billInfos = session.getObjectList(sqls, SubcollBillInfo.class, ids);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return billInfos;
//		
//		
//	}
	/**
	 * 1.托收打印时判断 票据是否保存所用
	 * 2.根据ids字符串和前置状态（保存状态BS310）查询SubcollBillInfo集合（集合不为空说明这批ids中有保存完的票）
	 * @throws SQLException 
	 * @throws SQLException */
	public List<SubcollBillInfo> getIssaveListbyidsandoperstatus(String ids,String operstatus) throws SQLException{
		IDB session = DBFactory.getDB();
		List<SubcollBillInfo> list=null;
		String sql ="select * from tsubcoll_bill_info where oper_status='"+operstatus+"'and rgct_id in("+ids+")";
		
			list = session.getObjectList(sql, SubcollBillInfo.class);
	
		return list;
	}
	
	/**
	 * 1.托收打印时判断 票据是否保存所用
	 * 2.根据主键subcollmx_id字符串和前置状态（保存状态BS310）查询SubcollBillInfo集合（集合不为空说明这批subcollmxid中有保存完的票）
	 * @throws SQLException 
	 * @throws SQLException */
	public List<SubcollBillInfo> getIssaveListbysubcollmxidandoperstatus(String subcollmxid,String operstatus) throws SQLException{
		IDB session = DBFactory.getDB();
		List<SubcollBillInfo> list=null;
		String sql ="select * from tsubcoll_bill_info where oper_status='"+operstatus+"'and subcollmx_id in("+subcollmxid+")";
		
			list = session.getObjectList(sql, SubcollBillInfo.class);
	
		return list;
	}
}
