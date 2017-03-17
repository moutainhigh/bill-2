package com.herongtech.console.core.common.search;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.apache.commons.lang.StringUtils;



public abstract class BaseSearchBean {

    public static final String MORE=" > ";
    public static final String MORE_AND_EQUAL=" >= ";
    public static final String LESS=" < ";
    public static final String LESS_AND_EQUAL=" <=" ;
    public static final String DEFAULT_EQUAL=" = ";
    public static final String NOT_EQUAL=" != ";
    public static final String LIKE=" like ";
    
    public static final String IN = " in ";
    
    
    private String dfaultSrchTbAliasName;//默认表对象别名
    private Map propertyBelongObj=new HashMap();//单独设置表属于对象映射关系
    private Map property2JdbcProperty=new HashMap();//属性和jdbc属性之间的映射
    private Map prop2valueOpertions=new HashMap();//字段名称与值的操作关系
    
    private LinkedList orderList = new LinkedList();//排序列表
    
    
    
    public void setDfaultSrchTbAliasName(String dfaultSrchTbAliasName) {
        this.dfaultSrchTbAliasName = dfaultSrchTbAliasName;
    }
    //如果没有映射字段属性名称与表对象别名的关系,则使用dfaultSrchTbAliasName
    /**
     * 属性与表别名关系
     * 比如 属性（bill_no） 别名（bill） 则在生成sql语句时会生成bill.billNo
     * @param property
     * @param tableAliasName
     */
    public void addProperty2TableObj(String property,String tableAliasName){
        propertyBelongObj.put(property, tableAliasName);
    }
    public void addProperty2TableObj(String[] propertys,String tableAliasName){
        for(int i=0;i<propertys.length;i++)
            propertyBelongObj.put(propertys[i], tableAliasName);
    }
    
    /**
     * seachbean某个与数据库属性对不上时可以使用此方法
     * 
     * 通常下面3行一起使用
     *      seachbean.addSqlPropretyMapping("billNos", "billNo");
     *      seachbean.addProperty2TableObj("billNos", "bill");
     *      seachbean.addSpecialOpertion("billNos", BaseSearchBean.IN);
     *      
     * @param localPropname
     * @param SqlPropname
     */
    public void addSqlPropretyMapping(String localPropname,String SqlPropname){
        property2JdbcProperty.put(localPropname, SqlPropname);
    }
    //添加字段名和值之间的比较关系,默认是=
    public void addSpecialOpertion(String property,String opertionMark){
        prop2valueOpertions.put(property, opertionMark);
    }
    
    
    public void appendOrder(OrderBean order) {
        if (order != null) {
            orderList.addLast(order);
        }
    }

    public void insertOrder(OrderBean order) {
        if (order != null) {
            orderList.addFirst(order);
        }
    }
    
    public LinkedList getOrderList(){
        return orderList;
    }
    
    public String sqlQueryCondField(String property){
        String tableAliasName=(String)propertyBelongObj.get(property);
        if(tableAliasName==null)
            tableAliasName=dfaultSrchTbAliasName;
        
        String propertyName=(String)property2JdbcProperty.get(property);
        
        if(propertyName==null)
            propertyName=property;
        
        String opermark=(String)prop2valueOpertions.get(property);
        if(opermark==null)
            opermark=DEFAULT_EQUAL;
        
        
        String condStr= tableAliasName+"."+beanField2jdbcField(propertyName)+opermark+":"+property;
        if(IN.equals(opermark)){
            condStr= tableAliasName+"."+beanField2jdbcField(propertyName)+opermark+"(:"+property+")";
        }
        
        return  condStr;
    }
    
    public String beanField2jdbcField(String propertyName){
        if(propertyName.indexOf("_")>0)
            return propertyName;
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < propertyName.length(); i++) {
            if (Character.isUpperCase(propertyName.charAt(i))) {
                sb.append("_"+propertyName.charAt(i));
            }else{
                sb.append(propertyName.charAt(i));
            }
        }
        return sb.toString();
    }
    
    
    public String queryOrder() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < orderList.size(); i++) {
            OrderBean order = (OrderBean) orderList.get(i);
            String tableAliasName = (String) propertyBelongObj.get(order.getProperty());
            if (tableAliasName == null) tableAliasName = dfaultSrchTbAliasName;
            String propertyName = (String) property2JdbcProperty.get(order.getProperty());
            if (propertyName == null) propertyName = order.getProperty();
            
            if (i > 0) {
                sb.append(",");
            }
            sb.append(tableAliasName + "." + beanField2jdbcField(propertyName) + " ");
            if (order.isAsc()) {
                sb.append("asc");
            } else {
                sb.append("desc");
            }
        }
        return sb.toString();
    }
    
    public String findProp2valueOpertions(String property){
        String operator = (String) prop2valueOpertions.get(property);
        if(operator == null){
            return DEFAULT_EQUAL;
        }else{
            return operator;
        }
    }
}
