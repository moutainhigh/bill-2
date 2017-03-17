create table trgct_method  (
   id                 INTEGER                         not null,
   sub_system         VARCHAR2(70)                    not null,
   interface_name     VARCHAR2(50)                    not null,
   method_name        VARCHAR2(50)                    not null,
   method_cn_name     VARCHAR2(100),
   is_check_status    CHAR(1)                        default '0',
   is_check_del       CHAR(1)                        default '0',
   is_add_endorse     CHAR(1)                        default '0',
   is_back_history    CHAR(1)                        default '0',
   is_add_obligee     VARCHAR2(1),
   constraint PK_RGCT_METHOD primary key (id)
);

create table trgct_status  (
   id                 INTEGER    not null,
   method_id          INTEGER,
   before_status      VARCHAR2(70),
   after_status       VARCHAR2(70),
   run_status         VARCHAR2(70),
   param              VARCHAR2(70),
   constraint PK_RGCT_STATUS primary key (id)
);

create table trgct_bill_info  (
   id                 VARCHAR2(30)                    not null,
   hist_id            VARCHAR2(30),
   temp_hist_id       VARCHAR2(30),
   ebs_no             VARCHAR2(50),
   bill_class         VARCHAR2(70),
   bill_type          VARCHAR2(70),
   bill_no            VARCHAR2(30),
   issue_dt           VARCHAR2(20),
   is_accpt           CHAR(1)                        default '0',
   remitter           VARCHAR2(300),
   remitter_cust_no   VARCHAR2(50),
   remitter_acct      VARCHAR2(50),
   remitter_sign      VARCHAR2(2000),
   remitter_bank_no   VARCHAR2(50),
   remitter_bank_name VARCHAR2(300),
   drawee_bank_name   VARCHAR2(300),
   drawee_bank_no     VARCHAR2(30),
   drawee_branch_no   VARCHAR2(50),
   drawee_addr        VARCHAR2(200),
   payee_name         VARCHAR2(300),
   payee_acct         VARCHAR2(50),
   payee_bank_no      VARCHAR2(30),
   payee_bank_name    VARCHAR2(300),
   acceptor           VARCHAR2(200),
   acceptor_cust_no   VARCHAR2(50),
   acceptor_acct      VARCHAR2(50),
   acceptor_bank_no   VARCHAR2(30),
   acceptor_bank_name VARCHAR2(300),
   acceptor_sign      VARCHAR2(2000),
   assure_bank_name   VARCHAR2(300),
   assure_bank_no     VARCHAR2(50),
   letter_no          VARCHAR2(300),
   assure_self        CHAR(1)                        default '0',
   bill_source        CHAR(1),
   bill_money         NUMBER(20,2)                   default 0,
   due_dt             VARCHAR2(20),
   confer_no          VARCHAR2(90),
   info_forbid_flag   VARCHAR2(70),
   deduct_flag        VARCHAR2(70),
   is_acpt_acct       CHAR(1)                        default '0',
   bill_usage         VARCHAR2(100),
   remark             VARCHAR2(200),
   cancel_reason      VARCHAR2(200),
   cancel_remark      VARCHAR2(500),
   create_dt          VARCHAR2(20),
   create_time        VARCHAR2(20),
   del_flag           CHAR(1)                        default '0',
   invoice_no         VARCHAR2(90),
   remitter_remark    VARCHAR2(500),
   drwr_creditrating  VARCHAR2(50),
   drwr_creditrating_agency VARCHAR2(500),
   drwr_creditrating_duedt VARCHAR2(50),
   acptr_creditrating VARCHAR2(50),
   acptr_creditrating_agency VARCHAR2(50),
   acptr_creditrating_duedt VARCHAR2(50),
   busi_deep          INTEGER,
   acceptor_date      VARCHAR2(20),
   recourse_flag      VARCHAR2(10)                   default '0',
   remitter_role      VARCHAR2(30),
   remitter_org_code  VARCHAR2(50),
   acceptor_org_code  VARCHAR2(10),
   payee_org_code     VARCHAR2(10),
   cur_status         VARCHAR2(70),
   acct_branch_no     VARCHAR2(12),
   storage_branch_no  VARCHAR2(12),
   acceptor_role      VARCHAR2(10),
   req_draft_id       VARCHAR2(50),
   resp_draft_id      VARCHAR2(50),
   constraint PK_RGCT_BILL_INFO primary key (id)
);

