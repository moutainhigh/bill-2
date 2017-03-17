/********************************************
 * 文件名称: ServiceFactory.java
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

package com.herongtech.console.service;
import com.herongtech.beancontainer.BeanContainerFactory;
import com.herongtech.console.service.accountFlowSearch.IAccountFlowSearchService;
import com.herongtech.console.service.balanceSearch.IBalanceSearchService;
import com.herongtech.console.service.busiservice.acpt.IAcptService;
import com.herongtech.console.service.busiservice.assu.IAssuService;
import com.herongtech.console.service.busiservice.buyback.IBuybackService;
import com.herongtech.console.service.busiservice.collateralization.ICollateralizationService;
import com.herongtech.console.service.busiservice.common.ICommonService;
import com.herongtech.console.service.busiservice.disc.IDiscService;
import com.herongtech.console.service.busiservice.into.IIntoService;
import com.herongtech.console.service.busiservice.out.IOutService;
import com.herongtech.console.service.busiservice.rebuy.IRebuyService;
import com.herongtech.console.service.busiservice.repurchasedcollateralization.IRepurCollateService;
import com.herongtech.console.service.busiservice.sale.ISaleService;
import com.herongtech.console.service.busiservice.subcoll.ISubcollService;
import com.herongtech.console.service.common.audit.interfaces.IAuditNodeService;
import com.herongtech.console.service.common.audit.interfaces.IAuditProcessService;
import com.herongtech.console.service.common.audit.interfaces.IAuditRouteService;
import com.herongtech.console.service.common.audit.interfaces.IAuditStationService;
import com.herongtech.console.service.common.audit.interfaces.IAuditTaskService;
import com.herongtech.console.service.common.audit.interfaces.IReArProdService;
import com.herongtech.console.service.common.audit.interfaces.IReAsRoleService;
import com.herongtech.console.service.common.dayend.interfaces.IDayEndSchedulerService;
import com.herongtech.console.service.common.dayend.interfaces.ITaskLogService;
import com.herongtech.console.service.common.dayend.interfaces.ITaskPoolService;
import com.herongtech.console.service.common.entitydraftregister.IEntityDraftRegisterService;
import com.herongtech.console.service.common.export.IExportService;
import com.herongtech.console.service.common.fac.IFacRenewService;
import com.herongtech.console.service.common.machinestatus.MachineStatusService;
import com.herongtech.console.service.common.product.IProductService;
import com.herongtech.console.service.happensearch.IHappenSearchService;
import com.herongtech.console.service.interfaces.IAccountFacadeService;
import com.herongtech.console.service.interfaces.IAcctBalanceService;
import com.herongtech.console.service.interfaces.IBankInfoService;
import com.herongtech.console.service.interfaces.IBbspProductService;
import com.herongtech.console.service.interfaces.IBlackService;
import com.herongtech.console.service.interfaces.IBranchService;
import com.herongtech.console.service.interfaces.IBusiBranchService;
import com.herongtech.console.service.interfaces.IBusiDateService;
import com.herongtech.console.service.interfaces.ICacheService;
import com.herongtech.console.service.interfaces.ICertificateBindingService;
import com.herongtech.console.service.interfaces.ICustInfoAcctService;
import com.herongtech.console.service.interfaces.ICustInfoService;
import com.herongtech.console.service.interfaces.ICustLimitFlowService;
import com.herongtech.console.service.interfaces.ICustLimitService;
import com.herongtech.console.service.interfaces.ICustManageService;
import com.herongtech.console.service.interfaces.IDelayRuleService;
import com.herongtech.console.service.interfaces.IDeptService;
import com.herongtech.console.service.interfaces.IDictService;
import com.herongtech.console.service.interfaces.IDraftDrwrWrapper;
import com.herongtech.console.service.interfaces.IEcdsDataImportService;
import com.herongtech.console.service.interfaces.IErrmsgService;
import com.herongtech.console.service.interfaces.IFacCreateFlowService;
import com.herongtech.console.service.interfaces.IFacService;
import com.herongtech.console.service.interfaces.IFmsAgentService;
import com.herongtech.console.service.interfaces.IHolidayService;
import com.herongtech.console.service.interfaces.IInterestService;
import com.herongtech.console.service.interfaces.ILogsService;
import com.herongtech.console.service.interfaces.IMenuService;
import com.herongtech.console.service.interfaces.INoticeService;
import com.herongtech.console.service.interfaces.IOperationService;
import com.herongtech.console.service.interfaces.IParamService;
import com.herongtech.console.service.interfaces.IProdLimitTypeService;
import com.herongtech.console.service.interfaces.IRiskBillCheckService;
import com.herongtech.console.service.interfaces.IRiskBillService;
import com.herongtech.console.service.interfaces.IRoleMenuService;
import com.herongtech.console.service.interfaces.IRoleService;
import com.herongtech.console.service.interfaces.ISaleBackService;
import com.herongtech.console.service.interfaces.ISequenceService;
import com.herongtech.console.service.interfaces.ISerialnoService;
import com.herongtech.console.service.interfaces.ISignProdService;
import com.herongtech.console.service.interfaces.ISystemService;
import com.herongtech.console.service.interfaces.ITradeBankRootService;
import com.herongtech.console.service.interfaces.ITradeBankService;
import com.herongtech.console.service.interfaces.ITransPubService;
import com.herongtech.console.service.interfaces.IUserContextMenuService;
import com.herongtech.console.service.interfaces.IUserService;
import com.herongtech.console.service.interfaces.IWorkingAdsService;
import com.herongtech.console.service.tailAfterSearch.ITailAfterSearchService;
import com.herongtech.console.web.busicontroller.print.AbstractPrint;
import com.herongtech.rc.service.interfaces.IBillFlowInfoService;
import com.herongtech.rc.service.interfaces.ICustBillStorageService;
import com.herongtech.rc.service.interfaces.IEcdsBankDataService;


public class ServiceFactory {
	
	public static final String HERONGTECH_VERSION="@system 合融技术平台 @version 2.0.0.1 @lastModiDate @describe ";

	/**
	 * IUserService  用户相关方法
	 * @return
	 */
	public static IUserService getUserService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("UserService");
	}
	
	/**打印所需springbean公共方法 */
	public static AbstractPrint getPrint(String print){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean(print);
	}
	
	/**
	 * ISystemService  系统相关方法
	 * @return
	 */
	public static ISystemService getSystemService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("SystemService");
	}
	
	/**
	 * IMenuService  菜单相关方法
	 * @return
	 */
	public static IMenuService getMenuService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("MenuService");
	}
	
	/**
	 * IEcdsDataImportService  ECDS基础数据导入
	 * @return
	 */
	public static IEcdsDataImportService getEcdsDataImportService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("EcdsDataImportService");
	}
	
	/**
	 * IParamService  系统参数表
	 * @return
	 */
	public static IParamService getParamService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("ParamService");
	}
	/**
	 * ICustmanagerService  客户管理表
	 * @return
	 */
	public static ICustManageService getCustManageService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("CustManageService");
	}


	/**
	 * ISerialnoService  序列表
	 * @return
	 */
	public static ISerialnoService getSerialnoService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("SerialnoService");
	}
	/**
	 * IRiskBillService  风险票表
	 * @return
	 */
	public static IRiskBillService getRiskBillService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("RiskBillService");
	}

	public static IHolidayService getHolidayService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("HolidayService");
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
	 * ILogsService  系统日志表
	 * @return
	 */
	public static ILogsService getLogsService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("LogsService");
	}
	
	/**
	 * IBusiBranchService  销售机构表
	 * @return
	 */
	public static IBusiBranchService getBusiBranchService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("BusiBranchService");
	}
	
	/**
	 * IDeptService  部门表
	 * @return
	 */
	public static IDeptService getDeptService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("DeptService");
	}
	
	/**
	 * IBlackService  黑名单表
	 * @return
	 */
	public static IBlackService getBlackService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("BlackService");
	}
	
	
	/**
	 * SaleBackService 到期返售
	 * @return
	 */
	public static ISaleBackService getSaleBackService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("SaleBackService");
	}
	
	/**
     * 获取序列号管理类	
     * @return
     */
	public static ISequenceService getSequenceService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("SequenceService");
	}
	
	/**
    	 * 获系统信息
   	  * @return
     */
	public static IBusiDateService getSysBusiInfoService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("BusiDateService");
	}
	
	/**
	 * IErrmsgService 错误信息表
	 * @return
	 */
	public static IErrmsgService getErrmsgService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("ErrmsgService");
	}
	
	/**
	 * IBbspProductService  产品维护表
	 * @return
	 */
	public static IBbspProductService getBbspProductService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("BbspProductService");
	}
	

	/**
	 * IDictService  数据字典表
	 * @return
	 */
	public static IDictService getDictService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("DictService");
	}
	/**
	 * IBankInfoService  大行信息表
	 * @return
	 */
	public static IBankInfoService getBankInfoService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("BankInfoService");

	}
	
	/**
	 * IBusiDateService  营业时间表
	 * @return
	 */
	public static IBusiDateService getBusiDateService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("BusiDateService");

	}
	
	/**
	 * ICacheService  缓存表
	 * @return
	 */
	public static ICacheService getCacheService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("CacheService");
	}
	
	/**
	 * OperationService  菜单按钮控制表
	 * @return
	 */
	public static IOperationService getOperationService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("OperationService");
	}

	/**
	 * ISignProdService  产品签约表
	 * @return
	 */
	public static ISignProdService getSignProdService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("SignProdService");

	}
	
	/**
	 * ITradeBankService  交易对手信息维护表
	 * @return
	 */
	public static ITradeBankService getTradeBankService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("TradeBankService");

	}
	
	/**
	 * ITradeBankRootService  交易对手总行对照表
	 * @return
	 */
	public static ITradeBankRootService getTradeBankRootService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("TradeBankRootService");

	}
	
	/**
	 * ITransPubService  公共处理服务
	 * @return
	 */
	public static ITransPubService getTransPubService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("TransPubService");

	}
	
	/**
	 * ITransPubService  公共处理服务
	 * @return
	 */
	public static IDraftDrwrWrapper getDraftDrwrWrapper(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("DraftDrwrWrapper");

	}
	
	
			/**
	 * RoleService 角色服务类
	 * @return
	 */
	public static IRoleService getRoleService() {
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("RoleService");
	}
	
	/**
	 * IRoleMenuService 功能授权服务类
	 * @return
	 */
	public static IRoleMenuService getRoleMenuService() {
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("RoleMenuService");
	}

	/**
	 * IBranchService  机构表
	 * @return
	 */
	public static IBranchService getBranchService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("BranchService");

	}
	
	/**
	 * IWorkingAds  经营机构表
	 * @return
	 */
	public static IWorkingAdsService getWorkingAdsService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("WorkingAdsService");

	}
	
	/**
	 * IDelayRule  顺延规则表
	 * @return
	 */
	public static IDelayRuleService getDelayRuleService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("DelayRuleService");

	}
	
	

	/**
	 * ICustInfoService  客户信息表
	 * @return
	 */
	public static ICustInfoService getCustInfoService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("CustInfoService");
	}

	/**
	 * ICustInfoAcctService  客户帐号信息表
	 * @return
	 */
	public static ICustInfoAcctService getCustInfoAcctService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("CustInfoAcctService");
	}
	
	
	/**
	 * IDiscService  贴现服务类
	 * @return
	 */
	public static IDiscService getDiscService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("DiscService");
	}
	
	/**
	 * ISubcollService  托收服务类
	 * @return
	 */
	public static ISubcollService getSubcollService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("SubcollService");
	}
	
	/**
	 * IAssuService  保证服务类
	 * @return
	 */
	public static IAssuService getAssuService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("AssuService");
	}
	
	/**
	 * ICollateralizationService  质押服务类
	 * @return
	 */
	public static ICollateralizationService getCollateralizationService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("CollateralizationService");
	}
	
	/**
	 * IRepurCollateService  解质押服务类
	 * @return
	 */
	public static IRepurCollateService getRepurCollateService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("RepurCollateService");
	}
	
	/**
     * MachineStatusService  获取状态机服务类
     * @return
     */
    public static MachineStatusService getMachineStatusService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
                getBean("MachineStatusService");
    }
    
    /**
     * InterestService 获取利息计算服务类
     * @return
     */
    public static IInterestService getInterestService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
                getBean("InterestService");
    }
    
    /**
     * 获取日终调度服务类
     * @return
     */
    public static IDayEndSchedulerService getDayEndSchedulerService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
                getBean("dayEndSchedulerService");
    }
    
    /**
     * 日终任务服务类
     * @return
     */
    public static ITaskPoolService getTaskPoolService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
                getBean("taskPoolService");
    }
    
    /**
     * 获取审批路线服务类
     * @return
     */
    public static IAuditRouteService getAuditRouteService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
                getBean("auditRouteService");
    }  
    
    /**
     * 获取审批节点服务类
     * @return
     */
    public static IAuditNodeService getAuditNodeService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
                getBean("auditNodeService");
    }  
    
    /**
     * 获取审批岗位服务类
     * @return
     */
    public static IAuditStationService getAuditStationService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
                getBean("auditStationService");
    }
    
    /**
     * 获取审批角色服务类
     * @return
     */
    public static IReAsRoleService getReAsRoleService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
                getBean("reAsRoleService");
    }  
    /**
     * 获取审批机构产品服务类
     * @return
     */
    public static IReArProdService getReArProdService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
                getBean("reArProdService");
    }  
    
    /**
     * 获取审批流程服务类
     * @return
     */
    public static IAuditProcessService getAuditProcessService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
                getBean("auditProcessService");
    }  
    
    /**
     * 获取审批任务服务类
     * @return
     */
    public static IAuditTaskService getAuditTasksService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
                getBean("auditTaskService");
    }  
    
    /**
     * SaleService 获取转卖批次信息服务类
     * @return
     */
    public static ISaleService getISaleService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
                getBean("SaleService");
    }
    
    /**
     * IRebuyService  转入服务类
     * @return
     */
    public static IRebuyService getRebuyService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
                getBean("RebuyService");
    }
    
    /**
     * IAcptService 获取银承服务类
     * @return
     */
    public static IAcptService getAcptService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
                getBean("AcptService");
    }
    
    /**
     * 获取产品服务类
     * @return
     */
    public static IProductService getProductService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).getBean("ProductService");
    }
    
    /**
     *  获取贴现记账服务类
     * @return
     */
    public static IAccountFacadeService getDiscAccountService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).getBean("DiscAccountService");
    }
    
    /**
     *  获取回购记账服务类
     * @return
     */
    public static IAccountFacadeService getBuybackAccountService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).getBean("BuybackAccountService");
    }
    /**
     *  获取转入记账服务类
     * @return
     */
    public static IAccountFacadeService getRebuyAccountService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).getBean("RebuyAccountService");
    }
    /**
     *  获取反售记账服务类
     * @return
     */
    public static IAccountFacadeService getSalebackAccountService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).getBean("SalebackAccountService");
    }

    
    /**
     *  获取转出记账服务类
     * @return
     */
    public static IAccountFacadeService getSaleAccountService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).getBean("SaleAccountService");
    }
       /**
     *  获取质押记账服务类
     * @return
     */
    public static IAccountFacadeService getSaveAccountService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).getBean("SaveAccountService");
    }
    /**
     *  获取入池记账服务类
     * @return
     */
    public static IAccountFacadeService getIntoAccountService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).getBean("IntoAccountService");
    }
    /**
     *  获取解质押记账服务类
     * @return
     */
    public static IAccountFacadeService getGetAccountService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).getBean("GetAccountService");
    }
    /**
     *  获取出池记账服务类
     * @return
     */
    public static IAccountFacadeService getOutAccountService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).getBean("OutAccountService");
    }
    /**
     *  获取托收记账服务类
     * @return
     */
    public static IAccountFacadeService getSubcollAccountService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).getBean("SubcollAccountService");
    }
    /**
     * ICertificateBindingService 证书绑定
     * @return
     */
    public static ICertificateBindingService getCertificateBindingService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
                getBean("CertificateBindingService");
    }
    /**
     * ICommonService 基础查询
     * @return
     */
    public static ICommonService getCommonService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
                getBean("CommonService");
    }
    /**
     * IAccountFlowSearchService 账务流水查询
     * @return
     */
    public static IAccountFlowSearchService getAccountFlowSearchService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
                getBean("AccountFlowSearchService");
    }
    /**
     * ITailAfterSearchService 跟踪查询
     * @return
     */
    public static ITailAfterSearchService getTailAfterSearchService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
                getBean("TailAfterSearchService");
    }
    /**
     * IHappenSearchService 发生查询
     * @return
     */
    public static IHappenSearchService getHappenSearchService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
                getBean("HappenSearchService");
    }
    /**
     * IRiskBillCheckService 风险票检查
     * @return
     */
    public static IRiskBillCheckService getRiskBillCheckService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
                getBean("RiskBillCheckService");
    }
    
     /**
     * INoticeService 公告管理
     * @return
     */
    public static INoticeService getNoticeService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
                getBean("NoticeService");
    }
    
     /**
     * IAcctBalanceService	账务余额服务
     * @return
     */
    public static IAcctBalanceService getAcctBalanceService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
                getBean("AcctBalanceService");
    }
    /**
     *  获取余额查询服务类
     * @return
     */
    public static IBalanceSearchService getIBalanceSearchService(){
        return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).getBean("BalanceSearchService");
    }
    /**
     * 产品属性配置信息服务类
     * @return
     */
    public static IProdLimitTypeService getProdlLimitTypeService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).getBean("ProdLimitTypeService");
    	
    }
    /**
     * 日终日志服务类
     * @return
     */
    public static ITaskLogService getTaskLogService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).getBean("TaskLogService");
    	
    }
    
    /**
     * 日终日志服务类
     * @return
     */
    public static IExportService getExportService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).getBean("ExportService");
    	
    }
    
    /**
	 * IIntoService  存票服务类
	 * @return
	 */
	public static IIntoService getIntoService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("IntoService");
	}
	
	/**
	 * IOutService  取票服务类
	 * @return
	 */
	public static IOutService getOutService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("OutService");
	}
	/**
	 * IEcdsBankDataService 
	 * @return
	 */
	public static IEcdsBankDataService getEcdsBankDataService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("EcdsBankDataService");
	}
	
	/**
	 * ICustLimitService 客户余额管理
	 * @return
	 */
	public static ICustLimitService getCustLimitService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("CustLimitService");
	}
	
	/**
	 * ICustLimitFlowService 客户余额管理
	 * @return
	 */
	public static ICustLimitFlowService getCustLimitFlowService(){
			return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
					getBean("CustLimitFlowService");	
	}
	
	/**
	 * IFacCreateFlowService 客户余额流水管理
	 * @return
	 */
	public static IFacCreateFlowService getFacCreateFlowService(){
			return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
					getBean("FacCreateFlowService");	
	}
	/**
	 * IFmsAgentService 客户余额流水代理服务类
	 * @return
	 */
	public static IFmsAgentService getFmsAgentService() {
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("FmsAgentService");
	}
	/**
	 * IBuybackService 到期回购服务类
	 * @return
	 */
	public static IBuybackService getBuybackService() {
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("BuybackService");
	}
	public static IFacRenewService getFacRenewService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("FacRenewService");
	}
	/**
	 * 获取转贴现转入额度处理类
	 * @return
	 */
	public static IFacService getRebuyFacService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("RebuyFacService");
	}
	/**
	 * 获取质押额度处理类
	 * @return
	 */
	public static IFacService getCollateFacService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("CollateFacService");
	}
	/**
	 * 获取贴现额度处理类
	 * @return
	 */
	public static IFacService getDiscFacService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("DiscFacService");
	}
	
	/**
	 * IEntityDraftRegisterService 纸票登记服务类
	 * @return
	 */
	public static IEntityDraftRegisterService getEntityDraftRegisterService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("EntityDraftRegisterService");
	}
	
	/**
	 * IUserContextMenuService  快捷菜单相关方法
	 * @return
	 */
	public static IUserContextMenuService getUserContextMenuService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("UserContextMenuService");
	}
	/**
	 * ICustBillStorageService  余额查询处理服务类
	 * @return
	 */
	public static ICustBillStorageService getCustBillStorageService(){
		return BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
				getBean("CustBillStorageService");
	}
}
