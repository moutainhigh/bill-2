package com.herongtech.console.core.common.search;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class SqlTools {

    private String businessSQL;//业务SQL      
    private Map parameterReplaces=new HashMap();
    
    public String getBusinessSQL(){
        return businessSQL;
    }
    
    public SqlTools(String businessSQL){
        this.businessSQL=businessSQL;
    }
    
    public void analyzeSQL(){
        String operSQL=getLowBasicSQL(businessSQL);
        replaceParaSubSelectClause(operSQL);        
    }
    
    
    public String backshowSQL(String trueSQL){
        Set set=parameterReplaces.keySet();
        Iterator itor=set.iterator();
        while(itor.hasNext()){
            String key=(String)itor.next();
            String sqlflag=(String)parameterReplaces.get(key);
            trueSQL=trueSQL.replaceFirst(key, sqlflag);
        }
        return trueSQL;         
    }
    
    
    
    /**
     * 转化SQL变成小写
     * @param fromSQL
     * @return
     */
    private   String getLowBasicSQL(String fromSQL){
        StringBuffer sb=new StringBuffer();
        String str=fromSQL;
        sb.append(str.toLowerCase());
        return sb.toString();
    }
    
    /**
     * 寻找右括号
     */
    
    private int getRightBrtPos(String SQL,int startpos){
        int pos=-1;         
        int occan=0;            
        for(int i=startpos;i<SQL.length();i++){
            char chr=SQL.charAt(i);
            if(chr=='('){
                occan=occan+1;
            }else if(chr==')'){
                occan=occan-1;
                if(occan==0){
                    pos=i;
                    break;
                }
            }
            
        }
    
        return pos;
    }
    
    /**
     * 传入 select 是小写的SQL
     * @param SQL
     * @return
     * example:
     * select aa,dd,(select a.sib,a.pp from tt as a ) as totalSize from bb where bb.id in (select aa.id from temptable as tab ) and bb.name='jacke' 
     */
    private String formatSQL(String SQL){
        String[] SQLs=SQL.split(" ");
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<SQLs.length;i++){
            if(SQLs[i].trim().length()==0) continue;
            sb.append(SQLs[i].trim()+" ");
        }
        String tempStr=sb.toString();
        tempStr=tempStr.replaceAll(", ", ",");
        tempStr=tempStr.replaceAll(" select", "select");
        return tempStr;
    }
    /**
     * 替换子查询和子条件
     */
    private void replaceParaSubSelectClause(String SQL){
        String mark="(select";
        int pos=SQL.indexOf(mark);
        if(pos>-1){
            
            int rightpos=getRightBrtPos(SQL,pos)+1;
            String repStr=businessSQL.substring(pos, rightpos);
            String repStr_lower=repStr.toLowerCase();
            parameterReplaces.put("____para"+pos, repStr);
            
            //businessSQL=businessSQL.replaceFirst(repStr,"____para"+pos);
            //SQL=SQL.replaceFirst(repStr_lower, "____para"+pos);
            
            StringBuffer sb=new StringBuffer();
            sb.append(businessSQL);
            sb.replace(pos, rightpos, "____para"+pos);
            businessSQL=sb.toString();
            
            sb=new StringBuffer();
            sb.append(SQL);
            sb.replace(pos, rightpos, "____para"+pos);
            SQL=sb.toString();
            
            pos=SQL.indexOf(mark);
            if(pos>-1){
                replaceParaSubSelectClause(SQL);
            }
        }
    }

    
//    public static void main(String[] args) {
//        String SQL = "select bill.* from tdisc_bill_info bill,tdisc_apply_info apply where bill.dibu_id=apply.id";
//
//        System.out.println(getCountSql(SQL));
//    }
}
