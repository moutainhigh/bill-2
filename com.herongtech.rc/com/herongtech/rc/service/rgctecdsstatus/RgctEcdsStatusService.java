/********************************************
 * 文件名称: RgctEcdsStatusService.java
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
package com.herongtech.rc.service.rgctecdsstatus;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.DraftMapping;
import com.herongtech.rc.domain.bean.EcdsAuthlistData;
import com.herongtech.rc.domain.bean.RgctEcdsStatus;
import com.herongtech.rc.domain.dao.EcdsAuthlistDataDao;
import com.herongtech.rc.domain.dao.RgctEcdsStatusDao;
import com.herongtech.rc.service.interfaces.IRgctEcdsStatusService;
import com.herongtech.sysconstant.ISysErrorNo;

public class RgctEcdsStatusService implements IRgctEcdsStatusService {
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	
	/**
     * 获取ECDS当前时序状态
     * @return  ECDS当前时序状态
     */
    public String getCurEcdsStatus() {
        RgctEcdsStatusDao ecdsStatusDao=new RgctEcdsStatusDao();
        
        RgctEcdsStatus status=null;
        try {
            status = ecdsStatusDao.getRgctEcdsStatus(DraftConstants.ECDS_SEQUENCE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (status==null){
            return null;
        }else{
            return status.getPvalue();
        }
    }

    /**
     * 获取ECDS当前参数值
     * @return  参数
     */
    public String getEcdsPValueByPName(String pName) {
        RgctEcdsStatusDao ecdsStatusDao=new RgctEcdsStatusDao();
        
        RgctEcdsStatus status=null;
        try {
            status = ecdsStatusDao.getRgctEcdsStatus(pName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (status==null){
            return null;
        }else{
            return status.getPvalue();
        }
    }
    

    /**
     * 获取ECDS当前参数值
     * @return  参数
     */
    public String getEcdsDateYYYYMMDD(String pName) {
        RgctEcdsStatusDao ecdsStatusDao=new RgctEcdsStatusDao();
        String ecdsDate="";
        try {
            ecdsDate = ecdsStatusDao.getRgctEcdsStatus(pName).getPvalue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ecdsDate.replace("-", "");
    }

    /**
     * 判断参与者是否已登录到ECDS系统
     */
    public boolean isLogon() {
        boolean result = false;     
        String logStatus = getEcdsPValueByPName(DraftConstants.ECDS_LOGON_STATUS);        
        if (logStatus != null && logStatus.equals(DraftConstants.ECDS_LOGIN)) {
            result = true;
        }
        return result;
    }

    /**
     * 根据某种类型报文，判断ECDS当前是否可以处理该报文
     */
    public boolean isInWorkSequence(DraftMapping draftMapping) {
        boolean result = false;
        String sequence = getEcdsPValueByPName(DraftConstants.ECDS_SEQUENCE);
        if (draftMapping.getAllowSts().contains(sequence)) {
            result = true;
        }
        return result;
    }

    /**
     * 根据参于者行号,判断是否拥有某种类型报文的权限
     */
    public boolean isHaveAuth(String actorRowNumber, String msgType) {
        EcdsAuthlistDataDao ecdsAuthListDataDAO=new EcdsAuthlistDataDao();
        boolean result = false;
        EcdsAuthlistData ecdsAuthlistData =null;
        try {
            ecdsAuthlistData = ecdsAuthListDataDAO.getEcdsAuthlistData(actorRowNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (ecdsAuthlistData != null) {
            String launch = ecdsAuthlistData.getLaunchPurviewList();
            if (launch.contains(msgType)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * 判断当前ECDS状态是否能发送相应的报文
     * 
     * @param actorRowNumber
     * @param draftMapping
     * @throws BizAppException 
     */
    public void isCanSent(String actorRowNumber, DraftMapping draftMapping) throws BizAppException {
        String draftNo = draftMapping.getDraftNo();
        if (draftNo.equals("051")) {
            return;
        }
        if (!isLogon()) {
            throw new BizAppException("没有登录到ECDS系统.");
        } else if (!isInWorkSequence(draftMapping)) {
            //initMap();//初始化ecds状态
            if (!isInWorkSequence(draftMapping)){
                throw new BizAppException("当前ECDS系统不接收现在的业务.");
            }
        } else if(StringUtils.isBlank(actorRowNumber)){
                throw new BizAppException(draftMapping.getDraftNo()+"报文发起行号为空.");
        } else if (!isHaveAuth(actorRowNumber, draftNo)) {
            throw new BizAppException("没有发送此业务的权限.");
        }
    }
	
	
	
	
	/**
	 * 取系统参数信息
	 * 
	 * @param bankNo 参数标识
	 */
	public  RgctEcdsStatus getRgctEcdsStatus(String pname) throws BizAppException{
		RgctEcdsStatusDao dao=new RgctEcdsStatusDao();
		try {
			return dao.getRgctEcdsStatus(pname);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}
	
	/**
	 * 插入参数表
	 *
	 */
	public void addRgctEcdsStatus(RgctEcdsStatus RgctEcdsStatus)throws BizAppException{
		RgctEcdsStatusDao dao=new RgctEcdsStatusDao();
		try {
			if (dao.addRgctEcdsStatus(RgctEcdsStatus) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加RgctEcdsStatus失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
	}
	
	/**
	 * 修改参数表
	 */
	
	public void modifyRgctEcdsStatus(RgctEcdsStatus RgctEcdsStatus)throws BizAppException{
		RgctEcdsStatusDao dao=new RgctEcdsStatusDao();
		try {
			if (dao.modifyRgctEcdsStatus(RgctEcdsStatus) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改RgctEcdsStatus失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
	}
	
	/**
	 * 插入或者修改参数表
	 *
	 */
	public void addOrUpdateRgctEcdsStatus(RgctEcdsStatus RgctEcdsStatus)throws BizAppException{
		RgctEcdsStatusDao dao=new RgctEcdsStatusDao();
		try {
			//记录存在 
			if (dao.getRgctEcdsStatus(RgctEcdsStatus.getPname()) != null){
				 dao.modifyRgctEcdsStatus(RgctEcdsStatus);
			 } else{
				 dao.addRgctEcdsStatus(RgctEcdsStatus);
			 }
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
		
	}
	
	/**
	 * 批量插入或者修改参数表
	 *
	 */
	public void addOrUpdateRgctEcdsStatusList(List<RgctEcdsStatus> list)throws BizAppException{
		for (RgctEcdsStatus item : list){
			addOrUpdateRgctEcdsStatus(item);
		}
	}
	
	/**
	 * 
	 *
	 */
	public void dealSequenceBefore()throws BizAppException{
		
	}
	/**
	 * 
	 *
	 */
	public void dealSequenceRun()throws BizAppException{
		
	}
		
	/**
	 * 
	 *
	 */
	public void dealSequenceAfter()throws BizAppException{
		
	}
		
	/**
	 * 
	 *
	 */
	public void dealSequenceLast()throws BizAppException{
		
	}
		
}
