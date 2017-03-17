/********************************************
* 文件名称: FacCreateFlowDesc.java
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
package com.herongtech.console.domain.common.desc;

import java.util.HashMap;
import java.util.Map;
import com.herongtech.domain.FieldDesc;
import com.herongtech.domain.IBeanDesc;
public class FacCreateFlowDesc implements IBeanDesc {
	private Map<String,FieldDesc> fieldDescMap=new HashMap<String,FieldDesc>();
	public FacCreateFlowDesc(){
FieldDesc  fieldDesc=null;
 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("id");
 fieldDesc.setType("Long");
 fieldDesc.setLength(0);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("billNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(40);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("rgctId");
 fieldDesc.setType("Long");
 fieldDesc.setLength(0);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("source");
 fieldDesc.setType("String");
 fieldDesc.setLength(20);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("remitterBankNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(20);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("loanRequisition");
 fieldDesc.setType("String");
 fieldDesc.setLength(200);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("billMoney");
 fieldDesc.setType("double");
 fieldDesc.setLength(26);
 fieldDesc.setScale(2);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("billType");
 fieldDesc.setType("String");
 fieldDesc.setLength(20);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("billClass");
 fieldDesc.setType("String");
 fieldDesc.setLength(20);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("aimBranchNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(20);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("createDate");
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
 fieldDesc.setBeanFileName("status");
 fieldDesc.setType("String");
 fieldDesc.setLength(2);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("billId");
 fieldDesc.setType("Long");
 fieldDesc.setLength(0);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("facType");
 fieldDesc.setType("String");
 fieldDesc.setLength(10);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("facilityCode");
 fieldDesc.setType("String");
 fieldDesc.setLength(25);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("custNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(12);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("acceptor");
 fieldDesc.setType("String");
 fieldDesc.setLength(200);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("dueDt");
 fieldDesc.setType("String");
 fieldDesc.setLength(20);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("productId");
 fieldDesc.setType("String");
 fieldDesc.setLength(35);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("ownerPartyId");
 fieldDesc.setType("String");
 fieldDesc.setLength(20);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("isCyc");
 fieldDesc.setType("String");
 fieldDesc.setLength(10);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("branchNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(12);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("releaseDate");
 fieldDesc.setType("String");
 fieldDesc.setLength(20);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("releaseTime");
 fieldDesc.setType("String");
 fieldDesc.setLength(20);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);



}
public FieldDesc getFieldDesc(String beanFieldName){
   return fieldDescMap.get(beanFieldName);
}

}
