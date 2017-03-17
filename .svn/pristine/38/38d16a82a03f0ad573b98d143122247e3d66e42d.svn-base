/********************************************
 * 文件名称: ITransPubService.java
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

package com.herongtech.console.service.transpub;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.herongtech.commons.tools.BeanUtil;
import com.herongtech.commons.tools.DateUtil;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.constant.IErrorNo;
import com.herongtech.console.core.util.BillUtils;
import com.herongtech.console.core.util.XmlBeanUtil;
import com.herongtech.console.domain.bean.EcdsBillBean;
import com.herongtech.console.domain.bean.TransPub;
import com.herongtech.console.service.interfaces.ITransPubService;
import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.online.trans.acpt.trans201001.Var201001;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.domain.bean.RgctMethod;
import com.herongtech.rc.domain.bean.RgctStatus;
import com.herongtech.rc.domain.dao.RgctStatusDao;
import com.herongtech.rc.service.RcServiceFactory;

public class TransPubService implements ITransPubService {
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20160615 @describe ";
	
	//请求信息转成公共对象
	public TransPub getTransPub(Context context) throws BizAppException {
		TransPub transPub = new TransPub();
		String request = (String)ContextUtil.getRequestData(context);
		//公共结构对象存放请求报文信息
		Map<String, Class> clazzMap = new HashMap<String, Class>();
		clazzMap.put("Document", EcdsBillBean.class);
	    EcdsBillBean ecdsBillBean=(EcdsBillBean)XmlBeanUtil.xml2Bean(clazzMap, request);
	    transPub.setEcdsBillBean(ecdsBillBean);
		return transPub;
		
	}
	
	//请求信息转成公共对象
	public void ecdsBill2BillInfo(EcdsBillBean bean, RgctBillInfo info) throws BizAppException {
		try {
			BeanUtil.Bean2Bean(bean, info);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizAppException(IErrorNo.ERR_PACKAGE, e.getMessage());
		}
			
	}
	
	/**计算EBSNO
	 * 根据票面六要素: 票号、出票日、票据种类、承兑行行号/付款行行号、到期日、票面金额 计算EBSNO
	 * @param newBillInfo
	 * @return
	 */
	public String generateEBSNO(RgctBillInfo billInfo) {
		String  ebsNo = BillUtils.generateEbsNo(billInfo.getBillNo(), 
					DateUtil.dateTo8(billInfo.getIssueDt()), billInfo
					.getDraweeBankNo(), IDict.K_BILL_CLASS.K_ELEC_BILL.equals(billInfo
					.getBillClass()), IDict.K_BILL_TYPE.K_BANK_BILL.equals(billInfo
					.getBillType()));
		return ebsNo;
	}
	
	//设置登记中心
	public void setBillHistStatus(RgctBillHist hist) throws BizAppException {
		//方法
		RgctMethod method = RcServiceFactory.getRgctMethodService().getRgctMethod(
						IConstants.BA.BA, IConstants.BA.IRcRegBillService, "regBill");
		RgctStatusDao dao=new RgctStatusDao();
		try {
			List<RgctStatus> list=dao.getRgctStatusList(method.getId(), null);
			RgctStatus rgctStatus =list.get(0);
			String curStatus = rgctStatus.getAfterStatus();
			hist.setCurStatus(curStatus);
			hist.setStepName(method.getMethodCnName());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}
	
	
	//设置登记中心
	public String getBillhistStatusSql(RgctMethod method, boolean bDelFlag) throws BizAppException {
		IDB db = DBFactory.getDB();
		
		try {
			List<RgctStatus> list = db.getObjectList("select * from trgct_status where  method_id= ?", RgctStatus.class, method.getId());
			List<String> sList = new ArrayList<String>();
			
			for (int i = 0; i < list.size(); i++) {
				RgctStatus status = (RgctStatus) list.get(i);
				String qStatus = status.getBeforeStatus();
				String runStatus = status.getRunStatus();
				if (qStatus != null && qStatus.length() > 0) {
					StringBuffer sb = new StringBuffer();
					sb.append("billhist.cur_status='");
					sb.append(qStatus);
					sb.append("'");

					sList.add(sb.toString());
				}
				if (runStatus != null && runStatus.length() > 0) {
					StringBuffer sb = new StringBuffer();
					sb.append("billhist.run_status='");
					sb.append(runStatus);
					sb.append("'");

					sList.add(sb.toString());
				}
			}

			StringBuffer sb = new StringBuffer();
			if (sList.size() > 0) {
				sb.append(" and ( ");
				for (int i = 0; i < sList.size(); i++) {
					if (i > 0) {
						sb.append(" or ");
					}
					sb.append(sList.get(i));
				}
				sb.append(" ) ");
			}

			if (!bDelFlag) {
				sb.append(" and ");
				sb.append("billinfo.del_flag='");
				sb.append(IDict.K_YORN.K_YORN_NO);
				sb.append("' ");
			}

			return sb.toString();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return "";
	}
}