create table trgct_bill_hist  (
   hist_id            VARCHAR2(30)                    not null,
   rgct_id            VARCHAR2(30)                    not null,
   cur_status         VARCHAR2(70),
   pre_status         VARCHAR2(70),
   run_status         VARCHAR2(70),
   batch_id           VARCHAR2(50),
   from_name          VARCHAR2(300),
   from_cust_no       VARCHAR2(50),
   from_acct_no       VARCHAR2(50),
   from_bank_no       VARCHAR2(12),
   from_branch_no     VARCHAR2(50),
   from_sign          VARCHAR2(2000),
   to_name            VARCHAR2(300),
   to_cust_no         VARCHAR2(50),
   to_acct_no         VARCHAR2(50),
   to_bank_no         VARCHAR2(12),
   to_branch_no       VARCHAR2(50),
   sign_dt            VARCHAR2(20),
   sign_time          VARCHAR2(20),
   interest_rate      NUMBER(10,6)                   default 0,
   interest           NUMBER(20,2)                   default 0,
   deal_money         NUMBER(20,2)                   default 0,
   is_regress         CHAR(1)                        default '0',
   regress_dt         VARCHAR2(20),
   regress_time       VARCHAR2(20),
   regress_price      NUMBER(20,2)                   default 0,
   back_open_dt       VARCHAR2(20),
   back_end_dt        VARCHAR2(20),
   back_rate          NUMBER(10,6)                   default 0,
   back_amount        NUMBER(20,6),
   if_inner           CHAR(1)                        default '0',
   is_buy             CHAR(1)                        default '0',
   signer             VARCHAR2(300),
   signer_sign        VARCHAR2(2000),
   sign_flag          CHAR(1)                        default '0',
   hold_cust_no       VARCHAR2(50),
   hold_cust_name     VARCHAR2(300),
   hold_acct_no       VARCHAR2(50),
   hold_bank_no       VARCHAR2(50),
   obligee_cust_no    VARCHAR2(300),
   obligee_cust_name  VARCHAR2(300),
   obligee_bank_no    VARCHAR2(12),
   obligee_acct_no    VARCHAR2(50),
   return_code        VARCHAR2(70),
   oper_date          VARCHAR2(20),
   oper_time          VARCHAR2(20),
   oper_no            VARCHAR2(50),
   channel            CHAR(1),
   is_lock            CHAR(1)                        default '0',
   lock_branch_no     VARCHAR2(50),
   lock_flow_name     VARCHAR2(100),
   step_name          VARCHAR2(100),
   valid_hist_id      VARCHAR2(30),
   last_hist_id       VARCHAR2(30),
   is_online          CHAR(1)                        default '0',
   overdue_rs         VARCHAR2(500),
   is_accpt           CHAR(1)                        default '0',
   draft_log_id       VARCHAR2(30),
   reject_code        VARCHAR2(70),
   reject_reason      VARCHAR2(500),
   is_redisc_center   CHAR(1)                        default '0',
   assu_id            VARCHAR2(30),
   recourse_id        VARCHAR2(30),
   is_delegate        CHAR(1)                        default '0',
   in_acct_no         VARCHAR2(50),
   in_bank_no         VARCHAR2(12),
   partner_code       VARCHAR2(12),
   pay_trade_no       VARCHAR2(50),
   forbid_flag        CHAR(1)                        default '0',
   from_remark        VARCHAR2(500),
   to_remark          VARCHAR2(500),
   draft_info         VARCHAR2(500),
   old_interest_rate  NUMBER(10,6),
   old_dis_dt         VARCHAR2(20),
   delay_days         VARCHAR2(4),
   delay_type         VARCHAR2(1),
   bill_track         VARCHAR2(20),
   is_same_city       CHAR(1),
   interest_due_dt    VARCHAR2(20),
   interest_days      INTEGER,
   firstbuy_interest  NUMBER(20,2),
   from_orgcode       VARCHAR2(50),
   to_orgcode         VARCHAR2(50),
   from_role          VARCHAR2(30),
   to_role            VARCHAR2(30),
   bill_before_owner  VARCHAR2(200),
   prod_attr          VARCHAR2(120),
   acct_branch_no     VARCHAR2(12),
   storage_branch_no  VARCHAR2(12),
   buy_type           VARCHAR2(2),
   workingads_no      VARCHAR2(10),
   workingads_name    VARCHAR2(300),
   storage_branch_name VARCHAR2(300),
   endo_hist_id       VARCHAR2(30),
   endorses_bank_name VARCHAR2(180),
   endorses_bank_no   VARCHAR2(16),
   endorse_dt         VARCHAR2(20),
   endo_status        VARCHAR2(20),
   constraint PK_RGCT_BILL_HIST primary key (hist_id)
);

create table tECDS_BANK_DATA  (
   ROW_NUMBER         VARCHAR2(36)                    not null,
   ACTOR_TYPE           VARCHAR2(6),
   ROW_OTHER_CODE       VARCHAR2(9),
   SUPER_ACTOR          VARCHAR2(36),
   LOCAL_NODE_CODE      VARCHAR2(12),
   ROOT_SUPER_ACTOR     VARCHAR2(210),
   CATE_PEOPLE_CODE     VARCHAR2(36),
   CITY_CODE            VARCHAR2(12),
   ACTOR_FULL_CALL      VARCHAR2(180),
   ACTOR_FOR_SHORT      VARCHAR2(60),
   ADDRESS              VARCHAR2(180),
   POST_EDIT            VARCHAR2(18),
   PHONE                VARCHAR2(90),
   EMAIL                VARCHAR2(90),
   STATUS               VARCHAR2(3),
   INURE_DATE           VARCHAR2(30),
   LOG_OUT_DATE         VARCHAR2(30),
   UPDATE_TIME          VARCHAR2(78),
   LATELY_UPDATE_WORK   VARCHAR2(3),
   LOG_UPDATE_EXPECT    VARCHAR2(24),
   REMARK               VARCHAR2(180),
   DN_FIELD             VARCHAR2(600),
   SN_FIELD             VARCHAR2(600),
   CERT_BIND_STATUS     VARCHAR2(6),
   MEET_INCOME_CODE     VARCHAR2(36),
   CONTINUE_ROW_NUM     VARCHAR2(36),
   CONTINUE_MEET_INCOME VARCHAR2(36),
   IF_CONTINUE          VARCHAR2(6),
   HISTORY_CONTINUE_CON VARCHAR2(300),
   STTLM_ACC_STATUS     CHAR(3),
   STTLM_ACC_UPDATE     CHAR(30),
   STTLM_ACC_UPTIME     CHAR(42),
   constraint SQL160617100757900 primary key (ROW_NUMBER)
);

