/********************************************
 * 文件名称: NoticeService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: yanjl
 * 开发时间: 2016-6-15 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.service.notice;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.domain.bean.Notice;
import com.herongtech.console.domain.dao.NoticeDao;
import com.herongtech.console.service.interfaces.INoticeService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 公告信息参数取法
 *
 */

public class NoticeService implements INoticeService {

	@Override
	public Notice getNotice(String noticeNo) throws BizAppException {
		NoticeDao dao=new NoticeDao();
		try {
			return dao.getNotice(noticeNo);
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}

	/**
	 * 插入公告信息表
	 */
	@Override
	public void addNotice(Notice notice) throws BizAppException {
		NoticeDao dao=new NoticeDao();
		String date=DateTimeUtil.getWorkdayString();
		notice.setReleaseTime(date);
		try {
			if (dao.addNotice(notice) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加客户信息失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}

	}

	/**
	 * 修改公告信息表
	 */
	@Override
	public void modifyNotice(Notice notice) throws BizAppException {
		NoticeDao dao=new NoticeDao();
		try {
			if (dao.modifyNotice(notice) != 1) {
				 throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改客户信息失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}

	}
	
	/**
	 * 功能描述：查询公告列表
	 * @return
	 * @throws SQLException
	 */
	public List<Notice> getNotice(Page page,Notice notice) throws SQLException {
		NoticeDao dao=new NoticeDao();
		return dao.getNotice(page, notice);
	}
	
	
	/**
	 * 功能描述：删除公告信息
	 * @return
	 * @throws SQLException
	 */
	public int delNotice(String noticeNos) throws BizAppException {
		NoticeDao dao=new NoticeDao();
		try {
			return dao.delNotice(noticeNos);
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}

}
