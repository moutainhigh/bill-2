package com.herongtech.console.cache;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.herongtech.console.domain.common.bean.MachineStatus;
import com.herongtech.console.domain.common.dao.MachineStatusDao;

import com.herongtech.log.CommonLog;

/**状态机缓存类 ljt*/
public class MachineStatusCache {

	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20160615 @describe ";
	private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private final Lock r = rwl.readLock();
	private final Lock w = rwl.writeLock();
	private Map<String,MachineStatus> machineStatusmap = new HashMap<String,MachineStatus>();
	private static MachineStatusCache machineStatusCache = null;
	
	private MachineStatusCache(){
		init();
	}
	/**初始化缓存类*/
	private void init(){
		MachineStatusDao dao = new MachineStatusDao();
		try {
			List<MachineStatus> MachineStatuslist = dao.getMachineStatusList();
			for (int i = 0; i < MachineStatuslist.size(); i++) {
				MachineStatus machine = MachineStatuslist.get(i);
				String key = machine.getControllerName()+machine.getMethodName()+machine.getParamValue();
				machineStatusmap.put(key,MachineStatuslist.get(i));
			}			
		} catch (SQLException e) {
			e.printStackTrace();
			CommonLog.getCommonLogNoCache().errorMessage("初始化失败",e);
		}
	
	}
	
	/**获取状态机缓存类*/
	public static MachineStatusCache getInstance(){
		if(machineStatusCache==null){
			machineStatusCache = new MachineStatusCache();
		}
		return machineStatusCache;
	}
	
	/**获取状态机缓存信息*/
	public MachineStatus getmachinemapvalue(String key){
		try {
			r.lock();
			MachineStatus value = machineStatusmap.get(key);
			if(null==value){
				return new MachineStatus();
			}else{
				return value;			
			}
		}finally{
			r.unlock();			
		}
	}
	
	/**刷新缓存*/
	public void refresh() {
		try {
			w.lock();
			machineStatusmap.clear();
			init();
		} finally {
			w.unlock();
		}
	}
	
	/**清除缓存*/
	public static void clearmap(){
		if(machineStatusCache!=null){
			machineStatusCache.machineStatusmap.clear();	
		}
		machineStatusCache=null;
	}
	
}
