/********************************************
* 文件名称: RgctBillInfoDesc.java
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
package com.herongtech.rc.domain.desc;

import java.util.HashMap;
import java.util.Map;

import com.herongtech.domain.FieldDesc;
import com.herongtech.domain.IBeanDesc;
public class RgctBillInfoDesc implements IBeanDesc {
	private Map<String,FieldDesc> fieldDescMap=new HashMap<String,FieldDesc>();
	public RgctBillInfoDesc(){
FieldDesc  fieldDesc=null;
 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("id");
 fieldDesc.setType("String");
 fieldDesc.setLength(30);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);

 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("reqDraftId");
 fieldDesc.setType("String");
 fieldDesc.setLength(50);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);
 
 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("respDraftId");
 fieldDesc.setType("String");
 fieldDesc.setLength(50);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);fieldDesc=new FieldDesc();
 
 fieldDesc.setBeanFileName("histId");
 fieldDesc.setType("String");
 fieldDesc.setLength(30);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);

 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("tempHistId");
 fieldDesc.setType("String");
 fieldDesc.setLength(0);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("ebsNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(50);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("billClass");
 fieldDesc.setType("String");
 fieldDesc.setLength(70);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("billType");
 fieldDesc.setType("String");
 fieldDesc.setLength(70);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("billNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(30);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("issueDt");
 fieldDesc.setType("String");
 fieldDesc.setLength(20);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("isAccpt");
 fieldDesc.setType("String");
 fieldDesc.setLength(1);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("remitter");
 fieldDesc.setType("String");
 fieldDesc.setLength(300);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("remitterCustNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(50);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("remitterAcct");
 fieldDesc.setType("String");
 fieldDesc.setLength(50);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("remitterSign");
 fieldDesc.setType("String");
 fieldDesc.setLength(2000);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("remitterBankNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(50);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("remitterBankName");
 fieldDesc.setType("String");
 fieldDesc.setLength(300);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("draweeBankName");
 fieldDesc.setType("String");
 fieldDesc.setLength(300);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("draweeBankNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(30);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("draweeBranchNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(50);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("draweeAddr");
 fieldDesc.setType("String");
 fieldDesc.setLength(200);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("payeeName");
 fieldDesc.setType("String");
 fieldDesc.setLength(300);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("payeeAcct");
 fieldDesc.setType("String");
 fieldDesc.setLength(50);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("payeeBankNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(30);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("payeeBankName");
 fieldDesc.setType("String");
 fieldDesc.setLength(300);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("acceptor");
 fieldDesc.setType("String");
 fieldDesc.setLength(200);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("acceptorCustNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(50);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("acceptorAcct");
 fieldDesc.setType("String");
 fieldDesc.setLength(50);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("acceptorBankNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(30);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("acceptorBankName");
 fieldDesc.setType("String");
 fieldDesc.setLength(300);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("acceptorSign");
 fieldDesc.setType("String");
 fieldDesc.setLength(2000);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("assureBankName");
 fieldDesc.setType("String");
 fieldDesc.setLength(300);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("assureBankNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(50);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("letterNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(300);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("assureSelf");
 fieldDesc.setType("String");
 fieldDesc.setLength(1);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("billSource");
 fieldDesc.setType("String");
 fieldDesc.setLength(1);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("billMoney");
 fieldDesc.setType("double");
 fieldDesc.setLength(20);
 fieldDesc.setScale(2);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("dueDt");
 fieldDesc.setType("String");
 fieldDesc.setLength(20);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("conferNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(90);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("infoForbidFlag");
 fieldDesc.setType("String");
 fieldDesc.setLength(70);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("deductFlag");
 fieldDesc.setType("String");
 fieldDesc.setLength(70);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("isAcptAcct");
 fieldDesc.setType("String");
 fieldDesc.setLength(1);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("billUsage");
 fieldDesc.setType("String");
 fieldDesc.setLength(100);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("remark");
 fieldDesc.setType("String");
 fieldDesc.setLength(200);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("cancelReason");
 fieldDesc.setType("String");
 fieldDesc.setLength(200);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("cancelRemark");
 fieldDesc.setType("String");
 fieldDesc.setLength(500);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("createDt");
 fieldDesc.setType("String");
 fieldDesc.setLength(20);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("createTime");
 fieldDesc.setType("String");
 fieldDesc.setLength(20);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("delFlag");
 fieldDesc.setType("String");
 fieldDesc.setLength(1);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("invoiceNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(90);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("remitterRemark");
 fieldDesc.setType("String");
 fieldDesc.setLength(500);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);



 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("drwrCreditrating");
 fieldDesc.setType("String");
 fieldDesc.setLength(50);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("drwrCreditratingAgency");
 fieldDesc.setType("String");
 fieldDesc.setLength(500);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("drwrCreditratingDuedt");
 fieldDesc.setType("String");
 fieldDesc.setLength(50);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("acptrCreditrating");
 fieldDesc.setType("String");
 fieldDesc.setLength(50);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("acptrCreditratingAgency");
 fieldDesc.setType("String");
 fieldDesc.setLength(50);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("acptrCreditratingDuedt");
 fieldDesc.setType("String");
 fieldDesc.setLength(50);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("busiDeep");
 fieldDesc.setType("Long");
 fieldDesc.setLength(0);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("acceptorDate");
 fieldDesc.setType("String");
 fieldDesc.setLength(20);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("recourseFlag");
 fieldDesc.setType("String");
 fieldDesc.setLength(10);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("remitterRole");
 fieldDesc.setType("String");
 fieldDesc.setLength(30);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("remitterOrgCode");
 fieldDesc.setType("String");
 fieldDesc.setLength(50);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("acceptorOrgCode");
 fieldDesc.setType("String");
 fieldDesc.setLength(10);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("payeeOrgCode");
 fieldDesc.setType("String");
 fieldDesc.setLength(10);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("curStatus");
 fieldDesc.setType("String");
 fieldDesc.setLength(70);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("acctBranchNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(12);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("storageBranchNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(12);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);





 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("acceptorRole");
 fieldDesc.setType("String");
 fieldDesc.setLength(10);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


}
public FieldDesc getFieldDesc(String beanFieldName){
   return fieldDescMap.get(beanFieldName);
}

}
