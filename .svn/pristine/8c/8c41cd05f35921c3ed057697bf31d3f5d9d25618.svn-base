package com.herongtech.rc.draft.common.vo;

import java.util.Date;
import java.util.List;


/**
 * 系统管理报文构造/解析对象
 *
 */
public class SysMgrInfoVo extends RequestInfo{
	/**039报文属性*/
	private Integer id;       
	private Date receiveTime;     //报文发送/接受时间
    private String borderLevel;   //广播级别
    private String contents;      //报文内容
    private String sendBankNo;    //发起行行号
    private String receiveBankNo; //接受行行号
    private String status;        //发送接收处理状态 S0-发送成功 R0-接受未处理 R1-接受已处理
    private Date startDate; 
    private Date endDate;
	
	
    
    /**051报文属性*/
    private String actnTpMk;    //登录登出操作类型(051/052)
    private String orgnlIdCd;   //原识别码
    private String idCd;        //新识别码
    /**054/052报文属性*/
   
    private String sysSts;      //系统状态（052/054报文字段）
    private String sysDt;     //系统当前日期（052/054报文字段）
    private String orgnlSysDt;  //原系统日期
    private String orgnlSysSts; //原系统状态
    private String nxtSysDt;    //下一系统工作日期（054,060）
    private String bizRefTm;    //营业参考时间

    /**053报文属性*/
    private String msgCnts;     //强制退出登录报文内容
    
    /**055报文属性*/
    private List<DraftCommonData> cmonDt;        //公共数据变更信息 
    /**056报文属性*/
    private List<DraftAuthority> authority;      //业务权限变更信息

    /**058报文属性*/
    private Date fctvDt;            //生效日期(064)
    private String orgnlPtcptRole;  //原参与者（被承接行）类别 
    private String orgnlPtcptNm;    //原参与者（被承接行）名称    
    private String orgnlPtcptCmonId;//原参与者（被承接行）组织机构代码    
    private String orgnlPtcptBankNo;//原参与者（被承接行）行号
    private String curPtcptRole;    //新参与者（承接行）类别 
    private String curPtcptNm;      //新参与者（承接行）名称    
    private String curPtcptCmonId;  //新参与者（承接行）组织机构代码    
    private String curPtcptBankNo;  //新参与者（承接行）行号
    /**059*/
    private String termNb;          //期数
    private List<DraftBankInformation> bankInf;           //行号变更信息
    
    /**060*/
    private String sttlmOnlineMrk;  //线上清算标识
    private String sttlmRmrk;       //附言
    
    /**062*/
    private List<DraftAccessPoints> AccssPtsInf;

    /**063*/
    private List<DraftAccessPointAndParticipants> AccessPointAndParticipants;
    

	/**064*/
    private String ptcptTp;         //节点类别 01NPC，02 CCPC
    private String ptcptNb;         //节点号
    private String ptcptSts;        //系统状态 0:故障，1：生效前，2：正常，3：注销
    private String fctvTp;          //生效类型 1：立即生效，2：指定日生效
    
    
    /**065*/
    private String altrnTp;         //变更类型  AB00 新增， AB01 删除
    private String rmrk;            //附言
    private List<String> ptcpt;     //行号
    
    /**066*/
    List<BindingDetail> bindList;
    