create table trgct_draft_log  (
   log_id             VARCHAR2(30)                    not null,
   rgct_id           VARCHAR2(30) ,
   rgct_hist_id       VARCHAR2(30) ,
   draft_no_req       VARCHAR2(50),
   req_draft          CLOB,
   req_dt             VARCHAR2(20),
   req_time           VARCHAR2(20),
   req_sid            VARCHAR2(50),
   req_bank_no        VARCHAR2(50),
   resp_draft         CLOB,
   resp_dt            VARCHAR2(20),
   resp_time          VARCHAR2(20),
   resp_sid           VARCHAR2(50),
   resp_bank_no       VARCHAR2(50),
   draft_no_resp      VARCHAR2(50),
   req_msg_id         VARCHAR2(50),
   from_draft_no      VARCHAR2(50),
   in_out             CHAR(1)                        default '0',
   bill_no            VARCHAR2(30),
   reply_flag         CHAR(1)                        default '0',
   is_check_draft     CHAR(1)                        default '0',
   accept_bank_no   VARCHAR2(12),
   process_flag       CHAR(1),
   send_flag          CHAR(1),
   draft_info         VARCHAR2(500),
   entity_id          VARCHAR2(30) ,
   entity_no          VARCHAR2(50),
   entity_type        VARCHAR2(10),
   constraint PK_RGCT_DRAFT_LOG primary key (log_id)
);

create table trgct_temp_hist  (
   hist_id            VARCHAR2(30)                    not null,
   rgct_id            VARCHAR2(30)                    not null,
   cur_status         VARCHAR2(70),
   pre_status         VARCHAR2(70),
   run_status         VARCHAR2(70),
   batch_id           VARCHAR2(50),
   from_name          VARCHAR2(300),
   from_cust_no       VARCHAR2(50),
   from_acct_no       VARCHAR2(50),
   from_bank_no       VARCHAR2(12),
   from_brch_id       VARCHAR2(50),
   from_sign          VARCHAR2(2000),
   to_name            VARCHAR2(300),
   to_cust_no         VARCHAR2(50),
   to_acct_no         VARCHAR2(50),
   to_bank_no         VARCHAR2(12),
   to_brch_id         VARCHAR2(50),
   sign_dt            VARCHAR2(20),
   sign_time          VARCHAR2(20),
   interest_rate      NUMBER(10,6)                   default 0,
   interest           NUMBER(20,2)                   default 0,
   deal_money         NUMBER(20,2)                   default 0,
   is_regress         CHAR(1)                        default '0',
   regress_dt         VARCHAR2(20),
   regress_time       VARCHAR2(20),
   regress_price      NUMBER(20,2)                   default 0,
   back_open_dt       VARCHAR2(20),
   back_end_dt        VARCHAR2(20),
   back_rate          NUMBER(10,6)                   default 0,
   back_amount        NUMBER(20,6),
   is_inner           CHAR(1)                        default '0',
   is_buy             CHAR(1)                        default '0',
   signer             VARCHAR2(300),
   signer_sign        VARCHAR2(2000),
   sign_flag          CHAR(1)                        default '0',
   hold_cust_no       VARCHAR2(50),
   hold_cust_name     VARCHAR2(300),
   hold_acct_no       VARCHAR2(50),
   hold_bank_no       VARCHAR2(50),
   obligee_cust_no    VARCHAR2(300),
   obligee_cust_name  VARCHAR2(300),
   obligee_bank_no    VARCHAR2(12),
   obligee_acct_no    VARCHAR2(50),
   return_code        VARCHAR2(70),
   oper_date          VARCHAR2(20),
   oper_time          VARCHAR2(20),
   oper_no            VARCHAR2(50),
   channel            CHAR(1),
   is_lock            CHAR(1)                        default '0',
   lock_branch_no     VARCHAR2(50),
   lock_flow_name     VARCHAR2(100),
   step_name          VARCHAR2(100),
   valid_hist_id      VARCHAR2(30),
   last_hist_id       VARCHAR2(30),
   is_online          CHAR(1)                        default '0',
   overdue_rs         VARCHAR2(500),
   is_accpt           CHAR(1)                        default '0',
   draft_log_id       VARCHAR2(30),
   reject_code        VARCHAR2(70),
   reject_reason      VARCHAR2(500),
   is_redisc_center   CHAR(1)                        default '0',
   assu_id            VARCHAR2(30),
   recourse_id        VARCHAR2(30),
   is_delegate        CHAR(1)                        default '0',
   in_acct_no         VARCHAR2(50),
   in_bank_no         VARCHAR2(12),
   partner_code       VARCHAR2(12),
   pay_trade_no       VARCHAR2(50),
   forbid_flag        CHAR(1)                        default '0',
   from_remark        VARCHAR2(500),
   to_remark          VARCHAR2(500),
   draft_info         VARCHAR2(500),
   old_interest_rate  NUMBER(10,6),
   old_dis_dt         VARCHAR2(20),
   delay_days         VARCHAR2(4),
   delay_type         VARCHAR2(1),
   bill_track         VARCHAR2(20),
   is_same_city       CHAR(1),
   interest_due_dt    VARCHAR2(20),
   interest_days      INTEGER,
   firstbuy_interest  NUMBER(20,2),
   from_orgcode       VARCHAR2(50),
   to_orgcode         VARCHAR2(50),
   from_role          VARCHAR2(30),
   to_role            VARCHAR2(30),
   bill_before_owner  VARCHAR2(200),
   prod_attr          VARCHAR2(120),
   acct_branch_no     VARCHAR2(12),
   storage_branch_no  VARCHAR2(12),
   buy_type           VARCHAR2(2),
   workingads_no      VARCHAR2(10),
   workingads_name    VARCHAR2(300),
   storage_branch_name VARCHAR2(300),
   endo_hist_id      VARCHAR2(30),
   endorses_bank_name VARCHAR2(180),
   endorses_bank_no   VARCHAR2(16),
   endorse_dt         VARCHAR2(20),
   endo_status        VARCHAR2(20),
   comes_from         VARCHAR2(20),
   acct_brch_no       VARCHAR2(20),
   storage_brch_no    VARCHAR2(20),
   constraint PK_RGCT_TEMP_HIST primary key (hist_id)
);

