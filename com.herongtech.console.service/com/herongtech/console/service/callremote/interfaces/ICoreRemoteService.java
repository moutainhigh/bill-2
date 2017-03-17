package com.herongtech.console.service.callremote.interfaces;

import com.herongtech.console.domain.acct.bean.AccountResponseDTO;
import com.herongtech.console.domain.acct.bean.AcctFlow;
import com.herongtech.console.service.callremote.bean.AcctRequest;


/**
 * 调用核心系统服务接口
 *
 */
public interface ICoreRemoteService {
    
    
    public AccountResponseDTO account(AcctRequest request)throws Exception;
    
    public AccountResponseDTO reverseAccount(AcctFlow af)throws Exception;

}
