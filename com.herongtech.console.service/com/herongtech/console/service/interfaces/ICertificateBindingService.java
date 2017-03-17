package com.herongtech.console.service.interfaces;

import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.domain.bean.Branch;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.EcdsBankData;

public interface ICertificateBindingService {

	
	public List<Branch> SelectBranch(String branchbankno) throws BizAppException;
	
	/**证书绑定关联查询出指定状态的EcdsBankData集合*/
	List<EcdsBankData> getEcdsBankList(Page page, String branchbankno,
			String certBindingStatus,String row_number) throws BizAppException;
}
