prompt PL/SQL Developer import file
prompt Created on 2016��7��29�� by sunyingshan
set feedback off
set define off
prompt Creating TWORKING_ADS...
create table TWORKING_ADS
(
  working_ads_no   VARCHAR2(20) not null,
  working_ads_name VARCHAR2(200),
  status           VARCHAR2(10),
  del_flag         VARCHAR2(2)
)
nologging;
alter table TWORKING_ADS
  add constraint SQL160617100749960 primary key (WORKING_ADS_NO);
alter index SQL160617100749960 nologging;

prompt Loading TWORKING_ADS...
insert into TWORKING_ADS (working_ads_no, working_ads_name, status, del_flag)
values ('2', '����', '0', '1');
insert into TWORKING_ADS (working_ads_no, working_ads_name, status, del_flag)
values ('3', '����', '1', '1');
insert into TWORKING_ADS (working_ads_no, working_ads_name, status, del_flag)
values ('4', ' ��Ŀ����', '1', '0');
insert into TWORKING_ADS (working_ads_no, working_ads_name, status, del_flag)
values ('7', '����', '0', '1');
insert into TWORKING_ADS (working_ads_no, working_ads_name, status, del_flag)
values ('8', ' �ܾ���', '1', '0');
insert into TWORKING_ADS (working_ads_no, working_ads_name, status, del_flag)
values ('1', ' �ܾ���', '0', '0');
insert into TWORKING_ADS (working_ads_no, working_ads_name, status, del_flag)
values ('5', ' �ܾ���', '1', '0');
insert into TWORKING_ADS (working_ads_no, working_ads_name, status, del_flag)
values ('6', ' �ܾ���', '0', '0');
insert into TWORKING_ADS (working_ads_no, working_ads_name, status, del_flag)
values ('11', 'ccc', '1', '1');
insert into TWORKING_ADS (working_ads_no, working_ads_name, status, del_flag)
values ('12', 'ddd', '0', '0');
insert into TWORKING_ADS (working_ads_no, working_ads_name, status, del_flag)
values ('13', 'eee', '1', '0');
insert into TWORKING_ADS (working_ads_no, working_ads_name, status, del_flag)
values ('14', 'fff', '0', '0');
insert into TWORKING_ADS (working_ads_no, working_ads_name, status, del_flag)
values ('15', 'ggg', '1', '0');
insert into TWORKING_ADS (working_ads_no, working_ads_name, status, del_flag)
values ('10', 'aaa', '1', '0');
insert into TWORKING_ADS (working_ads_no, working_ads_name, status, del_flag)
values ('9', ' ��Ŀ����', '0', '0');
commit;
prompt 15 records loaded
set feedback on
set define on
prompt Done.
