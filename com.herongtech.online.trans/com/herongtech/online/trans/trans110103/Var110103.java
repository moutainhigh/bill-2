/********************************************
 * 文件名称: Var100002.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: yanjl
 * 开发时间: 20160716
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.online.trans.trans110103;

import com.herongtech.console.domain.bean.TransPub;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;


/**
 * 客户信息查询
 * @author Administrator
 *
 */
public class Var110103 {
	public static final String  HUNDSUN_VERSION="@system  票据管理平台  @version 2.0.0.1  @lastModiDate @describe ";
	
	//公共对象
	TransPub transPub = null;
	
	public void setTransPub(TransPub transPub){
		this.transPub = transPub;
	}
	
	public TransPub getTransPub(){
		return transPub;
	}
/**删除返回的结果集*/
	private int[] result;

public int[] getResult() {
	return result;
}

public void setResult(int[] result) {
	this.result = result;
}
	/**请求过来的id数组*/
	private String[] requestid;

	public String[] getRequestid() {
		return requestid;
	}

	public void setRequestid(String[] requestid) {
		this.requestid = requestid;
	}
	//返回记录数
	private int errNum;

	public int getErrNum() {
		return errNum;
	}

	public void setErrNum(int errNum) {
		this.errNum = errNum;
	}
	private RcBaseSearchBean rcBaseSearchBean;

	public RcBaseSearchBean getRcBaseSearchBean() {
		return rcBaseSearchBean;
	}

	public void setRcBaseSearchBean(RcBaseSearchBean rcBaseSearchBean) {
		this.rcBaseSearchBean = rcBaseSearchBean;
	}

	
	
}
