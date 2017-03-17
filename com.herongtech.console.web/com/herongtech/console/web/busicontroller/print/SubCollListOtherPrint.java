package com.herongtech.console.web.busicontroller.print;

import java.util.ArrayList;
import java.util.List;

import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.console.domain.subcoll.bean.SubcollApplyInfo;
import com.herongtech.console.domain.subcoll.bean.SubcollBillInfo;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.subcoll.ISubcollService;
import com.herongtech.console.service.interfaces.IBranchService;

/**
 * @author 李江涛
 * 托收清单打印     根据tsubcoll_bill_info 主键subcollmxId查询出结果
 */
public class SubCollListOtherPrint extends AbstractPrint{

	@Override
	public List<PublicItemBean<SubBean>> getPrintList(String ids, String batchId,String handleType) throws Exception {//参数：清单id和批次id
		List<PublicItemBean<SubBean>> list = new ArrayList<PublicItemBean<SubBean>>();
		PublicItemBean<SubBean> piBean = new PublicItemBean<SubBean>();// 显示subBean
		HeadPrintBean headbean = new HeadPrintBean();// 头信息（批次信息）BEAN
		List<SubBean> beanList = new ArrayList<SubBean>();
		ISubcollService service =ServiceFactory.getSubcollService();
		//批次
		IBranchService batchSer= ServiceFactory.getBranchService();
		int size=0;
		double totlAmount=0.0;
		SubcollBillInfo billInfo = null;
		/**
		 * 优先使用批次 查找打印记录（发托 新增 里面的打印清单使用）
		 * 
		 */
			//Long[] id = AcptPublicMethod.conversion(Long.parseLong(ids));
			String[] id = ids.split(",");
			size = id.length;
			for (int i = 0; i < id.length; i++) {
				billInfo = service.getSubcollBillInfobyid(id[i]);
				headbean.setBillClass(billInfo.getBillClass());
				headbean.setBillClassString(billInfo.getBillClassString());//票据类型		
				headbean.setBillTypeString(billInfo.getBillTypeString());//票据品种
				headbean.setBillClass(billInfo.getBillClass());
				headbean.setBillType(billInfo.getBillType());
				batchId=billInfo.getSubcollId();//批次号
				SubBean subBean = new SubBean();
				copyPropertys(subBean, billInfo);
				subBean.setSequence(i + 1);// 序号
				if("1".equals(subBean.getOnlineMark())){
					headbean.setQsTypeString("线上");
				}else{
					headbean.setQsTypeString("线下");					
				}
				//总金额
				totlAmount = CommUtils.addForMoney(totlAmount,subBean.getBillAmount());
				beanList.add(subBean);
			}
		
		headbean.setDate(DateTimeUtil.getWorkday());
		if(billInfo.getCollDate()!=null){
			headbean.setDate(billInfo.getCollDate());
		}
		SubcollApplyInfo collBatch = service.getSubcollApplyInfobyid(batchId);
		if(collBatch!=null){
			headbean.setApplyNo(collBatch.getSubcollId());
		}
		headbean.setNo(Long.valueOf(size));
		headbean.setAllBillMoneyString(CommUtils.NumberFormat3(totlAmount));
		headbean.setTopic("托  收  清  单");
		piBean.setItemList(beanList);
		piBean.setHeadPrintBean(headbean);
		list.add(piBean);
		return list;
	}

	public void copyPropertys(SubBean subBean, SubcollBillInfo billInfo) {
		String custName = billInfo.getCustName();
		subBean.setBillClass(billInfo.getBillClass());
		subBean.setBillType(billInfo.getBillType());
		//票号
		subBean.setBillNo(MoneyUtils.billNoSubString(billInfo.getBillNo()));
		// 票面金额
		subBean.setBillAmount(billInfo.getBillMoney());
		subBean.setBillAmountString(CommUtils.NumberFormat3(billInfo.getBillMoney()));
		// 票面到期日
		subBean.setEndBillDate(billInfo.getDueDt());
		//计息到期日
		if (null == billInfo.getGaleDate()) {
			subBean.setGaleDate("");// 计息到期日
		} else {
			subBean.setGaleDate(billInfo.getGaleDate());// 计息到期日
		}
		//票据来源
		subBean.setBillSource(billInfo.getBillSource());
		subBean.setBillSourceString(billInfo.getBillSourceString());
		//客户名称
		subBean.setCustName(custName);
		//承兑人
		subBean.setAcceptor(billInfo.getAcceptor());
		// 付款人开户行
		subBean.setOutBillBankNo(billInfo.getDraweeBankName());
		//付款人开户银行支付系统号
		subBean.setDaNo(billInfo.getAcceptorBankNo());
		//清算标识  0 线下 1 线上
		subBean.setOnlineMark(billInfo.getIsOnline());
	}
}
