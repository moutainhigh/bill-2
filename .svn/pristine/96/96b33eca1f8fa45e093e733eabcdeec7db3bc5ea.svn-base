prompt PL/SQL Developer import file
prompt Created on 2016年7月 星期三 by Administrator
set feedback off
set define off
prompt Creating TCACHEITEM...
create table TCACHEITEM
(
  cache_code   VARCHAR2(20) default ' ' not null,
  cache_name   VARCHAR2(250) default ' ' not null,
  cache_class  VARCHAR2(250) default ' ' not null,
  cache_method VARCHAR2(250) default ' ' not null,
  remark       VARCHAR2(250) default ' ' not null,
  bank_no      VARCHAR2(32) default ' ' not null,
  reserve1     VARCHAR2(250) default ' ' not null
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
alter table TCACHEITEM
  add constraint PK_TCACHEITEM primary key (CACHE_CODE)
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

prompt Disabling triggers for TCACHEITEM...
alter table TCACHEITEM disable all triggers;
prompt Deleting TCACHEITEM...
delete from TCACHEITEM;
commit;
prompt Loading TCACHEITEM...
insert into TCACHEITEM (cache_code, cache_name, cache_class, cache_method, remark, bank_no, reserve1)
values ('4', '阿斯达斯的', 'com.herongtech.console.cache.CacheRefresh', 'dictCacheRefresh', '是的撒', ' ', '阿斯达斯');
insert into TCACHEITEM (cache_code, cache_name, cache_class, cache_method, remark, bank_no, reserve1)
values ('5', '非官方的', 'com.herongtech.console.cache.CacheRefresh', 'errMsgCacheRefresh', '豆腐干', ' ', '豆腐干');
insert into TCACHEITEM (cache_code, cache_name, cache_class, cache_method, remark, bank_no, reserve1)
values ('1', '法克', '撒旦', '阿斯达', '阿斯达斯', ' ', '阿斯达斯的');
insert into TCACHEITEM (cache_code, cache_name, cache_class, cache_method, remark, bank_no, reserve1)
values ('2', '美国', '阿斯达斯', '闪电似的', '闪电似的', ' ', '闪电似的');
insert into TCACHEITEM (cache_code, cache_name, cache_class, cache_method, remark, bank_no, reserve1)
values ('3', '欧洲', '洒洒水', '闪电似的', '是的撒', ' ', '辅导费');
commit;
prompt 5 records loaded
prompt Enabling triggers for TCACHEITEM...
alter table TCACHEITEM enable all triggers;
set feedback on
set define on
prompt Done.
