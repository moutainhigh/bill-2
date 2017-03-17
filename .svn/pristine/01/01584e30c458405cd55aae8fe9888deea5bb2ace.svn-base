package com.herongtech.console.core.common.search;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.herongtech.console.domain.disc.bean.DiscSearchBean;





/**
 * sql前台查询条件封装
 * 
 * 
 */
public class QueryCondition {

	private List sqlstrList = new ArrayList();
	private List condArgs = new ArrayList(); //参数值
	private List relations = new ArrayList();
	private String basicSql_low;// 小写的基础Sql
	private String basicSql;// 基础Sql
	private String orderSql;
	private SqlTools  sqltools;
	/**
	 * 
	 * @param sqlWhereString
	 *            [tableObject.property>/=/</>=/<=/like]
	 * @param obj
	 *            Integer/String/Double/Date
	 * qc.add("roleCode=:rolecode", model.getRoleCode());
	 * 
	 */
	public void add(String sqlWhereString, Object condArg) {
		if (condArg == null || "".equals(condArg))
			return;
		sqlstrList.add(sqlWhereString);
		condArgs.add(condArg);
		relations.add("and");
	}

	/**
	 * 添加不确定长度参数
	 * eg:qc.add("info.rgct_id in (:rgctIds)", obj);
	 * @param sqlWhereString
	 * @param condArg
	 */
	public void add(String sqlWhereString, Object[] condArg) {
		if (condArg == null || condArg.length==0)
			return;
		sqlstrList.add(sqlWhereString);
		condArgs.add(condArg);
		relations.add("and");
	}

	
	
	public void add(String sqlWhereString, Object condArg, String relation) {
		if (condArg == null)
			return;
		sqlstrList.add(sqlWhereString);
		condArgs.add(condArg);
		relations.add(relation);
	}
	

	
	
	public void setOrderSql(String orderSql) {
		this.orderSql = orderSql;
	}

	public String getSqlWhereCond() {
		StringBuffer sb = new StringBuffer();
		if (sqlstrList.size() > 0)
			sb.append("(");
		for (int i = 0; i < sqlstrList.size(); i++) {

			sb.append((String) sqlstrList.get(i));
			if (i != sqlstrList.size() - 1)
				sb.append(" " + relations.get(i) + " ");
		}
		if (sqlstrList.size() > 0)
			sb.append(")");
		return sb.toString();
	}

	public Object[] getArgsObject() {
		Object[] args = new Object[condArgs.size()];
		for (int i = 0; i < condArgs.size(); i++) {
			args[i] = condArgs.get(i);
		}
		return args;
	}

	public String[] getArgsAliasName() {
		String[] aliasName = new String[sqlstrList.size()];
		for (int i = 0; i < sqlstrList.size(); i++) {
			String tempStr = (String) sqlstrList.get(i);
			String mark = tempStr.split(":")[1];
			mark = StringUtils.replace(mark, ")", "").trim();
			aliasName[i] = mark;
		}
		return aliasName;
	}

	public String getAllSqlString(String baseSql) {
	    sqltools=new SqlTools(baseSql);
	    sqltools.analyzeSQL();
		
		String whereCond = getSqlWhereCond();
		String tempStr = sqltools.getBusinessSQL().toLowerCase();
		basicSql_low = tempStr;
		basicSql = sqltools.getBusinessSQL();
		//int pos = tempStr.indexOf("order");
        int pos = tempStr.indexOf(" order ")+1;//避免表名和此关键字冲突时异常
		int length = tempStr.length();
		String orderFrontSql = baseSql;// order 之前的字符串
		String orderStr = "";// order 字符串
		if (pos > 0) {
			orderFrontSql = basicSql.substring(0, pos);
			orderStr = basicSql.substring(pos, length);
			basicSql_low = tempStr.substring(0, pos);
			basicSql = orderFrontSql;
		}
		// 暂时不添加order的处理
		StringBuffer sb = new StringBuffer();
		sb.append(orderFrontSql);
		if (whereCond != null && !"".equals(whereCond)) {
			if (tempStr.indexOf("where") == -1) {
				sb.append(" where ");
			} else {
				sb.append(" and ");
			}
			sb.append(whereCond);
		}
		sb.append(orderStr);
		
		String retSql = sqltools.backshowSQL(sb.toString());
		
		retSql=replaceParamter(retSql);
		if (orderSql != null && orderSql.length() > 0) {
			if(orderStr.equals("")) {
				return retSql + " order by " + orderSql;
			} else {
				return retSql + "," + orderSql;
			}
		} else {
			return retSql;
		}
	}
	
	
	/**
	 * 获取参数值
	 * @return
	 */
	public List<Object> getParameterValues(){
	   Object[] objArr = getArgsObject();
	   List list=new ArrayList();
	   for(Object obj:objArr){//不考虑数组里套数组在套数组的情况
	       if(obj instanceof Object[]){
               Object[] oa=(Object[])obj;
               for(int i=0;i<oa.length;i++){
                   list.add(oa[i]);
               }
           }else{
               list.add(obj);
           }
	   }
	    return list;
	}
	
	

