/********************************************
 * 文件名称: ParamService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: yanjl
 * 开发时间: 2016-6-15 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.service.sequence;
 
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;

import com.herongtech.commons.tools.DataUtil;
import com.herongtech.commons.tools.DateUtil;
import com.herongtech.console.cache.SequenceCache;
import com.herongtech.console.core.constant.IErrorNo;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.service.interfaces.ISequenceService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;

public class SequenceService implements ISequenceService{
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe zhangph ";
    
	/**
     * 生成内部日子流水号
     */
	public  String getLogSerialNo() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.LOG_NO).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取日志流水号错误");	
		}
	}
	
	/**
     * 生成内部日子流水号
     */
	public  String getRcLogSerialNo() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.RC_LOG_NO).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取日志流水号错误");	
		}
	}
	
	/**
     * 获取rgctbillinfo的id
     */
	public  String getBillInfoId() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.RGCT_BILL_INFO_ID).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取日志流水号错误");	
		}
	}
	
	/**
     * 获取RiskBill风险票据表的id
     */
	public  String getRiskBillId() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.RISK_BILL_ID).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取日志流水号错误");	
		}
	}
	
	/**
     * 获取rgctbillhist的id
     */
	public  String getBillInfoHist() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.RGCT_BILL_HIST_ID).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取日志流水号错误");	
		}
	}
	
	/**
     * 获取rgctendohist的id
     */
	public  String getBillEndoHist() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.RGCT_ENDO_HIST_ID).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取日志流水号错误");	
		}
	}
	/**
     * 获取rgctTemphist的id
     */
	public  String getBillTempHist() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.RGCT_TEMP_HIST_ID).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取日志流水号错误");	
		}
	}
	
	/**
     * 获取msgid_sequence
     */
	public  String getMsgIdSequence() throws BizAppException{
		try{
		    long no= SequenceCache.getInstance(this.MSGID_SEQUENCE).getSequenceNo();
		    return DateTimeUtil.get_ECDS_YYMMDD_Date() + DataUtil.fix0BeforeNumber(no, 8);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取日志流水号错误");	
		}
	}
	/**
	 * 根据指定流水号keyno生成批量流水号
	 */
	public long getBatchSerialNo(String keyNo, int inc) throws BizAppException {
		try{
		    long no = SequenceCache.getInstance(keyNo).getBatchSequenceNo(inc);
		    return no;
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "生成批量流水号失败");	
		}
	}
	
	/**
     * 获取DISC_BILL_INFO_ID
     */
	public  String getDISC_BILL_INFO_ID() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.DISC_BILL_INFO_ID).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取日志流水号错误");	
		}
	}
	
	/**
     * 获取DISC_APPLY_ID
     */
	public  String getDISC_APPLY_ID() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.DISC_APPLY_ID).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取日志流水号错误");	
		}
	}
	
	/**
     * 获取REBUY_APPLY_ID
     */
	public  String getREBUY_APPLY_ID() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.REBUY_APPLY_ID).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取日志流水号错误");	
		}
	}
	
	/**
     * 获取REBUY_BILL_INFO_ID
     */
	public  String getREBUY_BILL_INFO_ID() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.REBUY_BILL_INFO_ID).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取日志流水号错误");	
		}
	}
	
	/**
     * 获取SALE_APPLY_ID
     */
	public  String getSALE_APPLY_ID() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.SALE_APPLY_ID).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取日志流水号错误");	
		}
	}
	
	/**
     * 获取SALE_BILL_INFO_ID
     */
	public  String getSALE_BILL_INFO_ID() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.SALE_BILL_INFO_ID).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取日志流水号错误");	
		}
	}
	/**
     * 获取SALEBACK_BILL_INFO_ID
     */
	public  String getSALEBACK_BILL_INFO_ID() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.SALEBACK_BILL_INFO_ID).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取日志流水号错误");	
		}
	}
	
	/**
     * 获取SALEBACK_APPLY_INFO_ID
     */
	public  String getSALEBACK_APPLY_INFO_ID() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.SALEBACK_APPLY_INFO_ID).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取日志流水号错误");	
		}
	}
	
	
	/**
     * 获取SAVE_APPLY_ID
     */
	public  String getSAVE_APPLY_ID() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.SAVE_APPLY_ID).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取日志流水号错误");	
		}
	}
	
	/**
     * 获取SAVE_BILL_INFO_ID
     */
	public  String getSAVE_BILL_INFO_ID() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.SAVE_BILL_INFO_ID).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取日志流水号错误");	
		}
	}
	
	/**
     * 获取GET_BILL_INFO_ID
     */
	public  String getGET_BILL_INFO_ID() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.GET_BILL_INFO_ID).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取日志流水号错误");	
		}
	}
	
	/**
     * 获取GET_APPLY_ID
     */
	public  String getGET_APPLY_ID() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.GET_APPLY_ID).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取日志流水号错误");	
		}
	}
	
	/**
     * 获取SUBCOLL_APPLY_ID
     */
	public  String getSUBCOLL_APPLY_ID() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.SUBCOLL_APPLY_ID).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取日志流水号错误");	
		}
	}
	
	/**
     * 获取SUBCOLL_BILL_INFO_ID
     */
	public  String getSUBCOLL_BILL_INFO_ID() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.SUBCOLL_BILL_INFO_ID).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取日志流水号错误");	
		}
	}
	
	/**
     * 获取ACPT_BILL_INFO_ID
     */
	public  String getACPT_BILL_INFO_ID() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.ACPT_BILL_INFO_ID).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取银承清单流水号错误");	
		}
	}
	/**
     * 获取ACPT_APPLY_ID
     */
	public  String getACPT_APPLY_ID() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.ACPT_APPLY_ID).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取银承批次流水号错误");	
		}
	}
	
	
	/**
     * 获取ACPT_SSPD_REG_ID
     */
	public  String getACPT_SSPD_REG_ID() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.ACPT_SSPD_REG_ID).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取银承批次流水号错误");	
		}
	}
	

	/**
     * 获取到期付款ACPT_COLLTN_REG_ID
     */
	public  String getACPT_COLLTN_REG_ID() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.ACPT_COLLTN_REG_ID).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取银承批次流水号错误");	
		}
	}
	
	/**
     * 获取ACCT_BALANCE_HIS_ID的id
     */
	public  String getAcctBalanceHisId() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.ACCT_BALANCE_HIS_ID).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取流水号错误");	
		}
	}
	/**
     * 获取task_log_id的id
     */
	public  String getTaskLogId() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.TASK_LOG_ID).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取流水号错误");	
		}
	}
	/**
     * 获取fac_create_flow_id的id
     */
	public  String getFacCreateFlowId() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.FAC_CREATE_FLOW_ID).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取流水号错误");	
		}
	}
	
	
	/**
	 * 生成批次流水号
	 * @param key
	 * @param BrchNo
	 * @param size
	 * @return
	 * @throws SQLException 
	 */
	public String getFlowNo(String key, String brchNo, int size) throws BizAppException {
		try{
			long no= SequenceCache.getInstance(this.APPLY_NO).getSequenceNo();
		    return key+brchNo+DateTimeUtil.get_YYYYMMDD_Date() + DataUtil.fix0BeforeNumber(no, size);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取流水号错误");	
		}
	}
	
	/**
	 * 生成贴现批次号
	 * @param brchNo
	 * @return
	 * @throws BizAppException
	 */
	public String getDiscountApplyNo(String brchNo) throws BizAppException{
		return getFlowNo("TX", brchNo, 6);
	}
	/**
	 * 生成转入批次号
	 * @param brchNo
	 * @return
	 * @throws BizAppException
	 */
	public String getRebuyApplyNo(String brchNo) throws BizAppException{
		return getFlowNo("ZR", brchNo, 6);
	}
	
	/**
	 * 生成转出批次号
	 * @param brchNo
	 * @return
	 * @throws BizAppException
	 */
	public String getSaleApplyNo(String brchNo) throws BizAppException{
		return getFlowNo("ZC", brchNo, 6);
	}
	/**
	 * 生成托收批次号
	 * @param brchNo
	 * @return
	 * @throws BizAppException
	 */
	public String getSubcollApplyNo(String brchNo) throws BizAppException{
		return getFlowNo("TS", brchNo, 6);
	}
	/**
	 * 生成质押批次号
	 * @param brchNo
	 * @return
	 * @throws BizAppException
	 */
	public String getSaveApplyNo(String brchNo) throws BizAppException{
		return getFlowNo("ZY", brchNo, 6);
	}
	/**
	 * 生成解质押批次号
	 * @param brchNo
	 * @return
	 * @throws BizAppException
	 */
	public String getGetApplyNo(String brchNo) throws BizAppException{
		return getFlowNo("JZY", brchNo, 6);
	}
	/**
	 * 生成银承批次号
	 * @param brchNo
	 * @return
	 * @throws BizAppException
	 */
	public String getAcptApplyNo(String brchNo) throws BizAppException{
		return getFlowNo("YC", brchNo, 6);
	}
	
	/**
	 * 生成存票批次号
	 * @param brchNo
	 * @return
	 * @throws BizAppException
	 */
	public String getIntoApplyNo(String brchNo) throws BizAppException{
		return getFlowNo("CP", brchNo, 6);
	}
	
	/**
	 * 生成取票批次号
	 * @param brchNo
	 * @return
	 * @throws BizAppException
	 */
	public String getOutApplyNo(String brchNo) throws BizAppException{
		return getFlowNo("QP", brchNo, 6);
	}
	
	public String getPrimaryKeyValue()throws BizAppException{
	    try{
	        int id=getId();
	        if(id >0)
	            return getStringId(id);
            long no= SequenceCache.getInstance(this.PRIMARY_KEY_VALUE).getSequenceNo();
            return no+"";
        }catch(SQLException e){
            throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取主键错误");   
        }
	}
	
	private int getId() throws SQLException {
	    IDB session = null;
	    int id=0;;
	    ResultSet rs=null;
	    try {
	        session = DBFactory.getNewDB(); // 获取数据库连接
	        String dialectName=session.getDialectName();
	        if("DB2".equalsIgnoreCase(dialectName)){
	            rs = session.getResultSet("values next value for "+ID_SEQUENCE);
	        }else if("ORACLE".equalsIgnoreCase(dialectName)){
	            rs = session.getResultSet("select "+ID_SEQUENCE+".nextval from dual");
	        }
	        if(rs != null){
	            rs.next();
	            id=rs.getInt(1); 
	        }
	       
	    }catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
        }finally{
            try {
                if(rs != null){
                    session.closeResultSetAndStatement(rs);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                DBFactory.closeDB(session);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	    return id;
	}
	
	
	private String getStringId(int id){
	    return DateTimeUtil.get_YYMMDD_Date().substring(0, 2).concat(StringUtils.leftPad(id+"", 8,"0"));
	}
	
	
	/**
	 * 根据keyNo获取主键序列号
	 * @param keyNo
	 * @return
	 * @throws BizAppException
	 */
	public String getSequenceNoByKeyNo(String keyNo) throws BizAppException{
		try{
            long no= SequenceCache.getInstance(keyNo).getSequenceNo();
            return no+"";
        }catch(SQLException e){
            throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取主键错误");   
        }
	}
	
	public String getAcctFlowNo(String branchNo)throws BizAppException{
	    try{
            long no= SequenceCache.getInstance(this.ACCT_FLOW_NO).getSequenceNo();
            return branchNo+DateTimeUtil.getWorktimeAsyyyyMMddHHmmss() + DataUtil.fix0BeforeNumber(no, 6);
        }catch(SQLException e){
            throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取主键错误");   
        }
	}
	
	
	
	/**
	 * 生成到期回购批次号
	 * @param brchNo
	 * @return
	 * @throws BizAppException
	 */
	public String getBuybackApplyNo(String brchNo) throws BizAppException{
		return getFlowNo("HG", brchNo, 6);
	}
	
	/**
     * 获取BUYBACK_APPLY_INFO_ID
     */
	public  String getBUYBACK_APPLY_INFO_ID() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.BUYBACK_APPLY_INFO_ID).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取到期回购批次id错误");	
		}
	}
	
	/**
     * 获取BUYBACK_BILL_INFO_ID
     */
	public  String getBUYBACK_BILL_INFO_ID() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.BUYBACK_BILL_INFO_ID).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取到期回购清单id错误");	
		}
	}

	/**
     * 获取ASSU_BILL_INFO_ID
     */
	public String getASSU_BILL_INFO_ID() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.ASSU_BILL_INFO_ID).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取保证清单流水号错误");	
		}
	}

	/**
     * 获取ASSU_APPLY_ID
     */
	public String getASSU_APPLY_ID() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.ASSU_APPLY_ID).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取保证批次流水号错误");	
		}
	}

	/**
	 * 生成保证批次号
	 * @param brchNo
	 * @return
	 * @throws BizAppException
	 */
	public String getAssuApplyNo(String brchNo) throws BizAppException {
		return getFlowNo("BZ", brchNo, 6);
	}

	@Override
	public String getENTITY_REGISTER_ID() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.ENTITY_REGISTER_ID).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取纸票登记主键错误");	
		}
	}
	
	/**
     * 获取Sign_Prod_ID
     */
	public String getSign_Prod_ID() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.Sign_Prod_ID).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取保证批次流水号错误");	
		}
	}
	
	
	/**
     * 获取签约协议书编号
     */
	public  String getSign_ID() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.Sign_ID).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取签约协议书编号错误");	
		}
	}
	
	/**
     * 获取快捷菜单编号
     */
	public  String getConMenu_ID() throws BizAppException {
		try{
		    int id=getId();
            if(id >0)
                return getStringId(id);
		    long no= SequenceCache.getInstance(this.Con_Menu_ID).getSequenceNo();
		    return DateUtil.getDate()+ DataUtil.fix0BeforeString(DateUtil.getTime(),6) + DataUtil.fix0BeforeNumber(no, 6);
		}catch(SQLException e){
		    throw new BizAppException(IErrorNo.ERR_GENSERIAL, "获取快捷菜单编号错误");	
		}
	}
}
