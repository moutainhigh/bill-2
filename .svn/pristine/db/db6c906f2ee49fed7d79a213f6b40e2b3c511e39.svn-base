prompt PL/SQL Developer import file
prompt Created on 2016年7月26日 by fqz
set feedback off
set define off
prompt Dropping TBRANCH...
drop table TBRANCH cascade constraints;
prompt Creating TBRANCH...
create table TBRANCH
(
  BRANCH_NO        VARCHAR2(16) default ' ' not null,
  BRANCH_ID        VARCHAR2(16) default ' ' not null,
  BRANCH_LEVEL     VARCHAR2(8) default ' ' not null,
  BRANCH_NAME      VARCHAR2(64) default ' ' not null,
  SHORT_NAME       VARCHAR2(32) default ' ' not null,
  BRANCH_PATH      VARCHAR2(256) default ' ' not null,
  ORG_CODE         VARCHAR2(32) default ' ' not null,
  PARENT_BRCH_CODE VARCHAR2(16) default ' ' not null,
  PARENT_BRCH_ID   VARCHAR2(16) default ' ' not null,
  PAY_BANK_NO      VARCHAR2(16) default ' ' not null,
  ELEC_AUTH        VARCHAR2(8) default ' ' not null,
  IF_EFFECTIVE     VARCHAR2(8) default ' ' not null,
  BANK_NO          VARCHAR2(32) default ' ' not null,
  REMARK1          VARCHAR2(256) default ' ' not null,
  REMARK2          VARCHAR2(256) default ' ' not null,
  REMARK3          VARCHAR2(256) default ' ' not null
)
tablespace POSE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 1M
    next 1M
    minextents 1
    maxextents unlimited
    pctincrease 0
  );
alter table TBRANCH
  add constraint PK_TBRANCH primary key (BRANCH_NO)
  using index 
  tablespace POSE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 1M
    next 1M
    minextents 1
    maxextents unlimited
    pctincrease 0
  );

