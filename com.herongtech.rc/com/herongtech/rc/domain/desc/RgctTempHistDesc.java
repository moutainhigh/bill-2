/********************************************
* 文件名称: RgctTempHistDesc.java
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
public class RgctTempHistDesc implements IBeanDesc {
	private Map<String,FieldDesc> fieldDescMap=new HashMap<String,FieldDesc>();
	public RgctTempHistDesc(){
FieldDesc  fieldDesc=null;
 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("histId");
 fieldDesc.setType("String");
 fieldDesc.setLength(30);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("rgctId");
 fieldDesc.setType("String");
 fieldDesc.setLength(30);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("curStatus");
 fieldDesc.setType("String");
 fieldDesc.setLength(70);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("preStatus");
 fieldDesc.setType("String");
 fieldDesc.setLength(70);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("runStatus");
 fieldDesc.setType("String");
 fieldDesc.setLength(70);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("batchId");
 fieldDesc.setType("String");
 fieldDesc.setLength(50);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("fromName");
 fieldDesc.setType("String");
 fieldDesc.setLength(300);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("fromCustNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(50);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("fromAcctNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(50);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("fromBankNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(12);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("fromBrchId");
 fieldDesc.setType("String");
 fieldDesc.setLength(50);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("fromSign");
 fieldDesc.setType("String");
 fieldDesc.setLength(6000);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("toName");
 fieldDesc.setType("String");
 fieldDesc.setLength(300);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("toCustNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(50);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("toAcctNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(50);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("toBankNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(12);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("toBrchId");
 fieldDesc.setType("String");
 fieldDesc.setLength(50);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("signDt");
 fieldDesc.setType("String");
 fieldDesc.setLength(20);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("signTime");
 fieldDesc.setType("String");
 fieldDesc.setLength(20);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("interestRate");
 fieldDesc.setType("double");
 fieldDesc.setLength(10);
 fieldDesc.setScale(6);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("interest");
 fieldDesc.setType("double");
 fieldDesc.setLength(20);
 fieldDesc.setScale(2);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("dealMoney");
 fieldDesc.setType("double");
 fieldDesc.setLength(20);
 fieldDesc.setScale(2);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("isRegress");
 fieldDesc.setType("String");
 fieldDesc.setLength(1);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("regressDt");
 fieldDesc.setType("String");
 fieldDesc.setLength(20);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("regressTime");
 fieldDesc.setType("String");
 fieldDesc.setLength(20);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("regressPrice");
 fieldDesc.setType("double");
 fieldDesc.setLength(20);
 fieldDesc.setScale(2);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("backOpenDt");
 fieldDesc.setType("String");
 fieldDesc.setLength(20);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("backEndDt");
 fieldDesc.setType("String");
 fieldDesc.setLength(20);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("backRate");
 fieldDesc.setType("double");
 fieldDesc.setLength(10);
 fieldDesc.setScale(6);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("backAmount");
 fieldDesc.setType("double");
 fieldDesc.setLength(20);
 fieldDesc.setScale(6);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("isInner");
 fieldDesc.setType("String");
 fieldDesc.setLength(1);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("isBuy");
 fieldDesc.setType("String");
 fieldDesc.setLength(1);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("signer");
 fieldDesc.setType("String");
 fieldDesc.setLength(300);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("signerSign");
 fieldDesc.setType("String");
 fieldDesc.setLength(6000);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("signFlag");
 fieldDesc.setType("String");
 fieldDesc.setLength(1);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("holdCustNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(50);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("holdCustName");
 fieldDesc.setType("String");
 fieldDesc.setLength(300);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("holdAcctNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(50);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("holdBankNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(50);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("obligeeCustNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(300);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("obligeeCustName");
 fieldDesc.setType("String");
 fieldDesc.setLength(300);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("obligeeBankNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(12);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("obligeeAcctNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(50);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("returnCode");
 fieldDesc.setType("String");
 fieldDesc.setLength(70);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("operDate");
 fieldDesc.setType("String");
 fieldDesc.setLength(20);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("operTime");
 fieldDesc.setType("String");
 fieldDesc.setLength(20);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("operNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(50);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("channel");
 fieldDesc.setType("String");
 fieldDesc.setLength(1);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("isLock");
 fieldDesc.setType("String");
 fieldDesc.setLength(1);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("lockBranchNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(50);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("lockFlowName");
 fieldDesc.setType("String");
 fieldDesc.setLength(100);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("stepName");
 fieldDesc.setType("String");
 fieldDesc.setLength(100);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("validHistId");
 fieldDesc.setType("String");
 fieldDesc.setLength(0);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("lastHistId");
 fieldDesc.setType("String");
 fieldDesc.setLength(0);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("isOnline");
 fieldDesc.setType("String");
 fieldDesc.setLength(1);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("overdueRs");
 fieldDesc.setType("String");
 fieldDesc.setLength(500);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("isAccpt");
 fieldDesc.setType("String");
 fieldDesc.setLength(1);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("draftLogId");
 fieldDesc.setType("String");
 fieldDesc.setLength(0);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("rejectCode");
 fieldDesc.setType("String");
 fieldDesc.setLength(70);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("rejectReason");
 fieldDesc.setType("String");
 fieldDesc.setLength(500);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("isRediscCenter");
 fieldDesc.setType("String");
 fieldDesc.setLength(1);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("assuId");
 fieldDesc.setType("String");
 fieldDesc.setLength(0);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("recourseId");
 fieldDesc.setType("String");
 fieldDesc.setLength(0);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("isDelegate");
 fieldDesc.setType("String");
 fieldDesc.setLength(1);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("inAcctNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(50);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("inBankNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(12);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("partnerCode");
 fieldDesc.setType("String");
 fieldDesc.setLength(12);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("payTradeNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(50);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("forbidFlag");
 fieldDesc.setType("String");
 fieldDesc.setLength(1);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("fromRemark");
 fieldDesc.setType("String");
 fieldDesc.setLength(500);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("toRemark");
 fieldDesc.setType("String");
 fieldDesc.setLength(500);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("draftInfo");
 fieldDesc.setType("String");
 fieldDesc.setLength(500);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("oldInterestRate");
 fieldDesc.setType("double");
 fieldDesc.setLength(10);
 fieldDesc.setScale(6);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("oldDisDt");
 fieldDesc.setType("String");
 fieldDesc.setLength(20);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("delayDays");
 fieldDesc.setType("String");
 fieldDesc.setLength(4);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("delayType");
 fieldDesc.setType("String");
 fieldDesc.setLength(1);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("billTrack");
 fieldDesc.setType("String");
 fieldDesc.setLength(20);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("isSameCity");
 fieldDesc.setType("String");
 fieldDesc.setLength(1);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("interestDueDt");
 fieldDesc.setType("String");
 fieldDesc.setLength(20);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("interestDays");
 fieldDesc.setType("Long");
 fieldDesc.setLength(0);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("firstbuyInterest");
 fieldDesc.setType("double");
 fieldDesc.setLength(20);
 fieldDesc.setScale(2);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("fromOrgcode");
 fieldDesc.setType("String");
 fieldDesc.setLength(50);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("toOrgcode");
 fieldDesc.setType("String");
 fieldDesc.setLength(50);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("fromRole");
 fieldDesc.setType("String");
 fieldDesc.setLength(30);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("toRole");
 fieldDesc.setType("String");
 fieldDesc.setLength(30);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("billBeforeOwner");
 fieldDesc.setType("String");
 fieldDesc.setLength(200);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("prodAttr");
 fieldDesc.setType("String");
 fieldDesc.setLength(120);
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
 fieldDesc.setBeanFileName("buyType");
 fieldDesc.setType("String");
 fieldDesc.setLength(2);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("workingadsNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(10);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("workingadsName");
 fieldDesc.setType("String");
 fieldDesc.setLength(300);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("storageBranchName");
 fieldDesc.setType("String");
 fieldDesc.setLength(300);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("endoHistId");
 fieldDesc.setType("String");
 fieldDesc.setLength(0);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("endorsesBankName");
 fieldDesc.setType("String");
 fieldDesc.setLength(180);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("endorsesBankNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(16);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("endorseDt");
 fieldDesc.setType("String");
 fieldDesc.setLength(20);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("endoStatus");
 fieldDesc.setType("String");
 fieldDesc.setLength(20);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("comesFrom");
 fieldDesc.setType("String");
 fieldDesc.setLength(20);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("acctBrchNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(20);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("storageBrchNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(20);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);



}
public FieldDesc getFieldDesc(String beanFieldName){
   return fieldDescMap.get(beanFieldName);
}

}
