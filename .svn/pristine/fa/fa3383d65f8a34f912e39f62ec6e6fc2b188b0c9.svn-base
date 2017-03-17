package com.herongtech.console.service.interfaces;

import java.util.List;

import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.bean.FacDealResult;

/**
 * 转贴现转入额度处理
 * @author fqz
 *
 */
public interface IFacService {
	/**判断业务场景是否需要处理额度
	 * @param apply
	 * @param userInfo
	 * @return
	 */
	public abstract boolean isDealCredit(Object apply);

	
	/**额度通用处理
	 * @param apply
	 * @param rebuyBills
	 * @param user
	 * @param facOperType 见FacCode 0不扣减，1扣减，2释放
	 * @return
	 * @throws Exception 
	 */
	public abstract FacDealResult dealFac(Object apply,List bills, UserLoginfo user,final String facOperType) throws Exception;

}
