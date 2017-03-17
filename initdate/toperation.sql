prompt PL/SQL Developer import file
prompt Created on 2016Äê7ÔÂ27ÈÕ by lenovo
set feedback off
set define off
prompt Creating TOPERATION...
create table TOPERATION
(
  ID             VARCHAR2(32),
  OPERATION_CODE VARCHAR2(100) default ' ' not null,
  OPERATION_NAME VARCHAR2(256) default ' ' not null,
  OPERATION_TYPE VARCHAR2(1) default ' ' not null,
  MENU_CODE      VARCHAR2(32) default ' ' not null,
  STATUS         VARCHAR2(1) default ' ' not null,
  AUTH_FLAG      VARCHAR2(1) default ' ' not null,
  LOGON_FLAG     VARCHAR2(1) default ' ' not null,
  REMARK         VARCHAR2(256) default ' ' not null
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
alter table TOPERATION
  add constraint PK_TOPERATION primary key (OPERATION_CODE, MENU_CODE)
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

prompt Disabling triggers for TOPERATION...
alter table TOPERATION disable all triggers;
prompt Deleting TOPERATION...
delete from TOPERATION;
commit;
prompt Loading TOPERATION...
insert into TOPERATION (ID, OPERATION_CODE, OPERATION_NAME, OPERATION_TYPE, MENU_CODE, STATUS, AUTH_FLAG, LOGON_FLAG, REMARK)
values (null, 'm', 'm', ' ', 'm', ' ', ' ', ' ', ' ');
insert into TOPERATION (ID, OPERATION_CODE, OPERATION_NAME, OPERATION_TYPE, MENU_CODE, STATUS, AUTH_FLAG, LOGON_FLAG, REMARK)
values (null, 'l', 'l', ' ', 'l', ' ', ' ', ' ', ' ');
insert into TOPERATION (ID, OPERATION_CODE, OPERATION_NAME, OPERATION_TYPE, MENU_CODE, STATUS, AUTH_FLAG, LOGON_FLAG, REMARK)
values (null, 'c', 'c', ' ', 'c', ' ', ' ', ' ', ' ');
insert into TOPERATION (ID, OPERATION_CODE, OPERATION_NAME, OPERATION_TYPE, MENU_CODE, STATUS, AUTH_FLAG, LOGON_FLAG, REMARK)
values (null, 'z', 'z', ' ', 'z', ' ', ' ', ' ', ' ');
insert into TOPERATION (ID, OPERATION_CODE, OPERATION_NAME, OPERATION_TYPE, MENU_CODE, STATUS, AUTH_FLAG, LOGON_FLAG, REMARK)
values (null, 'a', 'a', ' ', 'a', ' ', ' ', ' ', ' ');
insert into TOPERATION (ID, OPERATION_CODE, OPERATION_NAME, OPERATION_TYPE, MENU_CODE, STATUS, AUTH_FLAG, LOGON_FLAG, REMARK)
values (null, 'w', 'w', ' ', 'w', ' ', ' ', ' ', ' ');
insert into TOPERATION (ID, OPERATION_CODE, OPERATION_NAME, OPERATION_TYPE, MENU_CODE, STATUS, AUTH_FLAG, LOGON_FLAG, REMARK)
values (null, 'ss', 'ss', ' ', 'ss', ' ', ' ', ' ', ' ');
commit;
prompt 7 records loaded
prompt Enabling triggers for TOPERATION...
alter table TOPERATION enable all triggers;
set feedback on
set define on
prompt Done.
