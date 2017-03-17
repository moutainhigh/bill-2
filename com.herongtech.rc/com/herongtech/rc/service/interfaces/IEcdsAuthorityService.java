package com.herongtech.rc.service.interfaces;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.EcdsAuthlistData;
/**业务权限变更通知报文*/
public interface IEcdsAuthorityService {

	public void AddOrUpdateAuthority(EcdsAuthlistData ecdsauthlistData) throws BizAppException;
}
