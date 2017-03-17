prompt PL/SQL Developer import file
prompt Created on 2016��7�� ������ by Administrator
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
  is '���ű��';
comment on column TDEPT.dep_name
  is '��������';
comment on column TDEPT.short_name
  is '���ż��';
comment on column TDEPT.parent_code
  is '�ϼ�����';
comment on column TDEPT.branch_code
  is '��������';
comment on column TDEPT.dep_path
  is '��������';
comment on column TDEPT.bank_no
  is '�����б��';
comment on column TDEPT.remark
  is '��ע';
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
values ('1006', '����', '����', ' ', ' ', ' ', ' ', '����');
insert into TDEPT (dep_code, dep_name, short_name, parent_code, branch_code, dep_path, bank_no, remark)
values ('1008', '������', '������', ' ', ' ', ' ', ' ', '������');
insert into TDEPT (dep_code, dep_name, short_name, parent_code, branch_code, dep_path, bank_no, remark)
values ('1000', '����', 'cs', ' ', ' ', ' ', ' ', '�����ʽ��');
insert into TDEPT (dep_code, dep_name, short_name, parent_code, branch_code, dep_path, bank_no, remark)
values ('1001', '����', 'bs', ' ', ' ', ' ', ' ', '�йصķ�������');
insert into TDEPT (dep_code, dep_name, short_name, parent_code, branch_code, dep_path, bank_no, remark)
values ('1002', '���²�', 'ds', ' ', ' ', ' ', ' ', '������Ա������');
insert into TDEPT (dep_code, dep_name, short_name, parent_code, branch_code, dep_path, bank_no, remark)
values ('1007', '����', '����', ' ', ' ', ' ', ' ', '����');
insert into TDEPT (dep_code, dep_name, short_name, parent_code, branch_code, dep_path, bank_no, remark)
values ('1003', '����', '��', ' ', ' ', ' ', ' ', '����');
commit;
prompt 8 records loaded
prompt Enabling triggers for TDEPT...
alter table TDEPT enable all triggers;
set feedback on
set define on
prompt Done.
