/********************************************
 * 文件名称: INoticeService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: fqq
 * 开发时间: 2016-9-22 
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.service.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.domain.bean.Notice;
import com.herongtech.exception.impl.BizAppException;

public interface INoticeService {

	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 获取公告信息参数
	 * 
	 * @param noticeNo 公告信息标识
	 */
    public  Notice getNotice(String noticeNo) throws BizAppException;
	
	/**
	 * 插入公告信息表
	 *
	 */
	public void addNotice(Notice notice)throws BizAppException;
	
	/**
	 * 修改公告信息表
	 */
	
	public void modifyNotice(Notice notice)throws BizAppException;
	
	
	/**
	 * 删除公告信息表
	 *
	 */
	public int delNotice(String noticeNos)throws BizAppException;
	
	/**
	 * 功能描述：查询公告列表
	 * @return
	 * @throws SQLException
	 */
	public List<Notice> getNotice(Page page,Notice notice) throws SQLException;
	
}