    private String switchTp;        //开关种类
    private String switchOnOffMk;   //开关标识
    
    
    public String getActnTpMk() {
        return actnTpMk;
    }

    
    public void setActnTpMk(String actnTpMk) {
        this.actnTpMk = actnTpMk;
    }

    
    public String getOrgnlIdCd() {
        return orgnlIdCd;
    }

    
    public void setOrgnlIdCd(String orgnlIdCd) {
        this.orgnlIdCd = orgnlIdCd;
    }

    
    public String getIdCd() {
        return idCd;
    }

    
    public void setIdCd(String idCd) {
        this.idCd = idCd;
    }

    
    public String getSysSts() {
        return sysSts;
    }

    
    public void setSysSts(String sysSts) {
        this.sysSts = sysSts;
    }

    
    public String getSysDt() {
        return sysDt;
    }

    
    public void setSysDt(String sysDt) {
        this.sysDt = sysDt;
    }

    
    public String getOrgnlSysDt() {
        return orgnlSysDt;
    }

    
    public void setOrgnlSysDt(String orgnlSysDt) {
        this.orgnlSysDt = orgnlSysDt;
    }

    
    public String getOrgnlSysSts() {
        return orgnlSysSts;
    }

    
    public void setOrgnlSysSts(String orgnlSysSts) {
        this.orgnlSysSts = orgnlSysSts;
    }

    
    public String getNxtSysDt() {
        return nxtSysDt;
    }

    
    public void setNxtSysDt(String nxtSysDt) {
        this.nxtSysDt = nxtSysDt;
    }

    
    public String getBizRefTm() {
        return bizRefTm;
    }

    
    public void setBizRefTm(String bizRefTm) {
        this.bizRefTm = bizRefTm;
    }

    
    public String getMsgCnts() {
        return msgCnts;
    }

    
    public void setMsgCnts(String msgCnts) {
        this.msgCnts = msgCnts;
    }

    
    public List<DraftCommonData> getCmonDt() {
        return cmonDt;
    }

    
    public void setCmonDt(List<DraftCommonData> cmonDt) {
        this.cmonDt = cmonDt;
    }

    
    public List<DraftAuthority> getAuthority() {
        return authority;
    }

    
    public void setAuthority(List<DraftAuthority> authority) {
        this.authority = authority;
    }

    
    public Date getFctvDt() {
        return fctvDt;
    }

    
    public void setFctvDt(Date fctvDt) {
        this.fctvDt = fctvDt;
    }

    
    public String getOrgnlPtcptRole() {
        return orgnlPtcptRole;
    }

    
    public void setOrgnlPtcptRole(String orgnlPtcptRole) {
        this.orgnlPtcptRole = orgnlPtcptRole;
    }

    
    public String getOrgnlPtcptNm() {
        return orgnlPtcptNm;
    }

    
    public void setOrgnlPtcptNm(String orgnlPtcptNm) {
        this.orgnlPtcptNm = orgnlPtcptNm;
    }

    
    public String getOrgnlPtcptCmonId() {
        return orgnlPtcptCmonId;
    }

    
    public void setOrgnlPtcptCmonId(String orgnlPtcptCmonId) {
        this.orgnlPtcptCmonId = orgnlPtcptCmonId;
    }

    
    public String getOrgnlPtcptBankNo() {
        return orgnlPtcptBankNo;
    }

    
    public void setOrgnlPtcptBankNo(String orgnlPtcptBankNo) {
        this.orgnlPtcptBankNo = orgnlPtcptBankNo;
    }

    
    public String getCurPtcptRole() {
        return curPtcptRole;
    }

    
    public void setCurPtcptRole(String curPtcptRole) {
        this.curPtcptRole = curPtcptRole;
    }

    
    public String getCurPtcptNm() {
        return curPtcptNm;
    }

    
    public void setCurPtcptNm(String curPtcptNm) {
        this.curPtcptNm = curPtcptNm;
    }

    
    public String getCurPtcptCmonId() {
        return curPtcptCmonId;
    }

    
    public void setCurPtcptCmonId(String curPtcptCmonId) {
        this.curPtcptCmonId = curPtcptCmonId;
    }

    
    public String getCurPtcptBankNo() {
        return curPtcptBankNo;
    }

    
    public void setCurPtcptBankNo(String curPtcptBankNo) {
        this.curPtcptBankNo = curPtcptBankNo;
    }

    
    public String getTermNb() {
        return termNb;
    }

    
    public void setTermNb(String termNb) {
        this.termNb = termNb;
    }

    
    public List<DraftBankInformation> getBankInf() {
        return bankInf;
    }

    
    public void setBankInf(List<DraftBankInformation> bankInf) {
        this.bankInf = bankInf;
    }

    
    public String getSttlmOnlineMrk() {
        return sttlmOnlineMrk;
    }

    
    public void setSttlmOnlineMrk(String sttlmOnlineMrk) {
        this.sttlmOnlineMrk = sttlmOnlineMrk;
    }

    
    public String getSttlmRmrk() {
        return sttlmRmrk;
    }

    
    public void setSttlmRmrk(String sttlmRmrk) {
        this.sttlmRmrk = sttlmRmrk;
    }

    
    public List<DraftAccessPoints> getAccssPtsInf() {
        return AccssPtsInf;
    }

    
    public void setAccssPtsInf(List<DraftAccessPoints> accssPtsInf) {
        AccssPtsInf = accssPtsInf;
    }


    
    public String getAltrnTp() {
        return altrnTp;
    }


    
    public void setAltrnTp(String altrnTp) {
        this.altrnTp = altrnTp;
    }


    
    public String getRmrk() {
        return rmrk;
    }


    
    public void setRmrk(String rmrk) {
        this.rmrk = rmrk;
    }


    
    public List<String> getPtcpt() {
        return ptcpt;
    }


    
    public void setPtcpt(List<String> ptcpt) {
        this.ptcpt = ptcpt;
    }


    
    public List<BindingDetail> getBindList() {
        return bindList;
    }


    
    public void setBindList(List<BindingDetail> bindList) {
        this.bindList = bindList;
    }


    
    public String getPtcptTp() {
        return ptcptTp;
    }


    
    public void setPtcptTp(String ptcptTp) {
        this.ptcptTp = ptcptTp;
    }


    
    public String getPtcptNb() {
        return ptcptNb;
    }


    
    public void setPtcptNb(String ptcptNb) {
        this.ptcptNb = ptcptNb;
    }


    
    public String getPtcptSts() {
        return ptcptSts;
    }


    
    public void setPtcptSts(String ptcptSts) {
        this.ptcptSts = ptcptSts;
    }


    
    public String getFctvTp() {
        return fctvTp;
    }


    
    public void setFctvTp(String fctvTp) {
        this.fctvTp = fctvTp;
    }


    
    public String getSwitchTp() {
        return switchTp;
    }


    
    public void setSwitchTp(String switchTp) {
        this.switchTp = switchTp;
    }


    
    public String getSwitchOnOffMk() {
        return switchOnOffMk;
    }


    
    public void setSwitchOnOffMk(String switchOnOffMk) {
        this.switchOnOffMk = switchOnOffMk;
    }
    
    
    public List<DraftAccessPointAndParticipants> getAccessPointAndParticipants() {
		return AccessPointAndParticipants;
	}