prompt Disabling triggers for TBRANCH...
alter table TBRANCH disable all triggers;
prompt Loading TBRANCH...
insert into TBRANCH (BRANCH_NO, BRANCH_ID, BRANCH_LEVEL, BRANCH_NAME, SHORT_NAME, BRANCH_PATH, ORG_CODE, PARENT_BRCH_CODE, PARENT_BRCH_ID, PAY_BANK_NO, ELEC_AUTH, IF_EFFECTIVE, BANK_NO, REMARK1, REMARK2, REMARK3)
values ('HR', ' ', '1', '北京合融科技有限公司', '合融科技', '000000', ' ', '0', ' ', '000000', '1', '1', ' ', ' ', ' ', ' ');
insert into TBRANCH (BRANCH_NO, BRANCH_ID, BRANCH_LEVEL, BRANCH_NAME, SHORT_NAME, BRANCH_PATH, ORG_CODE, PARENT_BRCH_CODE, PARENT_BRCH_ID, PAY_BANK_NO, ELEC_AUTH, IF_EFFECTIVE, BANK_NO, REMARK1, REMARK2, REMARK3)
values ('HR01', ' ', '2', ' 研发部', ' ', ' 001000', ' ', 'HR', ' ', ' 000000', '1', '1', ' ', ' ', ' ', ' ');
insert into TBRANCH (BRANCH_NO, BRANCH_ID, BRANCH_LEVEL, BRANCH_NAME, SHORT_NAME, BRANCH_PATH, ORG_CODE, PARENT_BRCH_CODE, PARENT_BRCH_ID, PAY_BANK_NO, ELEC_AUTH, IF_EFFECTIVE, BANK_NO, REMARK1, REMARK2, REMARK3)
values ('HR02', ' ', '2', ' 技术部', ' ', ' 002000', ' ', 'HR', ' ', ' 000000', ' ', ' ', ' ', ' ', ' ', ' ');
insert into TBRANCH (BRANCH_NO, BRANCH_ID, BRANCH_LEVEL, BRANCH_NAME, SHORT_NAME, BRANCH_PATH, ORG_CODE, PARENT_BRCH_CODE, PARENT_BRCH_ID, PAY_BANK_NO, ELEC_AUTH, IF_EFFECTIVE, BANK_NO, REMARK1, REMARK2, REMARK3)
values ('HR0101', ' ', '3', ' 总经理', ' ', ' 001001', ' ', 'HR01', ' ', ' 000000', ' ', ' ', ' ', ' ', ' ', ' ');
insert into TBRANCH (BRANCH_NO, BRANCH_ID, BRANCH_LEVEL, BRANCH_NAME, SHORT_NAME, BRANCH_PATH, ORG_CODE, PARENT_BRCH_CODE, PARENT_BRCH_ID, PAY_BANK_NO, ELEC_AUTH, IF_EFFECTIVE, BANK_NO, REMARK1, REMARK2, REMARK3)
values ('HR0201', ' ', '3', ' 总经理', ' ', ' 002001', ' ', 'HR02', ' ', ' 000000', ' ', ' ', ' ', ' ', ' ', ' ');
insert into TBRANCH (BRANCH_NO, BRANCH_ID, BRANCH_LEVEL, BRANCH_NAME, SHORT_NAME, BRANCH_PATH, ORG_CODE, PARENT_BRCH_CODE, PARENT_BRCH_ID, PAY_BANK_NO, ELEC_AUTH, IF_EFFECTIVE, BANK_NO, REMARK1, REMARK2, REMARK3)
values ('HR0301', ' ', '3', ' 总经理', ' ', ' 003001', ' ', 'HR03', ' ', ' 000000', ' ', ' ', ' ', ' ', ' ', ' ');
insert into TBRANCH (BRANCH_NO, BRANCH_ID, BRANCH_LEVEL, BRANCH_NAME, SHORT_NAME, BRANCH_PATH, ORG_CODE, PARENT_BRCH_CODE, PARENT_BRCH_ID, PAY_BANK_NO, ELEC_AUTH, IF_EFFECTIVE, BANK_NO, REMARK1, REMARK2, REMARK3)
values ('HR03', ' ', '2', ' 人力资源部', ' ', ' 003000', ' ', 'HR', ' ', ' 000000', ' ', ' ', ' ', ' ', ' ', ' ');
insert into TBRANCH (BRANCH_NO, BRANCH_ID, BRANCH_LEVEL, BRANCH_NAME, SHORT_NAME, BRANCH_PATH, ORG_CODE, PARENT_BRCH_CODE, PARENT_BRCH_ID, PAY_BANK_NO, ELEC_AUTH, IF_EFFECTIVE, BANK_NO, REMARK1, REMARK2, REMARK3)
values ('HR04', ' ', '2', ' 财务部', ' ', ' 004000', ' ', 'HR', ' ', ' 000000', ' ', ' ', ' ', ' ', ' ', ' ');
insert into TBRANCH (BRANCH_NO, BRANCH_ID, BRANCH_LEVEL, BRANCH_NAME, SHORT_NAME, BRANCH_PATH, ORG_CODE, PARENT_BRCH_CODE, PARENT_BRCH_ID, PAY_BANK_NO, ELEC_AUTH, IF_EFFECTIVE, BANK_NO, REMARK1, REMARK2, REMARK3)
values ('HR0401', ' ', '3', ' 总经理', ' ', ' 004001', ' ', 'HR04', ' ', ' 000000', ' ', ' ', ' ', ' ', ' ', ' ');
insert into TBRANCH (BRANCH_NO, BRANCH_ID, BRANCH_LEVEL, BRANCH_NAME, SHORT_NAME, BRANCH_PATH, ORG_CODE, PARENT_BRCH_CODE, PARENT_BRCH_ID, PAY_BANK_NO, ELEC_AUTH, IF_EFFECTIVE, BANK_NO, REMARK1, REMARK2, REMARK3)
values ('HR0102', ' ', '3', ' 项目经理', ' ', ' 001002', ' ', 'HR01', ' ', ' 000000', ' ', ' ', ' ', ' ', ' ', ' ');
insert into TBRANCH (BRANCH_NO, BRANCH_ID, BRANCH_LEVEL, BRANCH_NAME, SHORT_NAME, BRANCH_PATH, ORG_CODE, PARENT_BRCH_CODE, PARENT_BRCH_ID, PAY_BANK_NO, ELEC_AUTH, IF_EFFECTIVE, BANK_NO, REMARK1, REMARK2, REMARK3)
values ('HR0103', ' ', '3', ' 开发人员', ' ', ' 001003', ' ', 'HR01', ' ', ' 000000', ' ', ' ', ' ', ' ', ' ', ' ');
commit;
prompt 11 records loaded
prompt Enabling triggers for TBRANCH...
alter table TBRANCH enable all triggers;
set feedback on
set define on
prompt Done.
