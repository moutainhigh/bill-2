prompt PL/SQL Developer import file
prompt Created on 2016年8月4日 by sunyingshan
set feedback off
set define off
prompt Creating TDELAY_RULE...
create table TDELAY_RULE
(
  id         INTEGER not null,
  prod_no    VARCHAR2(50),
  bill_class VARCHAR2(20),
  bill_type  VARCHAR2(20),
  delay_type VARCHAR2(20),
  delay_days INTEGER default 0,
  oper_type  VARCHAR2(20),
  rule_desc  VARCHAR2(500),
  branch_no  VARCHAR2(50)
)
nologging;
comment on column TDELAY_RULE.prod_no
  is '[K_PRDTYPE]
S511001001-票据池
S511002001-电子票据
S511003001-银行承兑汇票
S511004001-银行承兑汇票(商票)';
comment on column TDELAY_RULE.bill_type
  is '[K_BILL_TYPE]
1-银票
2-商票';
comment on column TDELAY_RULE.delay_type
  is '[K_SYFS]
0-不顺延
1-只顺延节假日
2-只顺延异地
3-先节假日再异地
4-先异地再节假日
5-指定顺延天数';
comment on column TDELAY_RULE.oper_type
  is '[K_DELAYCZFS]
0-自动
1-手动选择
2-只选择天数';
alter table TDELAY_RULE
  add constraint PK_DELAY_RULE primary key (ID);
alter index PK_DELAY_RULE nologging;

prompt Loading TDELAY_RULE...
insert into TDELAY_RULE (id, prod_no, bill_class, bill_type, delay_type, delay_days, oper_type, rule_desc, branch_no)
values (1, 'S511001001', '2', '1', '5', 6, '1', ' ', ' ');
insert into TDELAY_RULE (id, prod_no, bill_class, bill_type, delay_type, delay_days, oper_type, rule_desc, branch_no)
values (2, 'S511002001', '2', '1', '5', 4, '2', ' ', ' ');
insert into TDELAY_RULE (id, prod_no, bill_class, bill_type, delay_type, delay_days, oper_type, rule_desc, branch_no)
values (3, 'S511002001', '7', '2', '5', 8, '0', ' ', ' ');
insert into TDELAY_RULE (id, prod_no, bill_class, bill_type, delay_type, delay_days, oper_type, rule_desc, branch_no)
values (4, 'S511003001', '9', '2', '1', 0, '1', ' ', ' ');
insert into TDELAY_RULE (id, prod_no, bill_class, bill_type, delay_type, delay_days, oper_type, rule_desc, branch_no)
values (5, 'S511002001', '9', '2', '4', 0, '0', ' ', ' ');
insert into TDELAY_RULE (id, prod_no, bill_class, bill_type, delay_type, delay_days, oper_type, rule_desc, branch_no)
values (6, 'S511003001', '5', '1', '5', 6, '0', ' ', ' ');
commit;
prompt 6 records loaded
set feedback on
set define on
prompt Done.
