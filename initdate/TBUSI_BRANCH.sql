prompt PL/SQL Developer import file
prompt Created on 2016Äê7ÔÂ27ÈÕ by sunyingshan
set feedback off
set define off
prompt Creating TBUSI_BRANCH...
create table TBUSI_BRANCH
(
  brch_no      VARCHAR2(20) not null,
  name         VARCHAR2(200),
  branch_level VARCHAR2(20),
  status       VARCHAR2(20),
  parent_id    VARCHAR2(20),
  valid_date   VARCHAR2(20),
  invalid_date VARCHAR2(20),
  syb_category VARCHAR2(20)
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
alter table TBUSI_BRANCH
  add constraint SQL160617101024370 primary key (BRCH_NO)
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
alter index SQL160617101024370 nologging;

prompt Disabling triggers for TBUSI_BRANCH...
alter table TBUSI_BRANCH disable all triggers;
prompt Loading TBUSI_BRANCH...
insert into TBUSI_BRANCH (brch_no, name, branch_level, status, parent_id, valid_date, invalid_date, syb_category)
values ('56', '778', ' ', '1', ' ', ' ', ' ', ' ');
insert into TBUSI_BRANCH (brch_no, name, branch_level, status, parent_id, valid_date, invalid_date, syb_category)
values ('77', '88', ' ', '1', ' ', ' ', ' ', ' ');
insert into TBUSI_BRANCH (brch_no, name, branch_level, status, parent_id, valid_date, invalid_date, syb_category)
values ('5', '5', ' ', '0', ' ', ' ', ' ', ' ');
insert into TBUSI_BRANCH (brch_no, name, branch_level, status, parent_id, valid_date, invalid_date, syb_category)
values ('88', '88', ' ', '0', ' ', ' ', ' ', ' ');
insert into TBUSI_BRANCH (brch_no, name, branch_level, status, parent_id, valid_date, invalid_date, syb_category)
values ('566', '3', ' ', '0', ' ', ' ', ' ', ' ');
commit;
prompt 5 records loaded
prompt Enabling triggers for TBUSI_BRANCH...
alter table TBUSI_BRANCH enable all triggers;
set feedback on
set define on
prompt Done.
