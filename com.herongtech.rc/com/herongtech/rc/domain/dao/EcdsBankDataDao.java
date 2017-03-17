/********************************************
* 文件名称: EcdsBankDataDao.java
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
import java.util.List;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.rc.domain.bean.EcdsBankData;
public class EcdsBankDataDao{

public int addEcdsBankData(EcdsBankData obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tECDS_BANK_DATA(row_number,actor_type,row_other_code,super_actor,local_node_code,root_super_actor,cate_people_code,city_code,actor_full_call,actor_for_short,address,post_edit,phone,email,status,inure_date,log_out_date,update_time,lately_update_work,log_update_expect,remark,dn_field,sn_field,cert_bind_status,meet_income_code,continue_row_num,continue_meet_income,if_continue,history_continue_con,sttlm_acc_status,sttlm_acc_update,sttlm_acc_uptime)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getRowNumber(),obj.getActorType(),obj.getRowOtherCode(),obj.getSuperActor(),obj.getLocalNodeCode(),
	obj.getRootSuperActor(),obj.getCatePeopleCode(),obj.getCityCode(),obj.getActorFullCall(),
	obj.getActorForShort(),obj.getAddress(),obj.getPostEdit(),obj.getPhone(),
	obj.getEmail(),obj.getStatus(),obj.getInureDate(),obj.getLogOutDate(),
	obj.getUpdateTime(),obj.getLatelyUpdateWork(),obj.getLogUpdateExpect(),obj.getRemark(),
	obj.getDnField(),obj.getSnField(),obj.getCertBindStatus(),obj.getMeetIncomeCode(),
	obj.getContinueRowNum(),obj.getContinueMeetIncome(),obj.getIfContinue(),obj.getHistoryContinueCon(),
	obj.getSttlmAccStatus(),obj.getSttlmAccUpdate(),obj.getSttlmAccUptime());
}
/**清空表中数据
 * @throws SQLException */
public void truncateEcdsBankData(EcdsBankData obj) throws SQLException{
	IDB session = DBFactory.getNewDB(); // 获取数据库连接
    String dialectName=session.getDialectName();
    if("DB2".equalsIgnoreCase(dialectName)){
    	session.execute("ALTER TABLE tECDS_BANK_DATA ACTIVATE NOT LOGGED INITIALLY WITH EMPTY TABLE;");
    }else if("ORACLE".equalsIgnoreCase(dialectName)){
    	session.execute("truncate table tECDS_BANK_DATA");
    }
	
}

public int deleteEcdsBankData(EcdsBankData obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tECDS_BANK_DATA where row_number=?",obj.getRowNumber());
}

public int deleteEcdsBankData(String rowNumber) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tECDS_BANK_DATA where row_number=?",rowNumber);
}

public int modifyEcdsBankData(EcdsBankData obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tECDS_BANK_DATA set actor_type=?,row_other_code=?,super_actor=?,local_node_code=?,root_super_actor=?,cate_people_code=?,city_code=?,actor_full_call=?,actor_for_short=?,address=?,post_edit=?,phone=?,email=?,status=?,inure_date=?,log_out_date=?,update_time=?,lately_update_work=?,log_update_expect=?,remark=?,dn_field=?,sn_field=?,cert_bind_status=?,meet_income_code=?,continue_row_num=?,continue_meet_income=?,if_continue=?,history_continue_con=?,sttlm_acc_status=?,sttlm_acc_update=?,sttlm_acc_uptime=? where row_number=?",
	obj.getActorType(),obj.getRowOtherCode(),obj.getSuperActor(),obj.getLocalNodeCode(),
	obj.getRootSuperActor(),obj.getCatePeopleCode(),obj.getCityCode(),obj.getActorFullCall(),
	obj.getActorForShort(),obj.getAddress(),obj.getPostEdit(),obj.getPhone(),
	obj.getEmail(),obj.getStatus(),obj.getInureDate(),obj.getLogOutDate(),
	obj.getUpdateTime(),obj.getLatelyUpdateWork(),obj.getLogUpdateExpect(),obj.getRemark(),
	obj.getDnField(),obj.getSnField(),obj.getCertBindStatus(),obj.getMeetIncomeCode(),
	obj.getContinueRowNum(),obj.getContinueMeetIncome(),obj.getIfContinue(),obj.getHistoryContinueCon(),
	obj.getSttlmAccStatus(),obj.getSttlmAccUpdate(),obj.getSttlmAccUptime(),obj.getRowNumber());
}

