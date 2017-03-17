package com.herongtech.rc.service.ecdsbankdata;

import java.sql.SQLException;

import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.EcdsAuthlistData;
import com.herongtech.rc.domain.dao.EcdsAuthlistDataDao;
import com.herongtech.rc.service.interfaces.IEcdsAuthorityService;

public class IEcdsAuthorityServiceImpl implements IEcdsAuthorityService{

	@Override
	public void AddOrUpdateAuthority(EcdsAuthlistData ecdsauthlistData) throws BizAppException{
		
		EcdsAuthlistDataDao dao = new EcdsAuthlistDataDao();
		EcdsAuthlistData ecdsauth;
		IDB session = DBFactory.getDB();
		try {
			 session.beginTransaction();
			ecdsauth = dao.getEcdsAuthlistData(ecdsauthlistData.getActorRowNumber());
			if(ecdsauth==null){                                //数据库中是否有该数据
				dao.addEcdsAuthlistData(ecdsauthlistData);     //添加
			}else{
				dao.modifyEcdsAuthlistData(ecdsauthlistData);  //更新		
			}
			session.endTransaction();
		} catch (SQLException e) {
            try {
                session.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

		
	}

}
