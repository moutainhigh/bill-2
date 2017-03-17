package com.herongtech.console.web.syscontroller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.domain.acpt.bean.AcptBillInfo;
import com.herongtech.console.domain.acpt.bean.AcptQueryCondition;
import com.herongtech.console.domain.bean.Notice;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.bean.RemindDTO;
import com.herongtech.console.domain.subcoll.bean.SubcollQueryCondition;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.acpt.AcptService;
import com.herongtech.console.service.busiservice.acpt.IAcptService;
import com.herongtech.console.service.busiservice.subcoll.ISubcollService;
import com.herongtech.console.service.common.audit.interfaces.IAuditProcessService;
import com.herongtech.console.service.interfaces.INoticeService;
import com.herongtech.console.web.busicontroller.common.AcptCodeConst;
import com.herongtech.rc.domain.bean.RgctBillData;

/**
 * 
 * 提醒
 */
@Scope("prototype")
@Controller
@RequestMapping("/remindController")
public class RemindController extends BaseController {

    @RequestMapping(params = "method=loadDeductDrawee")
    @ResponseBody
    public String loadDeductDrawee(Page page) throws Exception {
        // 根据柜员所属机构查询该机构电票纸票即将到期票据按照到期日排序取前6条信息

        // 根据柜员判断该柜员是否拥有权限
    	/***************纸票到期扣款快捷方式***************/
        String result = "";
        IAcptService acptService = ServiceFactory.getAcptService();
        AcptService as= new AcptService();//日期结束调度程序实例化
        AcptQueryCondition acptQuery =new AcptQueryCondition();
        UserLoginfo user=ResourceUtil.getSessionLoginfo();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//规定日期格式
        Date dt=format.parse(DateTimeUtil.getWorkdayString());//获得当前营业日
        
        acptQuery.setBranchNo(user.getBranchNo());//取管理员的机构号
        acptQuery.setBillStatus(AcptCodeConst.BILL_STATUS_ISSUE);
        acptQuery.setBillClass(IDict.K_BILL_CLASS.K_ENTY_BILL);
        List<AcptBillInfo> listBill = acptService.getAcptBillListForBatch(page, acptQuery);

        long day=0;
        int size = 1;
        List<RemindDTO> list = new ArrayList<RemindDTO>();
        if (listBill != null) {
            for (AcptBillInfo acptBillInfo:listBill) {
            	Date dueDt= format.parse(acptBillInfo.getDueDt());
            	day=(dueDt.getTime()-dt.getTime())/(24*60*60*1000);
            	String remindDays = as.isRemindDays();//取缓存中的提醒日期常量
            	int r=Integer.parseInt(remindDays);//将返回的参数表示值强转为int类型
            	if(day>=0&&day<=r){
            		   RemindDTO remindDto = new RemindDTO();
                       remindDto.setUrl("gotoPDeductDrawee(" + acptBillInfo.getAcptId()+ ")");
                       remindDto.setRemindName("票号【" + acptBillInfo.getBillNo() + "】到期日【"
                               + acptBillInfo.getDueDt() + "】");
                       list.add(remindDto);
            	}
            }
        }
        result = result.concat(createHtmlByData("green", "纸票到期扣款", list));
        /***************纸票到期扣款快捷方式***************/
        
        /***************电票到期扣款快捷方式***************/
        acptQuery.setBranchNo(user.getBranchNo());//取管理员的机构号
        acptQuery.setBillStatus(AcptCodeConst.BILL_STATUS_ISSUE);
        acptQuery.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
        List<AcptBillInfo> listBill1 = acptService.getAcptBillListForBatch(page, acptQuery);
        List<RemindDTO> list1 = new ArrayList<RemindDTO>();
        if (listBill1 != null) {
            for (AcptBillInfo acptBillInfo:listBill1) {
            	Date dueDt= format.parse(acptBillInfo.getDueDt());
            	day=(dueDt.getTime()-dt.getTime())/(24*60*60*1000);
            	String remindDays = as.isRemindDays();//取缓存中的提醒日期常量
            	int r=Integer.parseInt(remindDays);
            	if(day>=0&&day<=r){
                   RemindDTO remindDto = new RemindDTO();
                   remindDto.setUrl("gotoEDeductDrawee(" + acptBillInfo.getAcptId()+ ")");
                   remindDto.setRemindName("票号【" + acptBillInfo.getBillNo() + "】到期日【"
                        + acptBillInfo.getDueDt() + "】");
                list1.add(remindDto);
          	  }
            }
        }
        result = result.concat(this.createHtmlByData("green", "电票到期扣款", list1));
        /***************电票到期扣款快捷方式***************/
        
        /***************待发托票据快捷方式start***************/ 
       list = new ArrayList<RemindDTO>();
        ISubcollService subcollService = ServiceFactory.getSubcollService();
        SubcollQueryCondition query = new SubcollQueryCondition();
        String wokeDay = DateTimeUtil.getWorkdayString();//获得当前营业日
        String endDay = DateTimeUtil.getDate(wokeDay, 10);
        query.setEndDay(endDay);
        String billClass = IDict.K_BILL_CLASS.K_ELEC_BILL;
        query.setBillClass(billClass);
        List<RgctBillData> rgcBillData = subcollService.getSubcollBillForPrint(new Page(),query);
        if(rgcBillData != null){
        	 for (RgctBillData bill:rgcBillData) {
                 RemindDTO remindDto = new RemindDTO();
                 query.setBillNo(bill.getBillNo());
                 query.setBillClass(bill.getBillClass());
                 remindDto.setUrl("gotoPSubColl('"+bill.getBillNo()+"',"+bill.getBillClass()+")");
                 remindDto.setRemindName("票号【"+bill.getBillNo()+"】到期日【"
                         + bill.getDueDt() + "】");
                 list.add(remindDto);
             }
        }
        result = result.concat(this.createHtmlByData("green", "待发托", list));
        /***************待发托票据快捷方式end***************/
        
      /*  list = new ArrayList<RemindDTO>();
        listBill = new ArrayList();
        ;
        for (int i = 0; i < size; i++) {
            RemindDTO remindDto = new RemindDTO();
            remindDto.setUrl("gotoESubColl()");
            remindDto.setRemindName("票号【12342222212121】到期日【"
                    + DateTimeUtil.get_YYMMDD_Date() + "】");
            list.add(remindDto);
        }

        result = result.concat(this.createHtmlByData("green", "待提示付款", list));*/
        return result;
    }

