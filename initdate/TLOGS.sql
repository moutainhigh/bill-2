prompt PL/SQL Developer import file
prompt Created on 2016Äê7ÔÂ27ÈÕ by sunyingshan
set feedback off
set define off
prompt Creating TLOGS...
create table TLOGS
(
  log_serial     INTEGER default 0 not null,
  user_id        VARCHAR2(32) default ' ' not null,
  menu_code      VARCHAR2(32) default ' ' not null,
  operation_code VARCHAR2(100) default ' ' not null,
  terminal_no    VARCHAR2(16) default ' ' not null,
  op_date        VARCHAR2(20) default 0 not null,
  op_time        VARCHAR2(20) default 0 not null,
  auth_user      VARCHAR2(32) default ' ' not null,
  ip             VARCHAR2(15) default ' ' not null,
  summary        VARCHAR2(256) default ' ' not null,
  log_file       VARCHAR2(60) default ' ' not null,
  broswer        VARCHAR2(15) default ' ' not null
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
create index INDEX_LOG_SERIAL on TLOGS (LOG_SERIAL)
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
  )
  nologging;

prompt Disabling triggers for TLOGS...
alter table TLOGS disable all triggers;
prompt Loading TLOGS...
insert into TLOGS (log_serial, user_id, menu_code, operation_code, terminal_no, op_date, op_time, auth_user, ip, summary, log_file, broswer)
values (2, '2', '32', '55', '578', '2016-07-11', '11:11', 'bb', '192.', '77', '33', 'IE');
insert into TLOGS (log_serial, user_id, menu_code, operation_code, terminal_no, op_date, op_time, auth_user, ip, summary, log_file, broswer)
values (1, '1', '23', '33', '245', '2016-07-17', '11:11', 'aa', '192.', '66', '44', 'IE');
insert into TLOGS (log_serial, user_id, menu_code, operation_code, terminal_no, op_date, op_time, auth_user, ip, summary, log_file, broswer)
values (4, '4', '88', '99', '786', '2016-07-20', '11:11', 'dd', ' ', ' ', ' ', ' ');
insert into TLOGS (log_serial, user_id, menu_code, operation_code, terminal_no, op_date, op_time, auth_user, ip, summary, log_file, broswer)
values (3, '3', '56', '66', '657', '2016-07-30', '11:11', 'cc', ' ', ' ', ' ', ' ');
commit;
prompt 4 records loaded
prompt Enabling triggers for TLOGS...
alter table TLOGS enable all triggers;
set feedback on
set define on
prompt Done.
