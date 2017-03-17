/********************************************
* 文件名称: EcdsPsAssDataDesc.java
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
public class EcdsPsAssDataDesc implements IBeanDesc {
	private Map<String,FieldDesc> fieldDescMap=new HashMap<String,FieldDesc>();
	public EcdsPsAssDataDesc(){
FieldDesc  fieldDesc=null;
 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("byContinueBankNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(36);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("byContinueOrgCode");
 fieldDesc.setType("String");
 fieldDesc.setLength(60);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("byContinueRole");
 fieldDesc.setType("String");
 fieldDesc.setLength(12);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("msgId");
 fieldDesc.setType("String");
 fieldDesc.setLength(84);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("continueBankNo");
 fieldDesc.setType("String");
 fieldDesc.setLength(36);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("continueOrgCode");
 fieldDesc.setType("String");
 fieldDesc.setLength(60);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("continueRole");
 fieldDesc.setType("String");
 fieldDesc.setLength(12);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("inureDate");
 fieldDesc.setType("String");
 fieldDesc.setLength(30);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("byContinueLaunch");
 fieldDesc.setType("String");
 fieldDesc.setLength(6);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("continueLaunch");
 fieldDesc.setType("String");
 fieldDesc.setLength(6);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("launchTime");
 fieldDesc.setType("String");
 fieldDesc.setLength(78);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);


 fieldDesc=new FieldDesc();
 fieldDesc.setBeanFileName("updateState");
 fieldDesc.setType("String");
 fieldDesc.setLength(6);
 fieldDesc.setScale(0);
 fieldDescMap.put(fieldDesc.getBeanFileName(),fieldDesc);



}
public FieldDesc getFieldDesc(String beanFieldName){
   return fieldDescMap.get(beanFieldName);
}

}