	public void setAccessPointAndParticipants(
			List<DraftAccessPointAndParticipants> accessPointAndParticipants) {
		AccessPointAndParticipants = accessPointAndParticipants;
	}
	
	
	 public Integer getId() {
			return id;
		}


		public void setId(Integer id) {
			this.id = id;
		}

		/**039报文发送/接受时间*/
		public Date getReceiveTime() {
			return receiveTime;
		}

		/**039报文发送/接受时间*/
		public void setReceiveTime(Date receiveTime) {
			this.receiveTime = receiveTime;
		}

		/**039广播级别*/
		public String getBorderLevel() {
			return borderLevel;
		}

		/**039广播级别*/
		public void setBorderLevel(String borderLevel) {
			this.borderLevel = borderLevel;
		}

		/**039报文内容*/
		public String getContents() {
			return contents;
		}

		/**039报文内容*/
		public void setContents(String contents) {
			this.contents = contents;
		}

		/**039发起行行号*/
		public String getSendBankNo() {
			return sendBankNo;
		}

		/**039发起行行号*/
		public void setSendBankNo(String sendBankNo) {
			this.sendBankNo = sendBankNo;
		}

		/**039接受行行号*/
		public String getReceiveBankNo() {
			return receiveBankNo;
		}

		/**039接受行行号*/
		public void setReceiveBankNo(String receiveBankNo) {
			this.receiveBankNo = receiveBankNo;
		}

		/**039发送接收处理状态 S0-发送成功 R0-接受未处理 R1-接受已处理*/
		public String getStatus() {
			return status;
		}

		/**039发送接收处理状态 S0-发送成功 R0-接受未处理 R1-接受已处理*/
		public void setStatus(String status) {
			this.status = status;
		}


		public Date getStartDate() {
			return startDate;
		}


		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}


		public Date getEndDate() {
			return endDate;
		}


		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}


    
}