create table tecds_ap_data  (
   meet_code          VARCHAR2(30)                    not null,
   meet_name          VARCHAR2(180),
   nonce_ccpc         VARCHAR2(12),
   meet_state         VARCHAR2(6),
   inure_date         VARCHAR2(30),
   logout_date        VARCHAR2(30),
   update_time        VARCHAR2(78),
   lately_update_work VARCHAR2(6),
   meet_enter_state   VARCHAR2(3),
   enter_identify     VARCHAR2(60),
   this_month_set_first_time VARCHAR2(24),
   this_month_set_second VARCHAR2(24),
   first_limited      VARCHAR2(24),
   second_limited     VARCHAR2(24),
   constraint PK_ECDS_AP_DATA primary key (meet_code)
);


create table tecds_authlist_data  (
   actor_row_number            VARCHAR2(12)                    not null,
   launch_purview_list VARCHAR2(500),
   incept_purview_list VARCHAR2(500),
   constraint PK_ECDS_AUTHLIST_DATA primary key (ACTOR_ROW_NUMBER)
);

create table tecds_common_data  (
   commonality_data_code VARCHAR2(36)                    not null,
   commonality_data_name VARCHAR2(180),
   commonality_data_value VARCHAR2(750),
   commonality_data_updata VARCHAR2(750),
   commonality_data_type VARCHAR2(3),
   update_style       VARCHAR2(3),
   inure_date         VARCHAR2(30),
   postscipt          VARCHAR2(180),
   update_date        VARCHAR2(30),
   constraint PK_ECDS_COMMON_DATA primary key (commonality_data_code)
);

create table tecds_node_status  (
   id                 INTEGER                         not null,
   type               VARCHAR2(2),
   node_code          VARCHAR2(4),
   effective_type     VARCHAR2(1),
   effective_date       VARCHAR2(20),
   state              VARCHAR2(1)
);

create table tecds_op_code  (
   id                 INTEGER                         not null,
   op_code            VARCHAR2(10),
   cn_desc            VARCHAR2(100),
   constraint PK_ECDS_OP_CODE primary key (id)
);

create table tecds_ps_ass_data  (
   by_continue_bank_no VARCHAR2(36)                    not null,
   by_continue_org_code VARCHAR2(60),
   by_continue_role   VARCHAR2(12),
   msg_id             VARCHAR2(84),
   continue_bank_no   VARCHAR2(36),
   continue_org_code  VARCHAR2(60),
   continue_role      VARCHAR2(12),
   inure_date         VARCHAR2(30),
   by_continue_launch VARCHAR2(6),
   continue_launch    VARCHAR2(6),
   launch_time        VARCHAR2(78),
   update_state       VARCHAR2(6)
);

