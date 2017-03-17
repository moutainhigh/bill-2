package com.herongtech.console.service.common.dayend.task;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.domain.acct.bean.AcctBalance;
import com.herongtech.console.domain.acct.bean.AcctBalanceHis;
import com.herongtech.console.domain.acct.dao.AcctBalanceDao;
import com.herongtech.console.domain.acct.dao.AcctBalanceHisDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.common.dayend.DayEndBaseService;
import com.herongtech.console.service.interfaces.ISequenceService;
import com.herongtech.context.Context;
import com.herongtech.log.CommonLog;


public class BalanceExportService extends DayEndBaseService {

    @Override
    public String getServiceId() {
        return "DAYEND-balanceExport";
    }

    @Override
    protected void action(Context arg0) throws Exception {
    	CommonLog.getCommonLogCache().infoMessage("余额结转任务开始=======");
    	AcctBalanceHisDao balanceHisDao = new AcctBalanceHisDao();
    	List<AcctBalance> balanceList = new AcctBalanceDao().getAllAcctBalance();
    	ISequenceService sequenceService = ServiceFactory.getSequenceService();
    	for(AcctBalance balance : balanceList){
    		AcctBalanceHis balanceHis = new AcctBalanceHis();
    		BeanUtils.copyProperties(balanceHis, balance);
    		balanceHis.setHisId(sequenceService.getAcctBalanceHisId());
    		balanceHis.setBusiDt(DateTimeUtil.getWorkday());
    		balanceHisDao.addAcctBalanceHis(balanceHis);
    	}
    	CommonLog.getCommonLogCache().infoMessage("余额结转任务结束=======");
    }

    @Override
    public String getTransName() {
        return "余额结转";
    }

    @Override
    public String getTransVersion() {
        return "1.0";
    }

}
