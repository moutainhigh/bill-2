package com.herongtech.console.service.ecdsdataimportservice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IEcdsDataImportService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.EcdsApData;
import com.herongtech.rc.domain.bean.EcdsAuthlistData;
import com.herongtech.rc.domain.bean.EcdsBankAssData;
import com.herongtech.rc.domain.bean.EcdsBankData;
import com.herongtech.rc.domain.bean.EcdsCommonData;
import com.herongtech.rc.domain.bean.EcdsPsAssData;
import com.herongtech.rc.domain.bean.EcdsPsapAssData;
import com.herongtech.rc.domain.dao.EcdsApDataDao;
import com.herongtech.rc.domain.dao.EcdsAuthlistDataDao;
import com.herongtech.rc.domain.dao.EcdsBankAssDataDao;
import com.herongtech.rc.domain.dao.EcdsBankDataDao;
import com.herongtech.rc.domain.dao.EcdsCommonDataDao;
import com.herongtech.rc.domain.dao.EcdsPsAssDataDao;
import com.herongtech.rc.domain.dao.EcdsPsapAssDataDao;
import com.herongtech.rc.service.interfaces.IEcdsBankDataService;
/**ecds数据导入接口service*/
public class EcdsDataImportService  implements IEcdsDataImportService{

	/**
	 * 取得相应的实体类
	 * 
	 * @param ecdsDataList
	 * @param className
	 * @return
	 * @throws BizAppException 
	 * @throws SQLException 
	 */
	public void createEcdsData(List ecdsDataList, String className) throws BizAppException, SQLException {
		if (className.equals("ECDS_BANK_DATA")) {
			createEcdsBankData(ecdsDataList);
		} else if (className.equals("ECDS_BANK_ASS_DATA")) {
			createEcdsBankAssData(ecdsDataList);
		} else if (className.equals("ECDS_AUTHLIST_DATA")) {
			createEcdsAuthListData(ecdsDataList);
		} else if (className.equals("ECDS_COMMON_DATA")) {
			createEcdsCommonData(ecdsDataList);
		} else if (className.equals("ECDS_AP_DATA")) {
			createEcdsApData(ecdsDataList);
		} else if (className.equals("ECDS_PS_ASS_DATA")) {
			createEcdsPsAssData(ecdsDataList);
		} else if (className.equals("ECDS_PSAP_ASS_DATA")) {
			createEcdsPsapAssData(ecdsDataList);
		}

		

	}

	/**
	 * 将所有属性set到实体类存入一个list中 行号数据文件
	 * @throws BizAppException 
	 * @throws BizAppException 
	 * @throws SQLException 
	 */
	public void createEcdsBankData(List ecdsDataList) throws BizAppException, SQLException{
		List ecdsBankDataList = new ArrayList();
		Iterator iter = ecdsDataList.iterator();	
		EcdsBankDataDao dao =new EcdsBankDataDao();
		EcdsBankData ecdsbankdata = new EcdsBankData();
		dao.truncateEcdsBankData(ecdsbankdata);
		while (iter.hasNext()) {
			String[] ecdsData = (String[]) iter.next();
			if (ecdsData.length == 32) {
				EcdsBankData ecdsBankData = new EcdsBankData();
				ecdsBankData.setRowNumber(ecdsData[0]);
				ecdsBankData.setActorType(ecdsData[1]);
				ecdsBankData.setRowOtherCode(ecdsData[2]);
				ecdsBankData.setSuperActor(ecdsData[3]);
				ecdsBankData.setLocalNodeCode(ecdsData[4]);
				ecdsBankData.setRootSuperActor(ecdsData[5]);
				ecdsBankData.setCatePeopleCode(ecdsData[6]);
				ecdsBankData.setCityCode(ecdsData[7]);
				ecdsBankData.setActorFullCall(ecdsData[8]);
				ecdsBankData.setActorForShort(ecdsData[9]);
				ecdsBankData.setAddress(ecdsData[10]);
				ecdsBankData.setPostEdit(ecdsData[11]);
				ecdsBankData.setPhone(ecdsData[12]);
				ecdsBankData.setEmail(ecdsData[13]);
				ecdsBankData.setStatus(ecdsData[14]);
				ecdsBankData.setInureDate(ecdsData[15]);
				ecdsBankData.setLogOutDate(ecdsData[16]);
				ecdsBankData.setUpdateTime(ecdsData[17]);
				ecdsBankData.setLatelyUpdateWork(ecdsData[18]);
				ecdsBankData.setLogUpdateExpect(ecdsData[19]);
				ecdsBankData.setRemark(ecdsData[20]);
				ecdsBankData.setDnField(ecdsData[21]);
				ecdsBankData.setSnField(ecdsData[22]);
				ecdsBankData.setCertBindStatus(ecdsData[23]);
				ecdsBankData.setMeetIncomeCode(ecdsData[24]);
				ecdsBankData.setContinueRowNum(ecdsData[25]);
				ecdsBankData.setContinueMeetIncome(ecdsData[26]);
				ecdsBankData.setIfContinue(ecdsData[27]);
				ecdsBankData.setHistoryContinueCon(ecdsData[28]);
				ecdsBankData.setSttlmAccStatus(ecdsData[29]);
				ecdsBankData.setSttlmAccUpdate(ecdsData[30]);
				ecdsBankData.setSttlmAccUptime(ecdsData[31]);
				IEcdsBankDataService ecdsservice = ServiceFactory.getEcdsBankDataService();
				
				ecdsservice.addEcdsBankData(ecdsBankData);
				
				
			}
		}
	
	}