create table tecds_psap_ass_data  (
   row_number            VARCHAR2(36)                    not null,
   meet_code          VARCHAR2(30),
   effective_date     VARCHAR2(30),
   effective_time     VARCHAR2(30),
   update_date        VARCHAR2(30),
   update_time        VARCHAR2(30),
   process_state      VARCHAR2(6),
   remark             VARCHAR2(180),
   constraint PK_ECDS_PSAP_ASS_DATA primary key (row_number)
);


create table tecds_bank_ass_data  (
   id                 INTEGER                         not null,
   row_number            VARCHAR2(36)                    not null,
   actor_type         VARCHAR2(6),
   row_other_code     VARCHAR2(9),
   super_actor        VARCHAR2(36),
   local_node_code    VARCHAR2(12),
   root_super_actor   VARCHAR2(210),
   cate_people_code   VARCHAR2(36),
   city_code          VARCHAR2(12),
   actor_full_call    VARCHAR2(180),
   actor_for_short    VARCHAR2(60),
   address            VARCHAR2(180),
   post_edit          VARCHAR2(18),
   phone              VARCHAR2(90),
   email              VARCHAR2(90),
   inure_date         VARCHAR2(30),
   update_dt          VARCHAR2(20),
   update_time        VARCHAR2(78),
   log_update_expect  VARCHAR2(24),
   state              VARCHAR2(6),
   oper_status        VARCHAR2(1),
   remark             VARCHAR2(180),
   constraint PK_ECDS_BANK_ASS_DATA primary key (row_number)
);

create table trgct_endo_hist  (
   id                 VARCHAR2(30)                    not null,
   rgct_id            VARCHAR2(30),
   endo_type          VARCHAR2(5),
   from_name          VARCHAR2(300),
   from_acct_no       VARCHAR2(50),
   from_bank_no       VARCHAR2(20),
   from_org_code      VARCHAR2(20),
   to_name            VARCHAR2(100),
   to_acct_no         VARCHAR2(50),
   to_bank_no         VARCHAR2(20),
   to_org_code        VARCHAR2(20),
   endorse_dt         VARCHAR2(20),
   endorse_time       VARCHAR2(20),
   sign_date          VARCHAR2(20),
   sign_time          VARCHAR2(20),
   rpd_open_date      VARCHAR2(20),
   rpd_due_date       VARCHAR2(20),
   forbid_flag        VARCHAR2(4)                    default '0',
   is_reject_prsnt    VARCHAR2(1),
   reject_prsnt_rsn   VARCHAR2(300),
   recourse_type      VARCHAR2(1),
   guarntee_adr       VARCHAR2(300),
   remark             VARCHAR2(200),
   create_time        VARCHAR2(20),
   create_dt          VARCHAR2(20),
   constraint PK_RGCT_ENDO_HIST primary key (id)
);

create table trgct_trigger  (
   id                 VARCHAR2(20)                    not null,
   draft_type         VARCHAR2(50)                    not null,
   draft_type_cn      VARCHAR2(150),
   method_name        VARCHAR2(50)                    not null,
   method_cn_name     VARCHAR2(100),
   spring_id          VARCHAR2(100)                   not null,
   spring_desc        VARCHAR2(150),
   constraint PK_RGCT_Trigger primary key (id)
);

create table tproduct  (
   id                 INTEGER                         not null,
   prod_no            VARCHAR2(50),
   prod_name          VARCHAR2(100),
   prod_status        VARCHAR(1),
   create_time        VARCHAR2(50),
   parent_prod_no     VARCHAR2(50),
   constraint PK_product primary key (id)
);

create table tprod_limit_type  (
   id                 INTEGER                         not null,
   prod_no            VARCHAR2(50),
   limit_type         VARCHAR2(50),
   buy_type           VARCHAR2(1),
   is_inner           VARCHAR2(1),
   bill_type          VARCHAR2(1),
   buy_into_type      VARCHAR2(2),
   constraint PK_PROD_LIMIT_TYPE primary key (id)
);

create table tacct_flow 
(
   af_id                varchar2(30)                   not null,
   fore_flow_no         varchar2(50)                   null,
   back_flow_no         varchar2(50)                   null,
   trans_no             varchar2(50)                   null,
   trans_name           varchar2(200)                  null,
   trans_dt             varchar2(10)                   null,
   trans_tm             varchar2(10)                   null,
   acct_type            varchar2(4)                    null,
   acct_status          varchar2(4)                    null,
   prod_no              varchar2(30)                   null,
   trans_branch_no      varchar2(10)                   null,
   trans_branch_name    varchar2(200)                  null,
   trans_user_no        varchar2(10)                   null,
   acct_branch_no       varchar2(10)                   null,
   total_amount         decimal(20,2)                  null,
   settlement_money     decimal(20,2)                  null,
   settlement_interest  decimal(20,2)                  null,
   constraint pk_tacct_flow primary key  (af_id)
);

comment on table tacct_flow is 
'记账流水表';

comment on column tacct_flow.fore_flow_no is 
'票据系统传给核心的流水号';

comment on column tacct_flow.back_flow_no is 
'核心系统返回给票据系统的流水号';

comment on column tacct_flow.trans_no is 
'行里定的交易行为，后面如果要票据系统出分录可根据交易编码配置分录信息';

