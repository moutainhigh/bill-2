/********************************************
 * 文件名称: RcServiceFactory.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: yanjl
 * 开发时间: 2016-6-15 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.rc.service;


import com.herongtech.beancontainer.BeanContainerFactory;
import com.herongtech.rc.draft.DraftService;
import com.herongtech.rc.service.interfaces.IBillFlowInfoService;
import com.herongtech.rc.service.interfaces.IDraftMappingService;
import com.herongtech.rc.service.interfaces.IEcdsApDataService;
import com.herongtech.rc.service.interfaces.IEcdsBankDataService;
import com.herongtech.rc.service.interfaces.IEcdsCommonDataService;
import com.herongtech.rc.service.interfaces.IRcRegBillService;
import com.herongtech.rc.service.interfaces.IRgctBillHistService;
import com.herongtech.rc.service.interfaces.IRgctBillInfoService;
import com.herongtech.rc.service.interfaces.IRgctDraftLogService;
import com.herongtech.rc.service.interfaces.IRgctDraftTemplateService;
import com.herongtech.rc.service.interfaces.IRgctEcdsStatusService;
import com.herongtech.rc.service.interfaces.IRgctExceptionDraftService;
import com.herongtech.rc.service.interfaces.IRgctMethodService;
import com.herongtech.rc.service.interfaces.IRgctStatusService;
import com.herongtech.rc.service.interfaces.ISendEcdsService;
import com.herongtech.rc.service.rcservice.IRcAcptBillService;
import com.herongtech.rc.service.rcservice.IRcAssuranceService;
import com.herongtech.rc.service.rcservice.IRcBaseService;
import com.herongtech.rc.service.rcservice.IRcBillNotifyService;
import com.herongtech.rc.service.rcservice.IRcBuybackService;
import com.herongtech.rc.service.rcservice.IRcDiscService;
import com.herongtech.rc.service.rcservice.IRcEndorseService;
import com.herongtech.rc.service.rcservice.IRcGetBillService;
import com.herongtech.rc.service.rcservice.IRcImpawnService;
import com.herongtech.rc.service.rcservice.IRcPresentationService;
import com.herongtech.rc.service.rcservice.IRcRebuyService;
import com.herongtech.rc.service.rcservice.IRcSaleBackService;
import com.herongtech.rc.service.rcservice.IRcSaleService;
import com.herongtech.rc.service.rcservice.IRcSaveBillService;
import com.herongtech.rc.service.rcservice.IRcUnimpawnService;
import com.herongtech.rc.service.rcservice.ISysTrigger;
import com.herongtech.rc.service.rcservice.ITrigger;


public class RcServiceFactory {
	
	public static final String HERONGTECH_VERSION="@system 合融技术平台 @version 2.0.0.1 @lastModiDate @describe ";
	
	
	/**
	 * IEcdsBankDataService  ecds交易对手服务类
	 * @return
	 */
	public static IEcdsBankDataService getEcdsBankDataService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("EcdsBankDataService");
	}
	
	public static IEcdsCommonDataService getEcdsCommonDataService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
                getBean("EcdsCommonDataService");
    }
	
	/**
	 * IRgctDraftLogService  ecds日志信息服务类
	 * @return
	 */
	public static IRgctDraftLogService getRgctDraftLogService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("RgctDraftLogService");
	}
	
	/**
	 * 网银查询服务
	 * @return
	 */
	public static IBillFlowInfoService getBillFlowInfoService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
		getBean("BillFlowInfoService");
	}
	
	
	/**
	 * IRgctDraftTemplateService  日志模板信息服务类
	 * @return
	 */
	public static IRgctDraftTemplateService getRgctDraftTemplateService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("RgctDraftTemplateService");
	}
	
	/**
	 * IRgctExceptionDraftService  异常日志信息服务类
	 * @return
	 */
	public static IRgctExceptionDraftService getRgctExceptionDraftService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("RgctExceptionDraftService");
	}
	
	/**
	 * IRgctEcdsStatusService  ecds状态信息服务类
	 * @return
	 */
	public static IRgctEcdsStatusService getRgctEcdsStatusService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("RgctEcdsStatusService");
	}
	
	/**
	 * IRgctMethodService  票据交易对应的处理方法服务类
	 * @return
	 */
	public static IRgctMethodService getRgctMethodService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("RgctMethodService");
	}
	
	/**
	 * IRgctMethodService  状态信息
	 * @return
	 */
	public static IRgctStatusService getRgctStatusService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("RgctStatusService");
	}

	/**
	 * IRcBaseService  rc票据登记历史信息
	 * @return
	 */
	public static IRgctBillHistService getRgctBillHistService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("RgctBillHistService");
	}
	
	/**
	 * IRcBaseService  rc票据登记信息
	 * @return
	 */
	public static IRgctBillInfoService getRgctBillInfoService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("RgctBillInfoService");
	}
	
	
	/**
	 * 获取接入点信息数据服务
	 * @return
	 */
	public static IEcdsApDataService getEcdsApDataService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
                getBean("EcdsApDataService");
    }
	
	/**
	 * SendEcdsService
	 * @return
	 */
	public static ISendEcdsService getSendEcdsService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
                getBean("SendEcdsService");
    }
	
	/**
     * 报文服务类
     * @return
     */
    public static DraftService getDraftService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
                getBean("DraftService");
    }
    
    /**
     * 报文映射服务类
     * @return
     */
    public static IDraftMappingService getDraftMappingService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
                getBean("DraftMappingService");
    }
    
    
    /////////////////////rc处理类////////////////////
    /**
	 * IRcRegBillService  票据登记服务类
	 * @return
	 */
	public static IRcRegBillService getRcRegBillService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("RcRegBillService");
	}
	
	 /**
	 * IRcAcptBillService  票据承兑服务类
	 * @return
	 */
	public static IRcAcptBillService getRcAcptBillService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("RcAcptBillService");
	}
	
	 /**
	 * IRcBillNotifyService  票据收票服务类
	 * @return
	 */
	public static IRcBillNotifyService getRcBillNotifyService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("RcBillNotifyService");
	}
	
	 /**
	 * IRcDiscService  票据贴现服务类
	 * @return
	 */
	public static IRcDiscService getRcDiscService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("RcDiscService");
	}
	
	 /**
	 * IRcEndorseService  票据背书服务类
	 * @return
	 */
	public static IRcEndorseService getRcEndorseService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("RcEndorseService");
	}
	
	 /**
	 * IRcEndorseService  票据提示付款服务类
	 * @return
	 */
	public static IRcPresentationService getRcPresentationService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("RcPresentationService");
	}
	 /**
	 * IRcEndorseService  票据rcBase服务类
	 * @return
	 */
	public static IRcBaseService getRcBaseService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("RcBaseService");
	}
	
	 /**
	 * IRcImpawnService  票据质押服务类
	 * @return
	 */
	public static IRcImpawnService getRcImpawnService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("RcImpawnService");
	}
	
	/**
	 * IRcUnimpawnService  票据解质押服务类
	 * @return
	 */
	public static IRcUnimpawnService getRcUnimpawnService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("RcUnimpawnService");
	}
	
	/**
	 * IRcRebuyService  票据转入服务类
	 * @return
	 */
	public static IRcRebuyService getRcRebuyService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("RcRebuyService");
	}
	/**
	 * IRcSaleService  票据转入服务类
	 * @return
	 */
	public static IRcSaleService getRcSaleService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("RcSaleService");
	}
	/**
	 * ITrigger  票据电票处理回调类
	 * @return
	 */
	public static ITrigger getRcTriggerService(String bean){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean(bean);
	}
	/**
	 * ITrigger  电子票据系统通知回调类
	 * @return
	 */
	public static ISysTrigger getRcSysTriggerService(String bean){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean(bean);
	}
	
	/**
	 * IRcSaveBillService  票据存票服务类
	 * @return
	 */
	public static IRcSaveBillService getRcSaveBillService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("RcSaveBillService");
	}
	/**
	 * IRcGetBillService  票据取票服务类
	 * @return
	 */
	public static IRcGetBillService getRcGetBillService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("RcGetBillService");
	}
	
	
	/**
	 * IRcBuybackService  票据赎回服务类
	 * @return
	 */
	public static IRcBuybackService getRcBuybackService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("RcBuybackService");
	}
	/**
	 * IRcSaleBackService  票据返售服务类
	 * @return
	 */
	public static IRcSaleBackService getRcSaleBackService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("RcSaleBackService");
	}
	
	/**
	 * IRcAssuranceService  票据保证服务类
	 * @return
	 */
	public static IRcAssuranceService getRcAssuranceService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("RcAssuranceService");
	}
}