public int modifyEcdsBankData(EcdsBankData obj,String rowNumber) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tECDS_BANK_DATA set actor_type=?,row_other_code=?,super_actor=?,local_node_code=?,root_super_actor=?,cate_people_code=?,city_code=?,actor_full_call=?,actor_for_short=?,address=?,post_edit=?,phone=?,email=?,status=?,inure_date=?,log_out_date=?,update_time=?,lately_update_work=?,log_update_expect=?,remark=?,dn_field=?,sn_field=?,cert_bind_status=?,meet_income_code=?,continue_row_num=?,continue_meet_income=?,if_continue=?,history_continue_con=?,sttlm_acc_status=?,sttlm_acc_update=?,sttlm_acc_uptime=? where row_number=?",
	obj.getActorType(),obj.getRowOtherCode(),obj.getSuperActor(),obj.getLocalNodeCode(),
	obj.getRootSuperActor(),obj.getCatePeopleCode(),obj.getCityCode(),obj.getActorFullCall(),
	obj.getActorForShort(),obj.getAddress(),obj.getPostEdit(),obj.getPhone(),
	obj.getEmail(),obj.getStatus(),obj.getInureDate(),obj.getLogOutDate(),
	obj.getUpdateTime(),obj.getLatelyUpdateWork(),obj.getLogUpdateExpect(),obj.getRemark(),
	obj.getDnField(),obj.getSnField(),obj.getCertBindStatus(),obj.getMeetIncomeCode(),
	obj.getContinueRowNum(),obj.getContinueMeetIncome(),obj.getIfContinue(),obj.getHistoryContinueCon(),
	obj.getSttlmAccStatus(),obj.getSttlmAccUpdate(),obj.getSttlmAccUptime(),rowNumber);
}

public EcdsBankData getEcdsBankData(String rowNumber) throws SQLException {
	IDB session = DBFactory.getDB();
	EcdsBankData obj = (EcdsBankData)session.getObject("select * from tECDS_BANK_DATA where row_number=?",EcdsBankData.class,rowNumber);
	return obj;
}

public List<EcdsBankData> getEcdsBankDatalist(Page page,String branchbankno,String certBindingStatus,String row_number) throws SQLException {
	IDB session = DBFactory.getDB();
	if(StringUtils.isEmpty(certBindingStatus)){
		if(StringUtils.isEmpty(row_number)){
			int count = session.account("select count(0) from tecds_bank_data where row_number in (select pay_bank_no from tbranch  where bank_no=(select bank_no from tbranch where pay_bank_no=?))",branchbankno);
			page.setTotalResult(count);
			return session.getObjectList("select * from tecds_bank_data where row_number in (select pay_bank_no from tbranch  where bank_no=(select bank_no from tbranch where pay_bank_no=?))",EcdsBankData.class,branchbankno);
		}else{
			int count = session.account("select count(0) from tecds_bank_data where row_number in (?)",row_number);
			page.setTotalResult(count);
			return session.getObjectList("select * from tecds_bank_data where row_number in (?) ",EcdsBankData.class,row_number);
		}
	}
	if(StringUtils.isEmpty(row_number)){
		int count = session.account("select count(0) from tecds_bank_data where row_number in (select pay_bank_no from tbranch  where bank_no=(select bank_no from tbranch where pay_bank_no=?)) and cert_bind_status=?",branchbankno,certBindingStatus);
		page.setTotalResult(count);
		return session.getObjectList("select * from tecds_bank_data where row_number in (select pay_bank_no from tbranch  where bank_no=(select bank_no from tbranch where pay_bank_no=?)) and cert_bind_status=?",EcdsBankData.class,branchbankno,certBindingStatus);
	}else{
		int count = session.account("select count(0) from tecds_bank_data where row_number in (?) and cert_bind_status=?",row_number,certBindingStatus);
		page.setTotalResult(count);
		return session.getObjectList("select * from tecds_bank_data where row_number in (?) and cert_bind_status=?",EcdsBankData.class,row_number,certBindingStatus);
	}
	
	
}


}