comment on column tacct_flow.acct_type is 
'交易类型
1：记账
2：冲正';

comment on column tacct_flow.acct_status is 
'0：初始化  
1：记账成功  
2：失败
3：超时 ';

comment on column tacct_flow.prod_no is 
'票据系统产品编码';


create table tacct_flow_info 
(
   acct_info_id         varchar2(30)                   not null,
   af_id                varchar2(30)                   null,
   bill_no              varchar2(30)                   null,
   rgct_id              varchar2(20)                   null,
   info_id              varchar2(20)                   null,
   trans_no             varchar2(50)                   null,
   trans_dt             varchar2(10)                   null,
   trans_tm             varchar2(10)                   null,
   prod_no              varchar2(20)                   null,
   bill_amonut          decimal(20,2)                  null,
   settlement_amt       decimal(20,2)                  null,
   settlement_intrst    decimal(20,2)                  null,
   trans_branch_no      varchar2(10)                   null,
   acct_branch_no       varchar2(10)                   null,
   rema_intrst          decimal(20,2)                  null,
   cur_rema_intrst      decimal(20,2)                  null,
   discrepancy_interest decimal(20,2)                  null,
   descption             varchar2(150)                  null,
   pro_id               varchar2(20)                   null,
   constraint pk_tacct_flow_info primary key (acct_info_id)
);

comment on table tacct_flow_info is 
'记账流水明细';

comment on column tacct_flow_info.rema_intrst is 
'未计提时：待摊销收入=买入利息收入
已计提时：待摊销收入=买入利息收入-已计提利息';

comment on column tacct_flow_info.cur_rema_intrst is 
'当期未摊销金额=面值*买入利率*（转卖日-买入日/上一次摊销日）';

comment on column tacct_flow_info.discrepancy_interest is 
'利息损益=待摊销利息-当期未摊销金额';


create table tacct_balance 
(
   balance_id           varchar2(20)                   not null,
   info_id              varchar2(20)                   null,
   rgct_id              varchar2(20)                   null,
   bill_no              varchar2(30)                   null,
   start_dt             varchar2(10)                   null,
   end_dt               varchar2(10)                   null,
   cust_no              varchar2(20)                   null,
   cust_name            varchar2(200)                  null,
   prod_no              varchar2(20)                   null,
   status               varchar2(4)                    null,
   create_dt            varchar2(10)                   null,
   create_tm            varchar2(10)                   null,
   update_dt            varchar2(10)                   null,
   update_tm            varchar2(10)                   null,
   storage_brch_no    VARCHAR2(20),
   storage_brch_name  VARCHAR2(200),
   acct_brch_no       VARCHAR2(20),
   acct_brch_name     VARCHAR2(200),
   constraint pk_tacct_balance primary key (balance_id)
);

comment on table tacct_balance is 
'余额明细表';

comment on column tacct_balance.status is 
' 1：开始 
 2：结束';


create table tacct_balance_his 
(
   his_id               varchar2(20)                   not null,
   busi_dt              varchar2(10)                   null,
   info_id              varchar2(20)                   null,
   rgct_id              varchar2(20)                   null,
   bill_no              varchar2(30)                   null,
   start_dt             varchar2(10)                   null,
   end_dt               varchar2(10)                   null,
   cust_no              varchar2(20)                   null,
   cust_name            varchar2(200)                  null,
   prod_no              varchar2(20)                   null,
   status               varchar2(4)                    null,
   create_dt            varchar2(10)                   null,
   create_tm            varchar2(10)                   null,
   update_dt            varchar2(10)                   null,
   update_tm            varchar2(10)                   null,
   storage_brch_no    VARCHAR2(20),
   storage_brch_name  VARCHAR2(200),
   acct_brch_no       VARCHAR2(20),
   acct_brch_name     VARCHAR2(200),
   constraint pk_tacct_balance_his primary key (his_id)
);

comment on table tacct_balance_his is 
'余额结转表';

comment on column tacct_balance_his.status is 
' 1：开始 
 2：结束';

create table tprofession_invest_direction  (
   id                 VARCHAR2(10)                    not null,
   profession_name    VARCHAR2(200)                   not null,
   constraint PK_PROFESSION_INVEST_DIRECTION primary key (id)
);


create table ttask_pool  (
   task_no            VARCHAR2(30),
   task_name          VARCHAR2(50),
   parent_task_no     VARCHAR2(30),
   task_type          VARCHAR2(4),
   repeat_flag        VARCHAR2(4),
   delay_flag         VARCHAR2(4),
   delay_time         VARCHAR2(20),
   deal_status        VARCHAR2(4),
   deal_msg           VARCHAR2(256),
   oper_no            VARCHAR2(20),
   repeat_num         INT,
   begin_time         VARCHAR2(20),
   end_time           VARCHAR2(20),
   err_msg            VARCHAR2(256),
   effective_flag     VARCHAR2(4),
   constraint PK_TTASK_POOL primary key (task_no)
);

