package com.herongtech.console.core.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.herongtech.online.trans.trans101101.Var101101InfoBean;
import com.herongtech.online.trans.trans101101.Var101101Result;
import com.herongtech.xmlchannel.pkg.ProResult;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.mapper.MapperWrapper;


public final class XmlBeanUtil {

    /**
     * 将Bean转换为XML
     *
     * @param clazzMap 别名-类名映射Map
     * @param bean     要转换为xml的bean对象
     * @return XML字符串
     */
    public static String bean2xml(Map<String, Class> clazzMap, Object bean) {
        XStream xstream = new XStream();
        for (Iterator it = clazzMap.entrySet().iterator(); it.hasNext();) {
            Map.Entry<String, Class> m = (Map.Entry<String, Class>) it.next();
            xstream.alias(m.getKey(), m.getValue());
        }
        String xml = xstream.toXML(bean);
        return xml;
    }

    /**
     * 将XML转换为Bean
     *
     * @param clazzMap 别名-类名映射Map
     * @param xml      要转换为bean对象的xml字符串
     * @return Java Bean对象
     */
    public static Object xml2Bean(Map<String, Class> clazzMap, String xml) {
        XStream xstream = new XStream(){
            //不直接new XStream 是为了忽略 XML存在 而实体bean中没有的属性
            //1.4.5以后可以直接new 再设置Ignore方法
            @Override
            protected MapperWrapper wrapMapper(MapperWrapper next) {
                return new MapperWrapper(next) {
                    @Override
                    public boolean shouldSerializeMember(Class definedIn, String fieldName) {
                        if (definedIn == Object.class) {
                            return false;
                        }
                        return super.shouldSerializeMember(definedIn, fieldName);
                    }
                };
            }
        };
        for (Iterator it = clazzMap.entrySet().iterator(); it.hasNext();) {
            Map.Entry<String, Class> m = (Map.Entry<String, Class>) it.next();
            xstream.alias(m.getKey(), m.getValue());
        }
        
        Object bean = xstream.fromXML(xml);
        return bean;
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        //"<?xml version=1.0 encoding=UTF-8?>" +
        String xml=
                "<Document>" +
                "<functionId>201001</functionId>" +
                "<exSerial></exSerial>" +
                "<bankNo></bankNo>" +
                "<branchNo>0100</branchNo>" +
                "<channel></channel>" +
                "<operator></operator>" +
                "<authOper></authOper>" +
                "<authPwd></authPwd>" +
                "<applyDate></applyDate>" +
                "<applyTime></applyTime>" +
                "<reserve></reserve>" +
                "<reserve1></reserve1>" +
                "<accountNo1>2</accountNo1>" +
                "<grantAmt1>0</grantAmt1>" +
                "<billClass>1</billClass>" +
                "<billType>1</billType>" +
                "<dueDt>2016-11-26</dueDt>" +
                "<issueDt>2016-08-10</issueDt>" +
                "<loanSystem>1</loanSystem>" +
                "<protocalNo>AG005</protocalNo>" +
                "<remitterAcct>2</remitterAcct>" +
                "<remitterCustNo>2200006456</remitterCustNo>" +
                "<batchNo></batchNo>" +
                "<billList>"+
                "<bean>"+
                "<billMoney>3000</billMoney><currencyCategory>RMB</currencyCategory><payeeAcct>3</payeeAcct><payee>测试01</payee><payeeBankName>中国民生银行北京分行营业部</payeeBankName>" +
                "</bean><bean>"+
                "<billMoney>2000</billMoney><currencyCategory>RMB</currencyCategory><payeeAcct>3</payeeAcct><payee>测试02</payee><payeeBankName>中国民生银行北京分行营业部</payeeBankName>" +
                "</bean><bean>"+
                "<billMoney>3000</billMoney><currencyCategory>RMB</currencyCategory><payeeAcct>3</payeeAcct><payee>测试03</payee><payeeBankName>中国民生银行北京分行营业部</payeeBankName>" +
                "</bean>"+
                "</billList>"+
                "</Document> ";
        
        System.out.println("xml to bean");
//        Map<String, Class> cm = new HashMap<String, Class>();
//        cm.put("Document", Var201001.class);
//        cm.put("bean", AcptBillInfoBean.class);
//        Var201001 obj=(Var201001)xml2Bean(cm, xml);
//        System.out.println(obj.getProtocalNo()+obj.getBillList().get(0).getCurrencyCategory());

//        System.out.println("bean to xml");
//        Var201001 bean=new Var201001();
//        bean.setAccountNo1("1");
//        bean.setBatchNo("xxxsa");
//        List<AcptBillInfoBean> billList=new ArrayList<AcptBillInfoBean>();
//        AcptBillInfoBean info=new AcptBillInfoBean();
//        info.setBillMoney("22");
//        billList.add(info);
//        bean.setBillList(billList);
//        
//        Map<String, Class> clazzMap = new HashMap<String, Class>();
//        clazzMap.put("Document", Var201001.class);
//        clazzMap.put("bean", AcptBillInfoBean.class);
//        String xx= bean2xml(clazzMap, bean);
//        System.out.println(xx);
        
        System.out.println("list to xml");
        ProResult result1=new ProResult();
        result1.setType("S");
        Var101101InfoBean bean1=new Var101101InfoBean();
        bean1.setRgctId("1");
        bean1.setResult("S");
        Var101101InfoBean bean2=new Var101101InfoBean();
        bean2.setRgctId("2");
        bean2.setResult("E");
        bean2.setMessage("我错了");
        List list=new ArrayList();
//        list.add(result1);
        list.add(bean1);
        list.add(bean2);
        Var101101Result result=new Var101101Result();
        result.setProResult(result1);
        result.setResult(list);
        Map<String, Class> map = new HashMap<String, Class>();
        map.put("Document", Var101101Result.class);
        map.put("info", Var101101InfoBean.class);
        String errResp=XmlBeanUtil.bean2xml(map, result);
        System.out.println(errResp);
        
    }

}
