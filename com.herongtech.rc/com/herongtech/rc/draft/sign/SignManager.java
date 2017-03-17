package com.herongtech.rc.draft.sign;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import com.cfca.toolkit.CastleException;
import com.cfca.toolkit.CastleProperties;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import com.herongtech.appconfig.SysConfigUtil;
import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.exception.impl.BizAppException;


public class SignManager {
    
    private static String userCertFilePath;// 证书文件的路径
    private static String UserCertPassword=SysConfigUtil.getSysConfig().getValue("userCertPassword");// 用户证书密码
    private static CnccCastle castle;// 进行数字签名
    private static CastleProperties castleProperties;// 数字签名属性设置
    private static ResourceLoader resourceLoader = new DefaultResourceLoader();

    static {
        try {
            userCertFilePath = URLDecoder.decode(resourceLoader.getResource(SysConfigUtil.getSysConfig().getValue("userCertFilePath")).getURL().getFile(), "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 数字签名的基础信息的配置
        castleProperties = new CastleProperties();
        castleProperties.setmUserCertFilePath(userCertFilePath);
        castleProperties.setmUserCertPassword(UserCertPassword);
        castle = new CnccCastle(castleProperties);
        try {
            castle.initCertAppContext();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CastleException e) {
            e.printStackTrace();
        }
    }
    

    public static String signData(String doc)throws BizAppException{

        String signedMessage = "";
        try {
            signedMessage = castle.SignDataDetached(doc.getBytes(DraftConstants.ENCODING));
        } catch (Exception e) {
            throw new BizAppException("签名过程中出错，签名证书不可用，或签名算法不支持");
        }
        return signedMessage;
    }
    
}
