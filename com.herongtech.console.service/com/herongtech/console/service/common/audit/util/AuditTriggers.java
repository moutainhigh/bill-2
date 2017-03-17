package com.herongtech.console.service.common.audit.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("triggers")
public class AuditTriggers {
    @XStreamImplicit
    private List<AuditTrigger> mapping;

    public List<AuditTrigger> getMapping() {
        if (null==mapping) {
            mapping = new ArrayList<AuditTrigger>();
        }
        return mapping;
    }
    public void setMapping(List<AuditTrigger> mapping) {
        this.mapping = mapping;
    }
    
    /**
     * 根据产品号获取Mapping对象值
     * @param prodId    产品号
     * @return
     */
    public AuditTrigger getMapping(String prodNo) {
        if (null!=prodNo) {
            for (Iterator<AuditTrigger> ite=mapping.iterator();ite.hasNext();) {
                AuditTrigger mapping=ite.next();
                if (prodNo.equals(mapping.getProdNo())) {
                    return mapping;
                }
            }
        }
        return null;
    }
}
