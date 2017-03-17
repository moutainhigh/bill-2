prompt PL/SQL Developer import file
prompt Created on 2016年7月 星期三 by Administrator
set feedback off
set define off
prompt Creating TBLACK_LIST...
create table TBLACK_LIST
(
  id              INTEGER,
  enterprise_name VARCHAR2(100),
  union_bankno    VARCHAR2(12) not null,
  description     VARCHAR2(500),
  create_date     VARCHAR2(20),
  oper_brch_no    VARCHAR2(4),
  user_name       VARCHAR2(20)
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
alter table TBLACK_LIST
  add constraint SQL160617100658130 primary key (UNION_BANKNO)
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

prompt Disabling triggers for TBLACK_LIST...
alter table TBLACK_LIST disable all triggers;
prompt Deleting TBLACK_LIST...
delete from TBLACK_LIST;
commit;
prompt Loading TBLACK_LIST...
insert into TBLACK_LIST (id, enterprise_name, union_bankno, description, create_date, oper_brch_no, user_name)
values (0, '银行', '1', '银行', '2016-07-06', '1', ' ');
insert into TBLACK_LIST (id, enterprise_name, union_bankno, description, create_date, oper_brch_no, user_name)
values (0, '111', '1003', '1', '2016-06-27', '4', ' ');
commit;
prompt 2 records loaded
prompt Enabling triggers for TBLACK_LIST...
alter table TBLACK_LIST enable all triggers;
set feedback on
set define on
prompt Done.