	/**
	 * 将所有属性set到实体类存入一个list中 行号数据辅文件
	 * 
	 * @param ecdsDataList
	 * @return
	 * @throws SQLException 
	 */
	public void createEcdsBankAssData(List ecdsDataList) throws SQLException {
		List ecdsBankAssDataList = new ArrayList();
		Iterator iter = ecdsDataList.iterator();
		EcdsBankAssDataDao dao = new EcdsBankAssDataDao();
		dao.truncateEcdsBankAssData();
		while (iter.hasNext()) {
			String[] ecdsData = (String[]) iter.next();
			if (ecdsData.length == 19) {
				EcdsBankAssData ecdsBankassData = new EcdsBankAssData();
				ecdsBankassData.setRowNumber(ecdsData[0]);
				ecdsBankassData.setActorType(ecdsData[1]);
				ecdsBankassData.setRowOtherCode(ecdsData[2]);
				ecdsBankassData.setSuperActor(ecdsData[3]);
				ecdsBankassData.setLocalNodeCode(ecdsData[4]);
				ecdsBankassData.setRootSuperActor(ecdsData[5]);
				ecdsBankassData.setCatePeopleCode(ecdsData[6]);
				ecdsBankassData.setCityCode(ecdsData[7]);
				ecdsBankassData.setActorFullCall(ecdsData[8]);
				ecdsBankassData.setActorForShort(ecdsData[9]);
				ecdsBankassData.setAddress(ecdsData[10]);
				ecdsBankassData.setPostEdit(ecdsData[11]);
				ecdsBankassData.setPhone(ecdsData[12]);
				ecdsBankassData.setEmail(ecdsData[13]);
				ecdsBankassData.setInureDate(ecdsData[14]);
				ecdsBankassData.setUpdateTime(ecdsData[15]);
				ecdsBankassData.setLogUpdateExpect(ecdsData[16]);
				ecdsBankassData.setState(ecdsData[17]);
				ecdsBankassData.setRemark(ecdsData[18]);
				
				dao.addEcdsBankAssData(ecdsBankassData);

			}
		}
		
	}

	/**
	 * 将所有属性set到实体类存入一个list中 业务权限数据文件
	 * 
	 * @param ecdsDataList
	 * @return
	 * @throws SQLException 
	 */
	public void createEcdsAuthListData(List ecdsDataList) throws SQLException {
		List ecdsAuthListDataList = new ArrayList();
		Iterator iter = ecdsDataList.iterator();
		EcdsAuthlistDataDao dao = new EcdsAuthlistDataDao();
		dao.truncateEcdsAuthlistData();
		while (iter.hasNext()) {
			String[] ecdsData = (String[]) iter.next();
			if (ecdsData.length == 3) {
				EcdsAuthlistData ecdsauthlistdata = new EcdsAuthlistData();
				ecdsauthlistdata.setActorRowNumber(ecdsData[0]);
				ecdsauthlistdata.setLaunchPurviewList(ecdsData[1]);
				ecdsauthlistdata.setInceptPurviewList(ecdsData[2]);
				
				dao.addEcdsAuthlistData(ecdsauthlistdata);
			}
		}
		
	}

	/**
	 * 将所有属性set到实体类存入一个list中 公共数据数据文件
	 * 
	 * @param ecdsDataList
	 * @return
	 * @throws SQLException 
	 */
	public void createEcdsCommonData(List ecdsDataList) throws SQLException {
		List ecdsCommonDataList = new ArrayList();
		Iterator iter = ecdsDataList.iterator();
		EcdsCommonDataDao dao = new EcdsCommonDataDao();
		dao.truncateEcdsCommonData();
		while (iter.hasNext()) {
			String[] ecdsData = (String[]) iter.next();
			if (ecdsData.length == 9) {
				EcdsCommonData ecdscommondata = new EcdsCommonData();
				ecdscommondata.setCommonalityDataCode(ecdsData[0]);
				ecdscommondata.setCommonalityDataName(ecdsData[1]);
				ecdscommondata.setCommonalityDataValue(ecdsData[2]);
				ecdscommondata.setCommonalityDataUpdata(ecdsData[3]);
				ecdscommondata.setCommonalityDataType(ecdsData[4]);
				ecdscommondata.setUpdateStyle(ecdsData[5]);
				ecdscommondata.setInureDate(ecdsData[6]);
				ecdscommondata.setPostscipt(ecdsData[7]);
				ecdscommondata.setUpdateDate(ecdsData[8]);
				
				dao.addEcdsCommonData(ecdscommondata);
				
			}
		}
	
	}

