package com.herongtech.console.service.common.audit.interfaces;

import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.domain.common.audit.bean.ReArProd;
import com.herongtech.console.domain.common.audit.bean.ReArProdSearchBean;


public interface IReArProdService {
    
    /**
     * 查询审批机构产品信息
     *
     * @param page
     * @param searchBean
     * @return
     * @throws Exception
     */
    public List<ReArProd> queryReBrchProd(Page page,ReArProdSearchBean searchBean) throws Exception ;

    /**
     * 添加审批机构产品信息
     *
     * @param arProd
     * @throws Exception
     */
    public void addReBrchProd(ReArProd arProd) throws Exception ;

    /**
     * 编辑审批机构产品信息
     *
     * @param requestDto
     * @param reBrchProdDto
     * @throws Exception
     */
    public void editReBrchProd(ReArProd arProd) throws Exception ;

    /**
     * 删除审批机构产品信息
     *
     * @throws Exception
     */
    public void deleteReBrchProd(String id) throws Exception ;
    
    /**
     * 根据id获取审批机构产品信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    public ReArProd getReBrchProdById(String id) throws Exception ;
    
    /**
     * 根据机构号和产品号判断是否需要审批 
     * 
     * @param branchNo
     *            机构号
     * @param prodNo
     *            产品号
     * @return true - 需要审批 <br>
     *         false - 不需要审批
     * @throws Exception
     */
    public boolean isNeedToAudit(String branchNo, String prodNo) throws Exception;
    
    
}
