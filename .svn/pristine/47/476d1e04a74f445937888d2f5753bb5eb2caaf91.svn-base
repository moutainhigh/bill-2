prompt PL/SQL Developer import file
prompt Created on 2016年7月 星期三 by Administrator
set feedback off
set define off
prompt Creating TDEPT...
create table TDEPT
(
  dep_code    VARCHAR2(16) default ' ' not null,
  dep_name    VARCHAR2(64) default ' ' not null,
  short_name  VARCHAR2(32) default ' ' not null,
  parent_code VARCHAR2(16) default ' ' not null,
  branch_code VARCHAR2(16) default ' ' not null,
  dep_path    VARCHAR2(256) default ' ' not null,
  bank_no     VARCHAR2(32) default ' ' not null,
  remark      VARCHAR2(256) default ' ' not null
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
comment on column TDEPT.dep_code
  is '部门编号';
comment on column TDEPT.dep_name
  is '部门名称';
comment on column TDEPT.short_name
  is '部门简称';
comment on column TDEPT.parent_code
  is '上级部门';
comment on column TDEPT.branch_code
  is '所属机构';
comment on column TDEPT.dep_path
  is '部门内码';
comment on column TDEPT.bank_no
  is '法人行编号';
comment on column TDEPT.remark
  is '备注';
alter table TDEPT
  add constraint PK_TDEPT primary key (DEP_CODE)
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

prompt Disabling triggers for TDEPT...
alter table TDEPT disable all triggers;
prompt Deleting TDEPT...
delete from TDEPT;
commit;
prompt Loading TDEPT...
insert into TDEPT (dep_code, dep_name, short_name, parent_code, branch_code, dep_path, bank_no, remark)
values ('111333', '1', '1', ' ', ' ', ' ', ' ', '1');
insert into TDEPT (dep_code, dep_name, short_name, parent_code, branch_code, dep_path, bank_no, remark)
values ('1006', '大门', '大门', ' ', ' ', ' ', ' ', '大门');
insert into TDEPT (dep_code, dep_name, short_name, parent_code, branch_code, dep_path, bank_no, remark)
values ('1008', '阿萨德', '阿萨德', ' ', ' ', ' ', ' ', '阿萨德');
insert into TDEPT (dep_code, dep_name, short_name, parent_code, branch_code, dep_path, bank_no, remark)
values ('1000', '财务部', 'cs', ' ', ' ', ' ', ' ', '管理资金的');
insert into TDEPT (dep_code, dep_name, short_name, parent_code, branch_code, dep_path, bank_no, remark)
values ('1001', '法务部', 'bs', ' ', ' ', ' ', ' ', '有关的法律事宜');
insert into TDEPT (dep_code, dep_name, short_name, parent_code, branch_code, dep_path, bank_no, remark)
values ('1002', '人事部', 'ds', ' ', ' ', ' ', ' ', '管理人员的流动');
insert into TDEPT (dep_code, dep_name, short_name, parent_code, branch_code, dep_path, bank_no, remark)
values ('1007', '法务', '法务', ' ', ' ', ' ', ' ', '法务');
insert into TDEPT (dep_code, dep_name, short_name, parent_code, branch_code, dep_path, bank_no, remark)
values ('1003', '部门', '门', ' ', ' ', ' ', ' ', '阿门');
commit;
prompt 8 records loaded
prompt Enabling triggers for TDEPT...
alter table TDEPT enable all triggers;
set feedback on
set define on
prompt Done.
