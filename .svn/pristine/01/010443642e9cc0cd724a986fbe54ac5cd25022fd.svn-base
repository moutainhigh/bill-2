/********************************************
 * 文件名称: SequenceCache.java
 * 系统名称: 基础技术平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 多语言缓存类
 * 系统版本: 2.0.0.1
 * 开发人员: yanjl
 * 开发时间: 2016-6-15 上午08:39:00
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期     修改人员        修改说明
 * 20160615-01   yanjl  创建  
 *********************************************/

package com.herongtech.console.cache;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.herongtech.console.domain.bean.Serialno;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;

/**
 * 序列号管理类
 * 
 * @author Administrator
 * 
 */
public class SequenceCache {
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20160615 @describe ";
	
	//当前序列号
	private long currSerial = 0;
	//最大序列号
	private long maxSerial = 0;
	//序列号对象集合
	private static Map<String, SequenceCache> seqInstances = new HashMap<String, SequenceCache>();
	//序列号键值
	private String keyNo = "";
	
	/**
	 * 
	 * @param keyNo
	 */
	private SequenceCache(String keyNo) {
		this.keyNo = keyNo;

	}

	/**
	 * 初始化序列号实例
	 * 
	 * @param keyNo
	 * @return
	 */
	public static SequenceCache getInstance(String keyNo) {
		SequenceCache instance = (SequenceCache) seqInstances.get(keyNo);
		if (instance == null) {
			synchronized (seqInstances) {
				instance = (SequenceCache) seqInstances.get(keyNo);
				
				if (instance == null){
					IDB db = null;
					try {
						db = DBFactory.getNewDB(); // 获取数据库连接
						int rowCount = db
							.account("select count(*) rowcnt from tserialno where key_no='"
									+ keyNo + "'");
						if (rowCount == 0) {
							throw new java.lang.RuntimeException(
								"流水表tserialno未配置keyno[" + keyNo + "]");
						}
					} catch (SQLException e) {
						e.printStackTrace();
						throw new java.lang.RuntimeException(e.getMessage());
					} finally {
						try {
							DBFactory.closeDB(db);
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					instance = new SequenceCache(keyNo);
					seqInstances.put(keyNo, instance);
				}
			}

		}
		return instance;
	}
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public synchronized long getSequenceNo() throws SQLException {
		
		currSerial++;
		if (currSerial <= maxSerial) {
			return currSerial;
		}
		
		IDB session = null;
		try {
			session = DBFactory.getNewDB(); // 获取数据库连接
			session.beginTransaction();

			session.execute(
							"update tserialno set curr_value = curr_value + inc where key_no = ?",
							this.keyNo);

			Serialno serialno = session.getObject(
					"select * from tserialno where key_no=?", Serialno.class,
					this.keyNo);

			//增加逻辑判断，若流水号超出当天最大流水号自动归0，重新计算
			maxSerial = serialno.getCurrValue();
			currSerial = maxSerial - serialno.getInc() + 1;
			if (currSerial <= serialno.getMaxValue()
					&& maxSerial > serialno.getMaxValue()) {
				maxSerial = serialno.getMaxValue();
			} else if (maxSerial > serialno.getMaxValue()
					&& currSerial > serialno.getMaxValue()) {

				session.execute(
								"update tserialno  set curr_value = min_value+inc   where key_no=?",
								this.keyNo);

				currSerial = serialno.getMinValue();
				maxSerial = serialno.getMinValue() + serialno.getInc();

			}

			session.endTransaction();
			return currSerial;
		} catch (SQLException e) {
			e.printStackTrace();
			session.rollback();
			throw e;
		} finally {
			try {
				DBFactory.closeDB(session);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
	
	
	/**
	 * 批量从数据库中顺序读取流水号,inc为获取流水的个数, 不影响原先预先加载到内存的流水号; 返回值为当前最大的流水号
	 * @throws SQLException
	 */
	public long getBatchSequenceNo(int inc) throws SQLException {

		IDB db = null;
		try {
			db = DBFactory.getNewDB(); // 获取数据库连接
			
			db.beginTransaction();
			db.execute("update tserialno set curr_value = curr_value + " + inc + " where key_no = ?",
							this.keyNo);

			Serialno sequenceRec = db.getObject(
					"select * from tserialno where key_no=?", Serialno.class,
					this.keyNo);

			// 增加逻辑判断，若流水号超出当天最大流水号自动归0，重新计算
			long serialNo = sequenceRec.getCurrValue();
			
			if (serialNo > sequenceRec.getMaxValue()) {
				db.execute("update tserialno set curr_value = min_value+"+inc+"   where key_no=?",
								this.keyNo);
				serialNo = sequenceRec.getMinValue() + inc;
			}
			db.endTransaction();
			
			return serialNo;
		} catch (SQLException e) {
			e.printStackTrace();
			db.rollback();
			throw e;
		} finally {
			try {
				DBFactory.closeDB(db);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
	
	/**
	 * 清理缓存
	 */
	public static void destroy(){
		synchronized (seqInstances) {
			seqInstances.clear();
		}
	}
}
