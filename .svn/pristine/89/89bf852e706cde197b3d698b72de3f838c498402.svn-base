/********************************************
 * 文件名称: IRgctDraftLogService.java
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
package com.herongtech.rc.service.rcgtdraftlog;

import java.sql.SQLException;

import com.herongtech.rc.domain.bean.RgctDraftLog;
import com.herongtech.rc.domain.dao.RgctDraftLogDao;
import com.herongtech.rc.service.interfaces.IRgctDraftLogService;
import com.herongtech.sysconstant.ISysErrorNo;
import com.herongtech.exception.impl.BizAppException;

public class RgctDraftLogService implements  IRgctDraftLogService{
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 取ecds交易行信息参数信息
	 */
	public  RgctDraftLog getRgctDraftLog(String logId) throws BizAppException {
		RgctDraftLogDao dao = new RgctDraftLogDao();
		try {
			return dao.getRgctDraftLog(logId);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}
	
	/**
	 * 插入参数表
	 *
	 */
	public void addRgctDraftLog(RgctDraftLog RgctDraftLog)throws BizAppException{
		RgctDraftLogDao dao=new RgctDraftLogDao();
		try {
			if (dao.addRgctDraftLog(RgctDraftLog) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加RgctDraftLog失败");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改参数表
	 */
	
	public void modifyRgctDraftLog(RgctDraftLog RgctDraftLog)throws BizAppException {
		
		RgctDraftLogDao dao=new RgctDraftLogDao();
		try {
			if (dao.modifyRgctDraftLog(RgctDraftLog) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加RgctDraftLog失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
	}
	public RgctDraftLog getrespDraftByReqSid(String reqSid)throws BizAppException{
	    RgctDraftLogDao dao = new RgctDraftLogDao();
        try {
            return dao.getrespDraftByReqSid(reqSid);
        }catch (SQLException e) {
            throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
        }
	}
	
	/**
	 * 取ecds交易行信息参数信息
	 */
	public  RgctDraftLog getRgctDraftLogByReqSid(String reqSid) throws BizAppException {
		RgctDraftLogDao dao = new RgctDraftLogDao();
		try {
			return dao.getRgctDraftLogByReqSid(reqSid);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}
	
	public  RgctDraftLog getDraftLogByReqMsgId(String reqMsgId) throws BizAppException {
        RgctDraftLogDao dao = new RgctDraftLogDao();
        try {
            return dao.getDraftLogByReqMsgId(reqMsgId);
        }catch (SQLException e) {
            throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
        }
    }
	
	/**
	 * 取ecds交易行信息参数信息
	 */
	public  RgctDraftLog getSignDraftLogByReqMsgId(String reqMsgId) throws BizAppException {
		RgctDraftLogDao dao = new RgctDraftLogDao();
		try {
			return dao.getSignDraftLogByReqMsgId(reqMsgId);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}

    @Override
    public RgctDraftLog getDraftLogByRespSid(String respSid)
            throws BizAppException {
        RgctDraftLogDao dao = new RgctDraftLogDao();
        try {
            return dao.getDraftLogByRespSid(respSid);
        }catch (SQLException e) {
            throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
        }
    }

    @Override
    public RgctDraftLog getDraftLogByReqSid(String reqSid) throws Exception {
        RgctDraftLogDao dao = new RgctDraftLogDao();
        try {
            return dao.getRgctDraftLogByReqSid(reqSid);
        }catch (SQLException e) {
            throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
        }
    }
}