	public String getCountSql(String keyQueryid) {
		String countSql = "";
		if (basicSql_low != null) {
			int pos = basicSql_low.indexOf("from");
			String behindFrom = basicSql.substring(pos, basicSql.length());
			countSql = "select count(" + keyQueryid + ") as totalSize "
					+ behindFrom;
			
			//如果出现group , 则去掉
			String countStr=countSql.toLowerCase();
			int grouppos=countStr.indexOf("group by");
			if(grouppos>-1)
				countSql=countSql.substring(0, grouppos);
			
		}
		String whereCond = getSqlWhereCond();
		StringBuffer sb = new StringBuffer();
		sb.append(countSql);
		if (whereCond != null && !"".equals(whereCond)) {
			if (basicSql_low.indexOf("where") == -1) {
				sb.append(" where ");
			} else {
				sb.append(" and ");
			}
			sb.append(whereCond);
			return replaceParamter(sqltools.backshowSQL(sb.toString()));
		}else{
			return replaceParamter(countSql);
		}
	}

	public String getCountSql2(String keyQueryId, String keyQueryMoney) {
		String countSql = "";
		if (basicSql_low != null) {
			int pos = basicSql_low.indexOf("from");
			String behindFrom = basicSql.substring(pos, basicSql.length());
			if (keyQueryMoney != null) {
				countSql = "select count(" + keyQueryId + ") as totalSize ,sum(" + keyQueryMoney + ") as sumMoney "
						+ behindFrom;
			} else {
				countSql = "select count(" + keyQueryId + ") as totalSize " + behindFrom;
			}
			
			//如果出现group , 则去掉
			String countStr=StringUtils.lowerCase(countSql) ;
			int grouppos=countStr.indexOf("group");
			if(grouppos>-1)
				countSql=countSql.substring(0, grouppos);
			
		}
		String whereCond = getSqlWhereCond();
		StringBuffer sb = new StringBuffer();
		sb.append(countSql);
		if (whereCond != null && !"".equals(whereCond)) {
			if (basicSql_low.indexOf("where") == -1) {
				sb.append(" where ");
			} else {
				sb.append(" and ");
			}
			sb.append(whereCond);
		}
		return replaceParamter(sqltools.backshowSQL(sb.toString()));
	}

	
	/**
	 * 将:property替换为？
	 * @param sql
	 * @return
	 */
	private String replaceParamter(String sql){
        for(int i=0;i<getArgsAliasName().length;i++){
            String name=getArgsAliasName()[i];
            Object obj=  getArgsObject()[i] ;
            if(obj instanceof Object[]){
                Object[] oa=(Object[])obj;
                String val="";
                for(int j=0;j<oa.length;j++){
                    val+="?,";
                }
                val=val.substring(0, val.length()-1);
                sql=StringUtils.replace(sql, ":"+name, val);
            }else{
                sql=StringUtils.replace(sql, ":"+name, "?");
            }
        }
        return sql;
    }
	
	/**
	 * 以下提供对SearchBean的支持
	 * @throws Exception 
	 * 
	 * @throws Exception
	 * 
	 */
	public void initFromSearchBean(final BaseSearchBean ChildSearchBean) throws Exception {
		if(ChildSearchBean == null)return;
		BaseSearchBean bBean =  ChildSearchBean;
		List pps = beanProperties(ChildSearchBean);
		for (int i = 0; i < pps.size(); i++) {
			String propName = (String) pps.get(i);
			String sqlQueryField = bBean.sqlQueryCondField(propName);
			Object vu = getPropertyValue(propName, ChildSearchBean);
			add(sqlQueryField, vu);
		}
		setOrderSql(bBean.queryOrder());
	}
	
