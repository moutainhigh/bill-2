prompt PL/SQL Developer import file
prompt Created on 2016年7月27日 by lenovo
set feedback off
set define off
prompt Creating TCUST_MANAGE...
create table TCUST_MANAGE
(
  ID                INTEGER,
  CUST_MANAGER_NO   VARCHAR2(30) not null,
  CUST_MANAGER_NAME VARCHAR2(50),
  STATUS            VARCHAR2(10),
  MANAGE_TYPE       VARCHAR2(10),
  BRANCH_NO         VARCHAR2(20),
  DEL_FLAG          VARCHAR2(1),
  DEL_DT            VARCHAR2(20),
  DEL_TIME          VARCHAR2(20),
  DEL_OPER_NAME     VARCHAR2(20),
  DEL_OPER_NO       VARCHAR2(20),
  CREATE_DT         VARCHAR2(20),
  CREATE_TIME       VARCHAR2(20),
  CREATE_OPER_NAME  VARCHAR2(20),
  CREATE_OPER_NO    VARCHAR2(20),
  DEPT_NAME         VARCHAR2(200),
  DEPT_NO           INTEGER
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
-- Create/Recreate primary, unique and foreign key constraints 
alter table TCUST_MANAGE
  add constraint PK_CUST_MANAGE primary key (CUST_MANAGER_NO)
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