	/**
	 * 将所有属性set到实体类存入一个list中 接入点信息数据文件
	 * 
	 * @param ecdsDataList
	 * @return
	 * @throws SQLException 
	 */
	public void createEcdsApData(List ecdsDataList) throws SQLException {
		List ecdsAPDataList = new ArrayList();
		Iterator iter = ecdsDataList.iterator();
		EcdsApDataDao dao = new EcdsApDataDao();
		dao.truncateEcdsApData();
		while (iter.hasNext()) {
			String[] ecdsData = (String[]) iter.next();
			if (ecdsData.length == 14) {
				EcdsApData ecdsapdata = new EcdsApData();
				ecdsapdata.setMeetCode(ecdsData[0]);
				ecdsapdata.setMeetName(ecdsData[1]);
				ecdsapdata.setNonceCcpc(ecdsData[2]);
				ecdsapdata.setMeetState(ecdsData[3]);
				ecdsapdata.setInureDate(ecdsData[4]);
				ecdsapdata.setLogoutDate(ecdsData[5]);
				ecdsapdata.setUpdateTime(ecdsData[6]);
				ecdsapdata.setLatelyUpdateWork(ecdsData[7]);
				ecdsapdata.setMeetEnterState(ecdsData[8]);
				ecdsapdata.setEnterIdentify(ecdsData[9]);
				ecdsapdata.setThisMonthSetFirstTime(ecdsData[10]);
				ecdsapdata.setThisMonthSetSecond(ecdsData[11]);
				ecdsapdata.setFirstLimited(ecdsData[12]);
				ecdsapdata.setSecondLimited(ecdsData[13]);
				
				dao.addEcdsApData(ecdsapdata);
			}
		}
		
	}

	/**
	 * 将所有属性set到实体类存入一个list中 参与者承接关系数据辅文件
	 * 
	 * @param ecdsDataList
	 * @return
	 * @throws SQLException 
	 */
	public void createEcdsPsAssData(List ecdsDataList) throws SQLException {
		List ecdsPsAssDataList = new ArrayList();
		Iterator iter = ecdsDataList.iterator();
		EcdsPsAssDataDao dao = new EcdsPsAssDataDao();
		dao.truncateEcdsPsAssData();
		while (iter.hasNext()) {
			String[] ecdsData = (String[]) iter.next();
			if (ecdsData.length == 12) {
				EcdsPsAssData ecdspsassdata = new EcdsPsAssData();
				ecdspsassdata.setByContinueBankNo(ecdsData[0]);
				ecdspsassdata.setByContinueOrgCode(ecdsData[1]);
				ecdspsassdata.setByContinueRole(ecdsData[2]);
				ecdspsassdata.setMsgId(ecdsData[3]);
				ecdspsassdata.setContinueBankNo(ecdsData[4]);
				ecdspsassdata.setContinueOrgCode(ecdsData[5]);
				ecdspsassdata.setContinueRole(ecdsData[6]);
				ecdspsassdata.setInureDate(ecdsData[7]);
				ecdspsassdata.setByContinueLaunch(ecdsData[8]);
				ecdspsassdata.setContinueLaunch(ecdsData[9]);
				ecdspsassdata.setLaunchTime(ecdsData[10]);
				ecdspsassdata.setUpdateState(ecdsData[11]);
				
				dao.addEcdsPsAssData(ecdspsassdata);
			}
		}
		
	}

	/**
	 * 将所有属性set到实体类存入一个list中 参与者与接入点关系
	 * 
	 * @param ecdsDataList
	 * @return
	 * @throws SQLException 
	 */
	public void createEcdsPsapAssData(List ecdsDataList) throws SQLException {
		List ecdsPsapAssDataList = new ArrayList();
		Iterator iter = ecdsDataList.iterator();
		EcdsPsapAssDataDao dao = new EcdsPsapAssDataDao();
		dao.truncateEcdsPsapAssData();
		while (iter.hasNext()) {
			String[] ecdsData = (String[]) iter.next();
			if (ecdsData.length == 6) {
				EcdsPsapAssData ecdspsapassdata = new EcdsPsapAssData();
				ecdspsapassdata.setBankNo(ecdsData[0]);
				ecdspsapassdata.setMeetCode(ecdsData[1]);
				ecdspsapassdata.setEffectiveDate(ecdsData[2]);
				ecdspsapassdata.setUpdateDate(ecdsData[3]);
				ecdspsapassdata.setProcessState(ecdsData[4]);
				ecdspsapassdata.setRemark(ecdsData[5]);
				
				dao.addEcdsPsapAssData(ecdspsapassdata);
			}
		}
		
	}
}