    @RequestMapping(params = "method=loadNotice")
    @ResponseBody
    public String loadNotice(Page page) throws Exception {
        // 查询公告信息
    	INoticeService noticeService = ServiceFactory.getNoticeService();
    	page.setShowCount(5);
    	List<Notice> listBill = noticeService.getNotice(page, null);
        List<RemindDTO> list = new ArrayList<RemindDTO>();
        if (listBill != null) {
        	for(Notice notice:listBill){
        		 RemindDTO remindDto = new RemindDTO();
                 remindDto.setUrl("gotoNotice(" + notice.getNoticeNo() + ")");
                 remindDto.setDueDt(notice.getReleaseTime());
                 remindDto.setRemindName(notice.getNoticeName());
                 list.add(remindDto);
        	}
        }

        return this.createHtmlByData("blue", null, list);
    }

    @RequestMapping(params = "method=loadAudit")
    @ResponseBody
    public String loadAudit() throws Exception {
        // 查询待审核信息
        IAuditProcessService auditService = ServiceFactory.getAuditProcessService();
        List<RemindDTO> list = auditService.queryAuditProcessByUserNo(ResourceUtil.getSessionLoginfo());
        return this.createHtmlByData("red", "待审批", list);
    }

    @RequestMapping(params = "method=loadDeductRdscnt")
    @ResponseBody
    public String loadDeductRdscnt() throws Exception {
        List<RemindDTO> list = new ArrayList<RemindDTO>();
        String result = "";
        List listBill = new ArrayList(2);
        int size = 2;
        if (listBill != null) {
            for (int i = 0; i < size; i++) {
                RemindDTO remindDto = new RemindDTO();
                remindDto.setUrl("gotoPSaleBack()");
                remindDto.setRemindName("批次号【FS"
                        + DateTimeUtil.get_YYMMDD_Date() + i + "】返售到期日【"
                        + DateTimeUtil.get_YYMMDD_Date() + "】");
                list.add(remindDto);
            }
        }
        result = result.concat(this.createHtmlByData("green", "纸票转入返售", list));
        list = new ArrayList<RemindDTO>();
        listBill = new ArrayList(2);
        if (listBill != null) {
            for (int i = 0; i < size; i++) {
                RemindDTO remindDto = new RemindDTO();
                remindDto.setUrl("gotoESaleBack()");
                remindDto.setRemindName("批次号【FS"
                        + DateTimeUtil.get_YYMMDD_Date() + i + "】返售到期日【"
                        + DateTimeUtil.get_YYMMDD_Date() + "】");
                list.add(remindDto);
            }
        }
        result = result.concat(this.createHtmlByData("green", "电票转入返售", list));
        list = new ArrayList<RemindDTO>();

        listBill = new ArrayList(2);
        if (listBill != null) {
            for (int i = 0; i < size; i++) {
                RemindDTO remindDto = new RemindDTO();
                remindDto.setUrl("gotoPBuyBack()");
                remindDto.setRemindName("批次号【HG"
                        + DateTimeUtil.get_YYMMDD_Date() + i + "】赎回到期日【"
                        + DateTimeUtil.get_YYMMDD_Date() + "】");
                list.add(remindDto);
            }
        }
        result = result.concat(this.createHtmlByData("green", "纸票到期赎回", list));
        list = new ArrayList<RemindDTO>();
        listBill = new ArrayList(2);
        if (listBill != null) {
            for (int i = 0; i < size; i++) {
                RemindDTO remindDto = new RemindDTO();
                remindDto.setUrl("gotoEBuyBack()");
                remindDto.setRemindName("批次号【HG"
                        + DateTimeUtil.get_YYMMDD_Date() + i + "】赎回到期日【"
                        + DateTimeUtil.get_YYMMDD_Date() + "】");
                list.add(remindDto);
            }
        }
        result = result.concat(this.createHtmlByData("green", "电票到期赎回", list));
        return result;
    }

    /**
     * 根据数据获取HTML字符串拼装信息 <br>
     * 例：<li><span class="red">待审批</span><a
     * href="#">任务编号【1590165】产品名称【贴现】需要处理</a></li>
     * 
     * @param color
     *            标题显示颜色
     * @param remindName
     *            后标签名称
     * @param list
     *            超链接信息集合
     * @return HTML拼装串
     * @throws ServiceException
     */
    private String createHtmlByData(String color, String remindName,
            List<RemindDTO> list) throws Exception {
        String liString = "";
        for (int i = 0; i < list.size(); i++) {
            RemindDTO remindDto = list.get(i);
            String titleString = remindName == null ? remindDto.getDueDt()
                    : remindName;
            liString = liString + "<li><span class=\"" + color + "\">"
                    + titleString + "</span><a href=\"#\" onclick=\""
                    + remindDto.getUrl() + "\">" + remindDto.getRemindName()
                    + "</a></li>";
        }
        return liString;
    }
}
