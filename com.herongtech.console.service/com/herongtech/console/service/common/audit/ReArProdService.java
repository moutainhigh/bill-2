package com.herongtech.console.service.common.audit;

import java.util.List;

import org.springframework.util.CollectionUtils;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.domain.common.audit.bean.ReArProd;
import com.herongtech.console.domain.common.audit.bean.ReArProdSearchBean;
import com.herongtech.console.domain.common.audit.dao.ReArProdDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.common.audit.interfaces.IAuditCommonService;
import com.herongtech.console.service.common.audit.interfaces.IReArProdService;


public class ReArProdService implements IReArProdService {

    private IAuditCommonService auditCommonService;
    
    @Override
    public List<ReArProd> queryReBrchProd(Page page,
            ReArProdSearchBean searchBean) throws Exception {
        ReArProdDao bpDao=new ReArProdDao();
        return bpDao.getReArProdBySeachBean(page, searchBean);
    }

    @Override
    public void addReBrchProd(ReArProd arProd) throws Exception {
        List<ReArProd> list=auditCommonService.getReBrchProdList(arProd.getBranchNo(), arProd.getProdNo());
        if (!CollectionUtils.isEmpty(list)) {
            throw new Exception("机构和产品对应的记录已存在，不允许重复添加");
        }
        arProd.setId(ServiceFactory.getSequenceService().getPrimaryKeyValue());
        ReArProdDao bpDao=new ReArProdDao();
        bpDao.addReArProd(arProd);
    }

    @Override
    public void editReBrchProd(ReArProd arProd) throws Exception {
        ReArProdDao bpDao=new ReArProdDao();
        bpDao.modifyReArProd(arProd);
    }

    @Override
    public void deleteReBrchProd(String id) throws Exception {
        ReArProdDao bpDao=new ReArProdDao();
        bpDao.deleteReArProd(id);
    }

    @Override
    public ReArProd getReBrchProdById(String id) throws Exception {
        ReArProdDao bpDao=new ReArProdDao();
        return bpDao.getReArProd(id);
    }

    @Override
    public boolean isNeedToAudit(String branchNo, String prodNo)
            throws Exception {
        return auditCommonService.isNeedToAudit(branchNo, prodNo);
    }

    
    public void setAuditCommonService(IAuditCommonService auditCommonService) {
        this.auditCommonService = auditCommonService;
    }

}
