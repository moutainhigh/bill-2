package com.herongtech.console.web.busicontroller.print;

import java.util.ArrayList;
import java.util.List;

import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.save.bean.SaveApplyInfo;
import com.herongtech.console.domain.save.bean.SaveBillInfo;
import com.herongtech.console.domain.save.dao.SaveApplyInfoDao;
import com.herongtech.console.domain.save.dao.SaveBillInfoDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IBranchService;

public class CollateralizationListPrint extends AbstractPrint{

	@SuppressWarnings("unchecked")
	@Override
	public List getPrintList(String ids, String batchId, String handleType)
			throws Exception {
		IBranchService branchService = ServiceFactory.getBranchService();
		SaveApplyInfoDao applydao = new SaveApplyInfoDao();
		SaveBillInfoDao billdao = new SaveBillInfoDao();
		SaveApplyInfo savebillbatch = null;
		SaveBillInfo saveBillInfo =null;
		List beanList = new ArrayList();
		HeadPrintBean headbean = new HeadPrintBean();// 头信息（批次信息）BEAN
		PublicItemBean pibean = new PublicItemBean();// 最终打印BEAN
		double allBillMoney = 0.0;// 票面总金额
		List list = new ArrayList();

		List<SaveBillInfo> billlist = billdao.getSaveBillListByMxids(ids.split(","));
		for (int i = 0; i < billlist.size(); i++) {
			PiaoGuanBean pgBean = new PiaoGuanBean();
			saveBillInfo = billlist.get(i);
			copyPropertys(pgBean, saveBillInfo);// inner中存放复核意见信息
			pgBean.setSequence(i + 1);// 序号
			allBillMoney = CommUtils.addForMoney(allBillMoney, saveBillInfo.getBillMoney());// 总金额
			beanList.add(pgBean);
		}
		
	
		savebillbatch = applydao.getSaveApplyInf(batchId);;
		headbean.setApplyNo(savebillbatch.getBatchNo());// 批次号
		headbean.setCustName(savebillbatch.getCustName());// 客户名称
		headbean.setCustNo(savebillbatch.getCustNo());//客户号 
		headbean.setBillClass(savebillbatch.getBatchClass());
		headbean.setBillClassString(savebillbatch.getBatchClass().equals("1")?"纸票":"电票");// 票据类型（实物、电子）
		headbean.setBillTypeString(savebillbatch.getBatchType().equals("1")?"银票":"商票");// 票据种类（银、商）
		headbean.setBillClass(savebillbatch.getBatchClass());
		headbean.setBillType(savebillbatch.getBatchType());
		headbean.setInPoolDt(savebillbatch.getCreateTime());// 申请存票日期
	
		
		headbean.setAllBillMoneyString(MoneyUtils.doubleToFormatStr(allBillMoney));// 总金额
		headbean.setNo(Long.valueOf(billlist.size()));// 票面总张数
		headbean.setDate(DateTimeUtil.get_YYYY_MM_DD_Date());
		
		Branch branch=branchService.getBranch(saveBillInfo.getBranchNo());
		headbean.setBrchName(branch.getBranchName());
		
		headbean.setTopic("质 押 "+handleType+" 清 单");
		
		headbean.setApplyDateName("质押申请日期：");
		//客户账号
		headbean.setCustAccount(savebillbatch.getCustAccount());
		//客户账号开户机构
		headbean.setCustBankName(branch.getBranchName());
	
		pibean.setItemList(beanList);
		pibean.setHeadPrintBean(headbean);
		list.add(pibean);
		
		return list;

	}

	// 属性复制，用公共BEAN接收清单信息
	private void copyPropertys(PiaoGuanBean pgBean, SaveBillInfo savebillinfo) {
		pgBean.setBillNo(MoneyUtils.billNoSubString(savebillinfo.getBillNo()));
		pgBean.setOutBillDate(savebillinfo.getIssueDt());
		pgBean.setEndBillDate(savebillinfo.getDueDt());
		
		pgBean.setOutBillPerson(savebillinfo.getRemitter());
		pgBean.setOutBillBank(savebillinfo.getRemitterBankName());
 
		pgBean.setBillAmountString(MoneyUtils.doubleToFormatStr(savebillinfo.getBillMoney()));// 票面金额（小写）
		pgBean.setReMark(savebillinfo.getSignRemark());// 备注
		pgBean.setAcceptor(savebillinfo.getAcceptor());
		pgBean.setBillClassString(savebillinfo.getBillClass().equals("1")?"纸票":"电票");
		pgBean.setBillTypeString(savebillinfo.getBillType().equals("1")?"银票":"商票");
		
		pgBean.setProdTypeString(savebillinfo.getProdNoString());
			
	}


}
