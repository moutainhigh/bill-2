package com.herongtech.rc.trans.trans056;

import java.util.List;




import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.event.central.Node;
import com.herongtech.rc.domain.bean.EcdsAuthlistData;
import com.herongtech.rc.draft.common.vo.DraftAuthority;
import com.herongtech.rc.draft.common.vo.RequestInfo;
import com.herongtech.rc.draft.common.vo.SysMgrInfoVo;
import com.herongtech.rc.service.ecdsbankdata.IEcdsAuthorityServiceImpl;
import com.herongtech.rc.service.interfaces.IEcdsAuthorityService;
import com.herongtech.rc.trans.EcdsBaseService;


/**权限变更通知报文*/
public class T056Service extends EcdsBaseService {


    @Override
    protected void action(Context context) throws Exception {
    	 RequestInfo req = (RequestInfo)ContextUtil.getRequestData(context);
         SysMgrInfoVo vo=(SysMgrInfoVo)req;
         List<DraftAuthority> list = vo.getAuthority();
    	if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				
				// 业务参于行号
				String  node = list.get(i).getPtcptAcctSvcr();
				String actorRowNumber = (node != null ? node : null);
				// 发起业务权限列表
				String nodeSnd = list.get(i).getSndAuthrtyList();
				String launch = (nodeSnd != null ? nodeSnd : null);
				// 接收业务权限列表
				String nodeRcv = list.get(i).getRcvAuthrtyLis();
				String incept = (nodeRcv != null ? nodeRcv : null);
				EcdsAuthlistData ecdsAuthlistData = new EcdsAuthlistData();
				ecdsAuthlistData.setActorRowNumber(actorRowNumber);
				ecdsAuthlistData.setLaunchPurviewList(launch);
				ecdsAuthlistData.setInceptPurviewList(incept);
				// 保存或更新
				IEcdsAuthorityService authorityservice = new IEcdsAuthorityServiceImpl();
				
	            authorityservice.AddOrUpdateAuthority(ecdsAuthlistData);
	             
			}
		}

    }

    @Override
    public String getTransName() {
        return "056业务权限 变更";
    }

    @Override
    public String getTransVersion() {
        return "2.0.0.1";
    }
    
    @Override
    public String getServiceId() {
        return "Proc056";
    }

}
