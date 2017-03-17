prompt PL/SQL Developer import file
prompt Created on 2016Äê8ÔÂ4ÈÕ by wzc123
set feedback off
set define off
prompt Creating TRISK_BILL...
create table TRISK_BILL
(
  id               INTEGER not null,
  bill_no          VARCHAR2(30) not null,
  msg_id           VARCHAR2(30),
  bill_type        VARCHAR2(20),
  acceptor         VARCHAR2(200),
  acceptor_bank_no VARCHAR2(30),
  bill_money       NUMBER(20,2) default 0,
  bill_start_date  VARCHAR2(20),
  due_dt           VARCHAR2(20),
  description      VARCHAR2(500),
  status           VARCHAR2(20),
  bill1            VARCHAR2(20),
  bill2            VARCHAR2(20),
  postdate         VARCHAR2(20),
  posttime         VARCHAR2(20),
  enterdate        VARCHAR2(20),
  entertime        VARCHAR2(20),
  remitter         VARCHAR2(300),
  urgeapplyname    VARCHAR2(200),
  courtname        VARCHAR2(200),
  matchfileno      VARCHAR2(50),
  issue_dt         VARCHAR2(20) not null
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
alter table TRISK_BILL
  add constraint PK_RISK_BILL primary key (BILL_NO, ISSUE_DT)
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
alter index PK_RISK_BILL nologging;

prompt Disabling triggers for TRISK_BILL...
alter table TRISK_BILL disable all triggers;
prompt Loading TRISK_BILL...
insert into TRISK_BILL (id, bill_no, msg_id, bill_type, acceptor, acceptor_bank_no, bill_money, bill_start_date, due_dt, description, status, bill1, bill2, postdate, posttime, enterdate, entertime, remitter, urgeapplyname, courtname, matchfileno, issue_dt)
values (0, '3241234', ' ', '1', '1234234134', ' ', 1234234, ' ', '2016-08-03', '41324', '123421', '1324123', '123412', '2016-08-30', ' ', '2016-08-17', ' ', '13241234', '234234', '34234123', '12321332', '2016-08-02');
insert into TRISK_BILL (id, bill_no, msg_id, bill_type, acceptor, acceptor_bank_no, bill_money, bill_start_date, due_dt, description, status, bill1, bill2, postdate, posttime, enterdate, entertime, remitter, urgeapplyname, courtname, matchfileno, issue_dt)
values (0, '32452315', ' ', '2', '657567', ' ', 341345, ' ', '2016-08-02', '567567', '1234234', '2341234', '43534', '2016-08-30', ' ', '2016-08-23', ' ', '4567', '657', '567567', '567567', '2016-08-02');
insert into TRISK_BILL (id, bill_no, msg_id, bill_type, acceptor, acceptor_bank_no, bill_money, bill_start_date, due_dt, description, status, bill1, bill2, postdate, posttime, enterdate, entertime, remitter, urgeapplyname, courtname, matchfileno, issue_dt)
values (0, '435345', ' ', '2', '4564563', ' ', 456456, ' ', '2016-08-04', '45556', '63456', '456456', '45645', '2016-08-31', ' ', '2016-08-15', ' ', '3456345', '634563', '1345345', '2435345', '2016-08-09');
commit;
prompt 3 records loaded
prompt Enabling triggers for TRISK_BILL...
alter table TRISK_BILL enable all triggers;
set feedback on
set define on
prompt Done.
