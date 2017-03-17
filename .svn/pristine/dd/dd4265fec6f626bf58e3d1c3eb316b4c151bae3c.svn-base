package com.herongtech.console.service.interfaces;

import com.herongtech.console.domain.common.bean.InterestReqByStringDTO;
import com.herongtech.console.domain.common.bean.InterestReqDTO;
import com.herongtech.console.domain.common.bean.InterestResultDTO;


/**
 * 利息计算服务接口
 *
 */
public interface IInterestService {

    /**
     * 利息计算
     * <br>利息=票面金额*利率*贴现期限
     * <br>贴现期限=计息到期日 – 贴现日（以天为单位）
     * <br>计息到期日=票面到期日+调整天数
     * @param interestDTO
     * @return
     * @throws Exception
     */
    public InterestResultDTO getInterest(InterestReqDTO interestDTO)throws Exception;
    
    /**
     * 网银端利息计算
     * <br>利息=票面金额*利率*期限
     * <br>计息期限=计息到期日 – 起息日（以天为单位）
     * <br>计息到期日=票面到期日+调整天数
     * @param interestDTO
     * @return
     * @throws Exception
     */
    public InterestResultDTO getIns(InterestReqByStringDTO interestDTO)throws Exception;
}
