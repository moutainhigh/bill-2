package com.herongtech.console.service.common.dayend.task;

import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.domain.acpt.bean.AcptApplyInfo;
import com.herongtech.console.domain.acpt.bean.AcptQueryCondition;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.acpt.IAcptService;
import com.herongtech.console.service.common.dayend.DayEndBaseService;
import com.herongtech.console.web.busicontroller.common.AcptCodeConst;
import com.herongtech.context.Context;
import com.herongtech.log.CommonLog;


public class AcptDeductService extends DayEndBaseService {

    @Override
    public String getServiceId() {
        return "DAYEND-acptDeduct";
    }

    @Override
    protected void action(Context arg0) throws Exception {
 //   	UserLoginfo user=ResourceUtil.getSessionLoginfo();
    	IAcptService acptService = ServiceFactory.getAcptService();
    	AcptQueryCondition query=new  AcptQueryCondition();
//		query.setBranchNo(user.getBranchNo());//取管理员的机构号
		String dt=DateTimeUtil.getLastWorkdayString();//获得上一个营业日
		query.setWorkday(dt);
		query.addSqlPropretyMapping("workday", "dueDt");
		query.setBillStatus(AcptCodeConst.BILL_STATUS_ISSUE);
		query.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
		Page page=new Page();
		List<AcptApplyInfo> batchList = acptService.getAcptApplyListForCondition(page,query);
		for(AcptApplyInfo batch : batchList){
			query.setAcptId(batch.getAcptId());
			acptService.dueChargeBillForAcptmxId(query);
    	}
		
        // TODO 银承到期扣款日终任务 待实现
    	CommonLog.getCommonLogCache().infoMessage("银承到期扣款日终任务");

    }

    @Override
    public String getTransName() {
        return "银承到期扣款日终";
    }

    @Override
    public String getTransVersion() {
        return "1.0";
    }

}
