prompt PL/SQL Developer import file
prompt Created on 2016Äê8ÔÂ4ÈÕ by wzc123
set feedback off
set define off
prompt Creating TBUSI_DATE...
create table TBUSI_DATE
(
  id               INTEGER default 0 not null,
  sys_bank_no      VARCHAR2(32) default '000' not null,
  workday          VARCHAR2(20) default '0',
  host_check_date  VARCHAR2(20) default '0',
  trans_check_date VARCHAR2(20) default '0',
  sys_status       VARCHAR2(20) default ' ',
  flag1            INTEGER default 0,
  flag2            VARCHAR2(20) default ' '
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
alter table TBUSI_DATE
  add constraint PK_BUSI_DATE primary key (SYS_BANK_NO)
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
alter index PK_BUSI_DATE nologging;

prompt Disabling triggers for TBUSI_DATE...
alter table TBUSI_DATE disable all triggers;
prompt Loading TBUSI_DATE...
insert into TBUSI_DATE (id, sys_bank_no, workday, host_check_date, trans_check_date, sys_status, flag1, flag2)
values (0, '00000', '2016-08-17', '2016-08-16', ' ', '1', 0, ' ');
commit;
prompt 1 records loaded
prompt Enabling triggers for TBUSI_DATE...
alter table TBUSI_DATE enable all triggers;
set feedback on
set define on
prompt Done.
