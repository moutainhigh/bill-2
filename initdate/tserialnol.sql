prompt PL/SQL Developer import file
prompt Created on 2016Äê7ÔÂ27ÈÕ by wzc123
set feedback off
set define off
prompt Creating TSERIALNO...
create table TSERIALNO
(
  key_no     VARCHAR2(20) default ' ' not null,
  curr_value INTEGER default 0 not null,
  inc        INTEGER default 1 not null,
  auto_reset INTEGER default 0 not null,
  max_value  INTEGER default 0 not null,
  min_value  INTEGER default 0 not null
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
alter table TSERIALNO
  add constraint PK_TSERIALNO primary key (KEY_NO)
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
alter index PK_TSERIALNO nologging;

prompt Disabling triggers for TSERIALNO...
alter table TSERIALNO disable all triggers;
prompt Loading TSERIALNO...
insert into TSERIALNO (key_no, curr_value, inc, auto_reset, max_value, min_value)
values ('213', 123123, 12312, 0, 123, 1231);
insert into TSERIALNO (key_no, curr_value, inc, auto_reset, max_value, min_value)
values ('123123', 213123, 12312, 0, 213123, 72);
commit;
prompt 2 records loaded
prompt Enabling triggers for TSERIALNO...
alter table TSERIALNO enable all triggers;
set feedback on
set define on
prompt Done.
