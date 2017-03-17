prompt PL/SQL Developer import file
prompt Created on 2016年7月27日 by sunyingshan
set feedback off
set define off
prompt Creating TRGCT_ECDS_STATUS...
create table TRGCT_ECDS_STATUS
(
  pname     VARCHAR2(50) not null,
  pvalue    VARCHAR2(50),
  pname_cn  VARCHAR2(50),
  pvalue_cn VARCHAR2(50)
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
  )
nologging;
alter table TRGCT_ECDS_STATUS
  add constraint SQL160617100742120 primary key (PNAME)
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
alter index SQL160617100742120 nologging;

prompt Disabling triggers for TRGCT_ECDS_STATUS...
alter table TRGCT_ECDS_STATUS disable all triggers;
prompt Loading TRGCT_ECDS_STATUS...
insert into TRGCT_ECDS_STATUS (pname, pvalue, pname_cn, pvalue_cn)
values ('ES001', 'TM00', '登录状态', '登陆');
insert into TRGCT_ECDS_STATUS (pname, pvalue, pname_cn, pvalue_cn)
values ('ES002', '10', '运行时序状态', '日间处理');
insert into TRGCT_ECDS_STATUS (pname, pvalue, pname_cn, pvalue_cn)
values ('ES007', '2016-07-07', '原系统日期', '2016-07-07');
insert into TRGCT_ECDS_STATUS (pname, pvalue, pname_cn, pvalue_cn)
values ('ES008', '00', '原系统状态', '营业前准备');
insert into TRGCT_ECDS_STATUS (pname, pvalue, pname_cn, pvalue_cn)
values ('ES004', '2016-07-08', '下一系统工作日期', '2016-07-08');
insert into TRGCT_ECDS_STATUS (pname, pvalue, pname_cn, pvalue_cn)
values ('ES005', '18:00:00', '营业参考时间', '18:00:00');
insert into TRGCT_ECDS_STATUS (pname, pvalue, pname_cn, pvalue_cn)
values ('ES009', 'SM01', '线上清算标识', '线下清算');
insert into TRGCT_ECDS_STATUS (pname, pvalue, pname_cn, pvalue_cn)
values ('ES010', null, '线上清算标识附言', null);
insert into TRGCT_ECDS_STATUS (pname, pvalue, pname_cn, pvalue_cn)
values ('ES011', '2016-07-08', '大额系统下一工作日期', '2016-07-08');
insert into TRGCT_ECDS_STATUS (pname, pvalue, pname_cn, pvalue_cn)
values ('ES013', 'SO01', '营业前准备批处理开关', '开');
insert into TRGCT_ECDS_STATUS (pname, pvalue, pname_cn, pvalue_cn)
values ('ES003', '2016-07-07', '系统当前日期', '2016-07-07');
commit;
prompt 11 records loaded
prompt Enabling triggers for TRGCT_ECDS_STATUS...
alter table TRGCT_ECDS_STATUS enable all triggers;
set feedback on
set define on
prompt Done.
