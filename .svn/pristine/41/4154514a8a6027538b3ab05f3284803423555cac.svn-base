package com.herongtech.console.service.certificateBinding;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.dao.BranchDao;
import com.herongtech.console.service.interfaces.ICertificateBindingService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.rc.domain.bean.EcdsBankData;
import com.herongtech.rc.domain.dao.EcdsBankDataDao;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * @author 李江涛
 *
 */
/**
 * @author 李江涛
 *
 */
public class CertificateBindingService implements ICertificateBindingService{

	/**返回相应状态ecdsbankdata集合
	 * @throws BizAppException */
	@Override
	public List<Branch> SelectBranch(String branchbankno) throws BizAppException {
		BranchDao branchdao = new BranchDao();
		IDB session =  DBFactory.getDB();
		try {

			session.beginTransaction();
			Branch branch = branchdao.getBranchByBrchBankNo(branchbankno); //根据登陆者行号查询出机构以便取出法人行编号
			List<Branch> branchs=null;
		
			if(branch!=null&&(!branch.getBankNo().equals("")||branch.getBankNo()!=null)){
				branchs = branchdao.getBranchBybankno(branch.getBankNo());		//根据法人行编号查询处所有机构		
			}
			if(branch!=null){
				return branchs;				
			}
			session.endTransaction();
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("数据库错误   ");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询信息失败");
		}
		
		return null;
	}

	
	
	/**证书绑定关联查询出指定状态的EcdsBankData集合*/
	@Override
	public List<EcdsBankData> getEcdsBankList(Page page,String branchbankno,String certBindingStatus,String row_number) throws BizAppException {
		EcdsBankDataDao ecdsdao = new EcdsBankDataDao();
		
	
			List<EcdsBankData> obj;
			try {
				obj = ecdsdao.getEcdsBankDatalist(page, branchbankno, certBindingStatus,row_number);
				return obj;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return null;
		
	
	}


}
