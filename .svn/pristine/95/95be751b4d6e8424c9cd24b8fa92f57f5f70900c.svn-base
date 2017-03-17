prompt PL/SQL Developer import file
prompt Created on 2016Äê8ÔÂ4ÈÕ by sunyingshan
set feedback off
set define off
prompt Creating TRGCT_EXCEPTION_DRAFT...
create table TRGCT_EXCEPTION_DRAFT
(
  log_id           VARCHAR2(30) not null,
  msg_sid          VARCHAR2(50),
  draft_dt         VARCHAR2(20),
  draft_time       VARCHAR2(20),
  draft_no         VARCHAR2(50),
  orgnl_msg_sid    VARCHAR2(50),
  orgnl_draft_dt   VARCHAR2(20),
  orgnl_draft_time VARCHAR2(20),
  orgnl_draft_no   VARCHAR2(50),
  bill_no          VARCHAR2(50),
  in_out           VARCHAR2(1),
  status           VARCHAR2(3)
)
nologging;
alter table TRGCT_EXCEPTION_DRAFT
  add constraint PK_RGCT_EXPT_DRAFT primary key (LOG_ID);
alter index PK_RGCT_EXPT_DRAFT nologging;

prompt Loading TRGCT_EXCEPTION_DRAFT...
insert into TRGCT_EXCEPTION_DRAFT (log_id, msg_sid, draft_dt, draft_time, draft_no, orgnl_msg_sid, orgnl_draft_dt, orgnl_draft_time, orgnl_draft_no, bill_no, in_out, status)
values ('8758', '0000000099682016042910159692', '2016-04-29', null, '060', null, null, null, null, null, '1', '1');
insert into TRGCT_EXCEPTION_DRAFT (log_id, msg_sid, draft_dt, draft_time, draft_no, orgnl_msg_sid, orgnl_draft_dt, orgnl_draft_time, orgnl_draft_no, bill_no, in_out, status)
values ('8759', '0000000099682016042910159698', '2016-04-29', null, '060', null, null, null, null, null, '1', '1');
insert into TRGCT_EXCEPTION_DRAFT (log_id, msg_sid, draft_dt, draft_time, draft_no, orgnl_msg_sid, orgnl_draft_dt, orgnl_draft_time, orgnl_draft_no, bill_no, in_out, status)
values ('8760', '0000000099682016042910159710', '2016-04-29', null, '060', null, null, null, null, null, '1', '0');
insert into TRGCT_EXCEPTION_DRAFT (log_id, msg_sid, draft_dt, draft_time, draft_no, orgnl_msg_sid, orgnl_draft_dt, orgnl_draft_time, orgnl_draft_no, bill_no, in_out, status)
values ('8761', '0000000099682016051210226871', '2016-05-12', null, '005', '3052410000842016051210410249', '2016-05-12', null, '001', '230524100008420160509100218049', '1', '0');
insert into TRGCT_EXCEPTION_DRAFT (log_id, msg_sid, draft_dt, draft_time, draft_no, orgnl_msg_sid, orgnl_draft_dt, orgnl_draft_time, orgnl_draft_no, bill_no, in_out, status)
values ('8762', '0000000099682016051210226872', '2016-05-12', null, '005', '3052410000842016051210410250', '2016-05-12', null, '001', '230524100008420160509100218057', '1', '0');
insert into TRGCT_EXCEPTION_DRAFT (log_id, msg_sid, draft_dt, draft_time, draft_no, orgnl_msg_sid, orgnl_draft_dt, orgnl_draft_time, orgnl_draft_no, bill_no, in_out, status)
values ('8763', '0000000099682016051210226873', '2016-05-12', null, '005', '3052410000842016051210410251', '2016-05-12', null, '001', '230524100008420160509100218065', '1', '0');
insert into TRGCT_EXCEPTION_DRAFT (log_id, msg_sid, draft_dt, draft_time, draft_no, orgnl_msg_sid, orgnl_draft_dt, orgnl_draft_time, orgnl_draft_no, bill_no, in_out, status)
values ('8764', '0000000099682016051210226874', '2016-05-12', null, '005', '3052410000842016051210410252', '2016-05-12', null, '001', '230524100008420160509100218073', '1', '0');
insert into TRGCT_EXCEPTION_DRAFT (log_id, msg_sid, draft_dt, draft_time, draft_no, orgnl_msg_sid, orgnl_draft_dt, orgnl_draft_time, orgnl_draft_no, bill_no, in_out, status)
values ('8765', '0000000099682016051210226875', '2016-05-12', null, '005', '3052410000842016051210410253', '2016-05-12', null, '001', '230524100008420160509100218081', '1', '0');
insert into TRGCT_EXCEPTION_DRAFT (log_id, msg_sid, draft_dt, draft_time, draft_no, orgnl_msg_sid, orgnl_draft_dt, orgnl_draft_time, orgnl_draft_no, bill_no, in_out, status)
values ('8766', '0000000099682016051210226876', '2016-05-12', null, '005', '3052410000842016051210410254', '2016-05-12', null, '001', '230524100008420160509100218090', '1', '0');
commit;
prompt 9 records loaded
set feedback on
set define on
prompt Done.