comment on column ttask_pool.deal_status is
'0-未激活
1-已激活
2-作业暂停
3-作业完成
4-作业失败
5-作业中断
Z-正在处理
需要初始化';


create table T_AUDIT_STATION  (
   id                 VARCHAR2(30)                    not null,
   AN_ID                VARCHAR2(30),
   AR_ID                VARCHAR2(30),
   AS_NAME              VARCHAR2(200),
   AS_PRIVILEGE         NUMBER(20,2),
   CREATE_BRCH_NO       VARCHAR2(20),
   AS_REMARK            VARCHAR2(256),
   SORT_NO              VARCHAR2(30),
   UPDATE_DT            VARCHAR2(20),
   UPDATE_TM            VARCHAR2(20),
   constraint PK_T_AUDIT_STATION primary key (id)
);

create table T_AUDIT_TASK  (
   id                VARCHAR2(30)                    not null,
   AR_ID                VARCHAR2(30),
   AT_STATUS            VARCHAR2(2),
   BATCH_ID             VARCHAR2(30),
   ENTITY_NAME          VARCHAR2(200),
   ENTITY_SERVICE       VARCHAR2(200),
   BATCH_NO             VARCHAR2(30),
   WAIT_AUDIT_AMT       NUMBER(20,2),
   AT_AUTHOR_ID         VARCHAR2(30),
   AT_AUTHOR_NAME       VARCHAR2(256),
   AT_CREATE_DT         VARCHAR2(20),
   AT_CREATE_TIME       VARCHAR2(20),
   AT_DONE_DT           VARCHAR2(20),
   AT_DONE_TIME         VARCHAR2(20),
   BRCH_NO              VARCHAR2(20),
   PROD_NO              VARCHAR2(30),
   AUDIT_REMARK         VARCHAR2(20),
   UPDATE_DT            VARCHAR2(20),
   UPDATE_TM            VARCHAR2(20),
   constraint PK_T_AUDIT_TASK primary key (id)
);

comment on column T_AUDIT_TASK.BATCH_ID is
'批次id';


create table T_RE_AS_ROLE  (
   id                 VARCHAR2(30)                    not null,
   AS_ID                VARCHAR2(30),
   BIND_ROLE_ID         VARCHAR2(30),
   UPDATE_DT            VARCHAR2(20),
   UPDATE_TM            VARCHAR2(20),
   constraint PK_T_RE_AS_ROLE primary key (id)
);

create table T_RE_AS_USER  (
   id                 VARCHAR2(30)                    not null,
   AS_ID                VARCHAR2(30),
   BIND_USER_ID         VARCHAR2(30),
   UPDATE_DT            VARCHAR2(20),
   UPDATE_TM            VARCHAR2(20),
   constraint PK_T_RE_AS_USER primary key (id)
);

create table T_RE_BRCH_PROD  (
   id                 VARCHAR2(30)                    not null,
   BRANCH_NO            VARCHAR2(20),
   PROD_NO              VARCHAR2(20),
   AUDIT_FLAG           VARCHAR2(1),
   UPDATE_DT            VARCHAR2(20),
   UPDATE_TM            VARCHAR2(20),
   constraint PK_T_RE_BRCH_PROD primary key (id)
);

create table T_AUDIT_PROCESS  (
   id                 VARCHAR2(30)                    not null,
   AT_ID                VARCHAR2(30)                    not null,
   AP_COMMIT_STATION_ID VARCHAR2(50),
   AP_COMMIT_STATION_NAME VARCHAR2(200),
   AP_COMMIT_PERSON_ID  VARCHAR2(50),
   AP_COMMIT_PERSON_NAME VARCHAR2(200),
   AP_COMMIT_BRCH_NO    VARCHAR2(20),
   AP_COMMIT_REMARK     VARCHAR2(256),
   AP_EXEC_STATION_ID   VARCHAR2(50),
   AP_EXEC_STATION_NAME VARCHAR2(200),
   AP_EXEC_PERSON_ID    VARCHAR2(50),
   AP_EXEC_PERSON_NAME  VARCHAR2(200),
   AP_EXEC_BRCH_NO      VARCHAR2(20),
   AP_STATUS            VARCHAR2(2),
   AP_EXEC_RESULT       VARCHAR2(2),
   AP_EXEC_REMARK       VARCHAR2(256),
   AP_DONE_DT           VARCHAR2(20),
   AP_DONE_TIME         VARCHAR2(20),
   AP_COMMIT_DT         VARCHAR2(20),
   AP_COMMIT_TIME       VARCHAR2(20),
   SORT_NO              VARCHAR2(50),
   UPDATE_DT            VARCHAR2(20),
   UPDATE_TM            VARCHAR2(20),
   constraint PK_T_AUDIT_PROCESS primary key (id)
);

create table T_AUDIT_NODE  (
   id                VARCHAR2(30)                    not null,
   AR_ID                VARCHAR2(30),
   AN_NAME              VARCHAR2(256),
   AN_BRCH_LVL          VARCHAR2(1),
   SORT_NO              VARCHAR2(30),
   UPDATE_DT            VARCHAR2(20),
   UPDATE_TM            VARCHAR2(20),
   constraint PK_T_AUDIT_NODE primary key (id)
);

