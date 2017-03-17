package com.herongtech.console.core.tag;

import java.math.BigInteger;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.herongtech.console.core.util.ContextHolderUtils;
import com.herongtech.logger.Logger;
import com.herongtech.logger.LoggerFactory;


public class TokenHelper {

    /**
     * tokenName的默认值
     */
    public static final String DEFAULT_TOKEN_NAME = "herong.token";

    /**
     * tokenName的存放属性名
     */
    public static final String TOKEN_NAME_FIELD = "herong.token.name";
    private static final Logger LOG = LoggerFactory.getLogger(TokenHelper.class);
    private static final Random RANDOM = new Random();

    
    /**
     * 生成token存入session中，使用默认的tokenName
     *
     * @return 返回生成的token
     */
    public static String setToken() {
        return setToken(DEFAULT_TOKEN_NAME);
    }

    /**
     * 生成token，并将值放入session中
     * 
     * @param tokenName 存入session中的token的属性名
     * @return 返回生成的token
     */
    public static String setToken(String tokenName) {
        HttpSession session = ContextHolderUtils.getSession();
        String token = generateGUID();
        try {
            session.setAttribute(tokenName, token);
        }
        catch(IllegalStateException e) {
            // WW-1182 explain to user what the problem is
            String msg = "Error creating HttpSession due response is commited to client. You can  create the HttpSession from your action before the result is rendered to the client: " + e.getMessage();
            LOG.error(msg, e);
            throw new IllegalArgumentException(msg);
        }

        return token;
    }

    /**
     * 根据tokenName从request中获取token值
     * 
     * @param tokenName
     * @return 找到则返回token值，否则返回null
     */
    public static String getToken(String tokenName,HttpServletRequest request) {
    	Map params = request.getParameterMap();
    	if(!params.containsKey(tokenName)){
    		LOG.warnMessage("Could not find token ["+tokenName+"] in params.");
    		return null;
    	}
    	String[] tokens = (String[])params.get(tokenName);
    	String token;
    	if((tokens==null)||(tokens.length<1)){
    		LOG.warnMessage("Got a null or empty token.");
    		return null;
    	}
    	token = tokens[0];
        return token;
    }

    /**
     * 从request参数中获取tokenName
     * @return 若存在，则返回tokenName，若不存在则返回null
     */
    public static String getTokenName(HttpServletRequest request) {
    	Map params = request.getParameterMap();
    	if(!params.containsKey(TOKEN_NAME_FIELD)){
    		LOG.warnMessage("Could not find token name in params.");
    		return null;
    	}
    	String[] tokenNames = (String[])params.get(TOKEN_NAME_FIELD);
    	String tokenName;
    	if((tokenNames==null)||(tokenNames.length<1)){
    		LOG.warnMessage("Got a null or empty token name.");
    		return null;
    	}
    	tokenName = tokenNames[0];
    	return tokenName;
    }

    /**
     * 校验token，request中的token和session中的token是否一致，校验成功后移除session中的token
     *
     * @return 一致则返回true，否则返回false
     */
    public static boolean validToken(HttpServletRequest request) {
        String tokenName = getTokenName(request); //从request中获取tokenName

        if (tokenName == null) {
            return false;
        }

        String token = getToken(tokenName,request); //从request中获取token

        if (token == null) {
            return false;
        }
        HttpSession session = ContextHolderUtils.getSession();
        String sessionToken = (String) session.getAttribute(tokenName); //从session中获取token

        if (!token.equals(sessionToken)) { //校验token是否一致
            return false;
        }

        //校验完毕后移除session
        session.removeAttribute(tokenName);

        return true;
    }

    public static String generateGUID() {
        return new BigInteger(165, RANDOM).toString(36).toUpperCase();
    }
}
