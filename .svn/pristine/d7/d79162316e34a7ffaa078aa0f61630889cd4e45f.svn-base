package com.herongtech.rc.draft.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang.StringUtils;

import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.exception.impl.BizAppException;




public class MsgHeadUtil {

private static String BeginFlag = "{H:";
    
    private static String VersionID = "01";
    
    private static String SenderID = "9968        ";
    
    private static String SenderSID = "CD";
    
    private static String SenderCID = "        ";
    
    /**
     * 接收人节点
     */
    private static String ReceiverID = "ReceiverID";
    
    private static String ReceiverSID = "CD";
    
    private static String ReceiverCID = "        ";
    
    /**
     * 报文发起人 12位 不足后面补空格
     */
    private static String OrigSender = "OrigSender";
    /**
     * 报文接收人 12位 不足后面补空格
     */
    private static String OrigReceiver = "OrigReceiver";
    
    /**
     * 需要替换发送日期
     */
    private static String OrigSendDate = "yyyyMMdd";
    /**
     * 需要替换发送时间
     */
    private static String OrigSendTime = "hhmmss";
    /**
     * 需要替换报文编号
     */
    private static String MesgType = "MesgType";
    /**
     * 需要替换报文ID,20位
     */
    private static String MesgID = "MesgID";
    
    private static String MesgRefID = "MesgRefID";
    
    private static String MesgPriority = "3";
    
    private static String EncryptFlag = "N";
    
    /**需要替换报文体长度 8 位，不足前面补0**/
    private static String BodyLength = "BodyLength";
    
    /**需要替换报体校验值,32位**/
    private static String BodyChksum = "BodyChksum";
    
    private static String Reserve = "        ";//8个空格
    
    private static String EndFlag = "}\r\n";
    
    public static String getHeadString(){
        StringBuffer sb = new StringBuffer();
        sb.append(BeginFlag);
        sb.append(VersionID);
        sb.append(SenderID);
        sb.append(SenderSID);
        sb.append(SenderCID);
        sb.append(ReceiverID);
        sb.append(ReceiverSID);
        sb.append(ReceiverCID);
        sb.append(OrigSender);
        sb.append(OrigReceiver);
        sb.append(OrigSendDate);
        sb.append(OrigSendTime);
        sb.append(MesgType);
        sb.append(MesgID);
        sb.append(MesgRefID);
        sb.append(MesgPriority);
        sb.append(EncryptFlag);
        sb.append(BodyLength);
        sb.append(BodyChksum);
        sb.append(Reserve);
        sb.append(EndFlag);
        return sb.toString();
    }
    
    
    /**
     * 
     * @param receiverID
     * @param origSender
     * @param origReceiver
     * @param mesgType
     * @param mesgID
     * @param mesgRefID
     * @param msg
     * @return draft
     */
    public static String getMsgHead(String senderId,String receiverID, String origSender, String origReceiver, String mesgType, String mesgID,String mesgRefID ,String msg){
        String s =getHeadString();
        
        //检查是否符合替换长度，不符合的进行补充
        senderId = StringUtils.rightPad(senderId, 12, " ");
        receiverID = StringUtils.rightPad(receiverID, 12, " ");
        origSender = StringUtils.rightPad(origSender, 12, " ");
        origReceiver = StringUtils.rightPad(origReceiver, 12, " ");
        
        mesgType = StringUtils.rightPad(mesgType, 20, " ");
        mesgID = StringUtils.rightPad(StringUtils.substring(mesgID, 20, 28), 20, " ");
        if(mesgRefID == null){
            mesgRefID="";
        }
        mesgRefID = StringUtils.rightPad(mesgRefID, 20, " ");
        
        String msgLength = StringUtils.leftPad(msg.length()+"", 8, "0");
        String bodyChkSum = StringUtils.rightPad(checkMsg(msg), 32, " ");
        
        String origSendDate = get_YYYYMMDD_Date();
        String origSendTime = get_hhmmss_Date();

        //进行替换
        s = msgHeadReplace( s,senderId,receiverID ,origSender, origReceiver, mesgType, mesgID, mesgRefID, origSendDate, origSendTime, msgLength, bodyChkSum);
        
        return s;
    }

    
    private static String msgHeadReplace(String headString,String senderId,String receiverID,String origSender, String origReceiver, String mesgType, String mesgID, 
            String mesgRefID, String origSendDate, String origSendTime, String msgLength, String bodyChkSum) {
        headString = headString.replaceFirst(MsgHeadUtil.SenderID, senderId);
        headString = headString.replaceFirst(MsgHeadUtil.OrigSender, origSender);
        headString = headString.replaceFirst(MsgHeadUtil.ReceiverID, receiverID);
        headString = headString.replaceFirst(MsgHeadUtil.OrigReceiver, origReceiver);
        headString = headString.replaceFirst(MsgHeadUtil.OrigSendDate, origSendDate);
        headString = headString.replaceFirst(MsgHeadUtil.OrigSendTime, origSendTime);
        headString = headString.replaceFirst(MsgHeadUtil.MesgType, mesgType);
        headString = headString.replaceFirst(MsgHeadUtil.MesgID, mesgID);
        headString = headString.replaceFirst(MsgHeadUtil.MesgRefID, mesgRefID);
        headString = headString.replaceFirst(MsgHeadUtil.BodyLength, msgLength);
        headString = headString.replaceFirst(MsgHeadUtil.BodyChksum, bodyChkSum);
        return headString;
    }
    
    
    /**
     * MD5校验算法
     * 
     * @param msg 报文体内容
     * @return String 校验的值
     */
    private static String checkMsg(String msg) {
        StringBuffer buf = new StringBuffer("");
        byte[] msgByte = null;
        try {
            msgByte = msg.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
         
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(msgByte);
            byte b[] = md.digest();
            int i;
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
        } catch (NoSuchAlgorithmException e) {
            
        }
        return buf.toString();
    }
    
    /*
     * 获得当前日期的格式  yyyyMMdd
     */
    public static String get_YYYYMMDD_Date(){
        try {
            return DateTimeUtil.get_ECDS_YYMMDD_Date();
        } catch (BizAppException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        Calendar cal=Calendar.getInstance();
        String  dt=sdf.format(cal.getTime());
        return dt;
    }
    
    
    public static String get_hhmmss_Date(){
         SimpleDateFormat sdf=new SimpleDateFormat("hhmmss");
         Calendar cal=Calendar.getInstance();
         return sdf.format(cal.getTime());
    }
    
}