comment on column T_AUDIT_NODE.AN_BRCH_LVL is
'1、总行2、分行3、支行4、网点';


create table T_AUDIT_ROUTE  (
   id                 VARCHAR2(30)                    not null,
   AR_NAME              VARCHAR2(256),
   AN_EXEC_MODE         VARCHAR2(1),
   AR_REMARK            VARCHAR2(256),
   BIND_BRANCH_NO       VARCHAR2(20),
   PROD_NO              VARCHAR2(20),
   PUB_FLAG             VARCHAR2(1),
   EFFECT_FLAG          VARCHAR2(1),
   AUDIT_START_BRCH_LVL VARCHAR2(1),
   CREATE_BRCH_NO       VARCHAR2(20),
   CREATE_DT            VARCHAR2(20),
   CREATE_TIME          VARCHAR2(20),
   UPDATE_DT            VARCHAR2(20),
   UPDATE_TM            VARCHAR2(20),
   constraint PK_T_AUDIT_ROUTE primary key (id)
);

comment on column T_AUDIT_ROUTE.PUB_FLAG is
'1、全行通用 2、指定机构使用';

comment on column T_AUDIT_ROUTE.EFFECT_FLAG is
'1、生效 0、失效';

comment on column T_AUDIT_ROUTE.AUDIT_START_BRCH_LVL is
'1、总行2、分行3、支行4、网点';


create table TTASK_LOG  (
   id                 VARCHAR2(30)                    not null,
   task_no            VARCHAR2(30)                    not null,
   task_name          VARCHAR2(50),
   parent_task_no     VARCHAR2(30),
   task_type          VARCHAR2(4),
   repeat_flag        VARCHAR2(4),
   delay_flag         VARCHAR2(4),
   delay_time         VARCHAR2(20),
   deal_status        VARCHAR2(4),
   deal_msg           VARCHAR2(256),
   oper_no            VARCHAR2(20),
   repeat_num         INT,
   begin_time         VARCHAR2(20),
   end_time           VARCHAR2(20),
   err_msg            VARCHAR2(256),
   effective_flag     VARCHAR2(4),
   workday            VARCHAR2(20),
   constraint PK_TTASK_LOG primary key (id)
);

comment on column TTASK_LOG.deal_status is
'0-未激活
1-已激活
2-作业暂停
3-作业完成
4-作业失败
5-作业中断
Z-正在处理
需要初始化';


create table tACPT_COLLTN_REG  (
   id                 VARCHAR2(30)                    not null,
   acpt_id            VARCHAR2(30),
   branch_no          VARCHAR2(12),
   reg_flow_no        VARCHAR2(50),
   bill_no            VARCHAR2(50),
   bill_class         VARCHAR2(1),
   bill_type          VARCHAR2(1),
   busi_type          VARCHAR2(1),
   drawee_acct        VARCHAR2(50),
   drawee_name        VARCHAR2(300),
   drawee_bank_no     VARCHAR2(50),
   drawee_bank_name_name VARCHAR2(300),
   payee_acct         VARCHAR2(50),
   payee_name         VARCHAR2(300),
   payee_bank_no      VARCHAR2(50),
   payee_bank_name    VARCHAR2(300),
   colltn_amt         NUMBER(20,2),
   pay_amt            NUMBER(20,2),
   compens_amt        NUMBER(20,2),
   payment_path       VARCHAR2(10),
   bank_type          VARCHAR2(10),
   city               VARCHAR2(50),
   pay_wait_order     VARCHAR2(30),
   reg_dt             VARCHAR2(20),
   is_delay           VARCHAR2(1),
   delay_reason       VARCHAR2(300),
   account_dt         VARCHAR2(20),
   reject_dt          VARCHAR2(20),
   reject_code        VARCHAR2(10),
   reject_reason      VARCHAR2(300),
   cust_remark        VARCHAR2(300),
   bank_remark        VARCHAR2(300),
   colltn_status      VARCHAR2(1),
   oper_no            VARCHAR2(20),
   oper_time          VARCHAR2(20),
   audit_user_no      VARCHAR2(20),
   audit_time         VARCHAR2(20),
   settle_mark        VARCHAR2(4),
   fund               VARCHAR2(1),
   yz_source          VARCHAR2(1)                    default '0',
   constraint PK_ACPT_COLLTN_REG primary key (id)
);

create table TNOTICE
(
  NOTICE_NO      VARCHAR2(30) not null,
  NOTICE_NAME    VARCHAR2(50),
  NOTICE         VARCHAR2(200),
  RELEASE_TIME   VARCHAR2(20),
   constraint PK_NOTICE primary key (NOTICE_NO)
)

create table TRGCT_STATUS_MAPPING  (
   id                 VARCHAR2(30)                    not null,
   bbsp_status_code   VARCHAR2(20),
   ecds_status_code   VARCHAR2(20 BYTE),
   status_name        VARCHAR2(256 BYTE),
   constraint PK_TRGCT_STATUS_MAPPING primary key (id)
);