	/**
     * 
     */
    public List beanProperties(Object searchBean) {

        List propList = new ArrayList();
        Method[] mds = searchBean.getClass().getMethods();
        for (int i = 0; i < mds.length; i++) {
            Method md = mds[i];
            String name = md.getName();
            Class type = md.getReturnType();

            if (name.startsWith("get")) {
                if (type == Integer.class || type == String.class || type == Long.class || type == Double.class
                        || type == java.lang.Boolean.class || type == java.lang.Byte.class
                        || type == java.lang.Float.class || type == java.lang.Short.class
                        || type == java.util.Date.class || type == java.lang.Object[].class) {
                    String prop = name.substring(3, name.length());
                    String firstMark = prop.substring(0, 1);
                    String newProp = firstMark.toLowerCase() + prop.substring(1, prop.length());

                    propList.add(newProp);
                }
            }
        }
        return propList;
    }

    public  Object getPropertyValue(String property, Object searchBean) throws Exception {

        String firstMark = property.substring(0, 1);
        String behindStr = property.substring(1, property.length());

        Method mthod = searchBean.getClass().getMethod("get" + firstMark.toUpperCase() + behindStr, new Class[0]);
        Object vu = mthod.invoke(searchBean);

        return vu;
    }
	
	
	
	
	public void reset(){
		this.sqlstrList = new ArrayList();
		this.condArgs = new ArrayList();
		this.relations = new ArrayList();
	}
	

	public String getBasicSql() {
		return basicSql;
	}

	public void setBasicSql(String basicSql) {
		this.basicSql = basicSql;
	}

	public String getBasicSql_low() {
		return basicSql_low;
	}

	public void setBasicSql_low(String basicSqlLow) {
		basicSql_low = basicSqlLow;
	}
	
	
    public static void main(String[] args) {
        String baseSql="select bill.* from tdisc_bill_info bill,tdisc_apply_info apply where bill.dibu_id=apply.id";
        DiscSearchBean searchBean=new DiscSearchBean();
        searchBean.setDfaultSrchTbAliasName("bill");//一般以查询返回对象的名字为别名
        
        searchBean.setOperStatus("BS01");//普通用法
        
        searchBean.setCustNo("1");
        searchBean.addProperty2TableObj("custNo", "apply");
        
        //当需要关联其他表时 塞值的用法
        searchBean.setBranchNo("%001%");
        searchBean.addProperty2TableObj("branchNo", "apply");
        searchBean.addSpecialOpertion("branchNo",BaseSearchBean.LIKE);
        
        
        //in的用法
        String[] rgctIds=new String[]{"1","2","3"};
        searchBean.setRgctIds(rgctIds);
        searchBean.addSpecialOpertion("rgctIds",BaseSearchBean.IN);
        searchBean.addSqlPropretyMapping("rgctIds", "rgctId");
        QueryCondition qc=new QueryCondition();
        
        //排序用法
        OrderBean order=new OrderBean("discmxId",false);//构造方法指定升序降序
        searchBean.appendOrder(order);
        
        OrderBean order2=new OrderBean("discId");
        searchBean.appendOrder(order2);
        
        //大于，等等写法
        searchBean.setStartDay("2016-08-01");
        searchBean.addProperty2TableObj("startDay", "bill");
        searchBean.addSpecialOpertion("startDay", BaseSearchBean.MORE_AND_EQUAL);
        searchBean.addSqlPropretyMapping("startDay", "dueDt");
        
        //add方法的用法 add里面的写法一定要写成sql
        qc.add("apply.batch_No=:batchNo", "TX123456");
        
        try {
            qc.initFromSearchBean(searchBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql=qc.getAllSqlString(baseSql);
        System.out.println(sql);
        
        List<Object> arr=qc.getParameterValues();
        for(Object o:arr){
            System.out.println(o.toString());
        }
        
        String sql2 = qc.getCountSql("discmx_id");
        System.out.println(sql2);
        
        String sql3 = qc.getCountSql2("discmx_id", "bill_money");
        System.out.println(sql3);
        
    }
}
