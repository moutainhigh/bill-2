prompt Importing table TMENU...
set feedback off
set define off
insert into TMENU (MENU_CODE, MENU_NAME, MENU_URL, MENU_TYPE, MENU_LEVEL, MENU_ICON, ORDER_NO, PARENT_MENU_CODE, OPEN_FLAG, TREE_IDX, LOGON_FLAG, ICON_DISPLAY, MENU_CLASS, REMARK)
values ('rootMenu', '根目录菜单', ' ', '0', '0', ' ', 1, 'root', '0', 'root', '1', '1', ' ', '		12		');

insert into TMENU (MENU_CODE, MENU_NAME, MENU_URL, MENU_TYPE, MENU_LEVEL, MENU_ICON, ORDER_NO, PARENT_MENU_CODE, OPEN_FLAG, TREE_IDX, LOGON_FLAG, ICON_DISPLAY, MENU_CLASS, REMARK)
values ('systemManage', '系统管理', ' ', '0', '1', ' ', 0, 'rootMenu', '1', '10', '0', '1', ' ', ' ');

insert into TMENU (MENU_CODE, MENU_NAME, MENU_URL, MENU_TYPE, MENU_LEVEL, MENU_ICON, ORDER_NO, PARENT_MENU_CODE, OPEN_FLAG, TREE_IDX, LOGON_FLAG, ICON_DISPLAY, MENU_CLASS, REMARK)
values ('sysErrmsgManage', '错误管理', 'sysErrmsgController.do?list', '0', '2', ' ', 5, 'systemManage', '1', ' ', '1', '1', ' ', ' ');

insert into TMENU (MENU_CODE, MENU_NAME, MENU_URL, MENU_TYPE, MENU_LEVEL, MENU_ICON, ORDER_NO, PARENT_MENU_CODE, OPEN_FLAG, TREE_IDX, LOGON_FLAG, ICON_DISPLAY, MENU_CLASS, REMARK)
values ('bbspProductManage', '产品管理', 'bbspProductController.do?list', '0', '2', ' ', 6, 'systemManage', '1', ' ', '1', '1', ' ', ' ');

insert into TMENU (MENU_CODE, MENU_NAME, MENU_URL, MENU_TYPE, MENU_LEVEL, MENU_ICON, ORDER_NO, PARENT_MENU_CODE, OPEN_FLAG, TREE_IDX, LOGON_FLAG, ICON_DISPLAY, MENU_CLASS, REMARK)
values ('dictManage', '字典管理', 'dictController.do?list', '0', '2', ' ', 6, 'systemManage', '1', ' ', '0', '1', ' ', ' ');

insert into TMENU (MENU_CODE, MENU_NAME, MENU_URL, MENU_TYPE, MENU_LEVEL, MENU_ICON, ORDER_NO, PARENT_MENU_CODE, OPEN_FLAG, TREE_IDX, LOGON_FLAG, ICON_DISPLAY, MENU_CLASS, REMARK)
values ('bankInfoManage', '大行信息', 'bankInfoController.do?list', '0', '2', ' ', 7, 'systemManage', '1', ' ', '0', '1', ' ', ' ');

insert into TMENU (MENU_CODE, MENU_NAME, MENU_URL, MENU_TYPE, MENU_LEVEL, MENU_ICON, ORDER_NO, PARENT_MENU_CODE, OPEN_FLAG, TREE_IDX, LOGON_FLAG, ICON_DISPLAY, MENU_CLASS, REMARK)
values ('serialnoManage', '序列号表维护', 'serialnoController.do?list', '0', '2', ' ', 5, 'systemManage', '1', ' ', '1', '1', ' ', ' ');

insert into TMENU (MENU_CODE, MENU_NAME, MENU_URL, MENU_TYPE, MENU_LEVEL, MENU_ICON, ORDER_NO, PARENT_MENU_CODE, OPEN_FLAG, TREE_IDX, LOGON_FLAG, ICON_DISPLAY, MENU_CLASS, REMARK)
values ('riskBillManage', '风险票维护', 'riskBillController.do?list', '0', '2', ' ', 6, 'systemManage', '1', ' ', '1', '1', ' ', ' ');

insert into TMENU (MENU_CODE, MENU_NAME, MENU_URL, MENU_TYPE, MENU_LEVEL, MENU_ICON, ORDER_NO, PARENT_MENU_CODE, OPEN_FLAG, TREE_IDX, LOGON_FLAG, ICON_DISPLAY, MENU_CLASS, REMARK)
values ('custManage', '经理维护', 'custManagerController.do?list', '0', '2', ' ', 8, 'systemManage', '1', ' ', '1', '1', ' ', ' ');

insert into TMENU (MENU_CODE, MENU_NAME, MENU_URL, MENU_TYPE, MENU_LEVEL, MENU_ICON, ORDER_NO, PARENT_MENU_CODE, OPEN_FLAG, TREE_IDX, LOGON_FLAG, ICON_DISPLAY, MENU_CLASS, REMARK)
values ('sysParamManage', '参数管理', 'sysParamController.do?list', '0', '2', ' ', 4, 'systemManage', '1', '14', '1', '1', ' ', ' ');

insert into TMENU (MENU_CODE, MENU_NAME, MENU_URL, MENU_TYPE, MENU_LEVEL, MENU_ICON, ORDER_NO, PARENT_MENU_CODE, OPEN_FLAG, TREE_IDX, LOGON_FLAG, ICON_DISPLAY, MENU_CLASS, REMARK)
values ('logsManage', '日志管理', 'logsController.do?list', '0', '2', ' ', 5, 'systemManage', '1', ' ', '1', '1', ' ', ' ');

insert into TMENU (MENU_CODE, MENU_NAME, MENU_URL, MENU_TYPE, MENU_LEVEL, MENU_ICON, ORDER_NO, PARENT_MENU_CODE, OPEN_FLAG, TREE_IDX, LOGON_FLAG, ICON_DISPLAY, MENU_CLASS, REMARK)
values ('blackListMaintain', '黑名单维护', 'blackController.do?listBlack', '0', '2', ' ', 6, 'systemManage', '1', ' ', '0', '1', ' ', ' ');

insert into TMENU (MENU_CODE, MENU_NAME, MENU_URL, MENU_TYPE, MENU_LEVEL, MENU_ICON, ORDER_NO, PARENT_MENU_CODE, OPEN_FLAG, TREE_IDX, LOGON_FLAG, ICON_DISPLAY, MENU_CLASS, REMARK)
values ('departmentManage', '部门管理', 'deptController.do?listDept', '0', '2', ' ', 5, 'systemManage', '1', ' ', '0', '1', ' ', ' ');

insert into TMENU (MENU_CODE, MENU_NAME, MENU_URL, MENU_TYPE, MENU_LEVEL, MENU_ICON, ORDER_NO, PARENT_MENU_CODE, OPEN_FLAG, TREE_IDX, LOGON_FLAG, ICON_DISPLAY, MENU_CLASS, REMARK)
values ('userManage', '用户管理', 'userController.do?listUsers', '0', '2', ' ', 3, 'systemManage', '1', '13', '0', '1', ' ', ' ');

insert into TMENU (MENU_CODE, MENU_NAME, MENU_URL, MENU_TYPE, MENU_LEVEL, MENU_ICON, ORDER_NO, PARENT_MENU_CODE, OPEN_FLAG, TREE_IDX, LOGON_FLAG, ICON_DISPLAY, MENU_CLASS, REMARK)
values ('roleManage', '角色管理', 'roleController.do?list', '0', '2', ' ', 2, 'systemManage', '1', '12', '0', '1', ' ', ' ');

insert into TMENU (MENU_CODE, MENU_NAME, MENU_URL, MENU_TYPE, MENU_LEVEL, MENU_ICON, ORDER_NO, PARENT_MENU_CODE, OPEN_FLAG, TREE_IDX, LOGON_FLAG, ICON_DISPLAY, MENU_CLASS, REMARK)
values ('menuManage', '菜单管理', 'menuController.do?list', '0', '2', ' ', 1, 'systemManage', '0', '11', '0', '1', ' ', ' ');

insert into TMENU (MENU_CODE, MENU_NAME, MENU_URL, MENU_TYPE, MENU_LEVEL, MENU_ICON, ORDER_NO, PARENT_MENU_CODE, OPEN_FLAG, TREE_IDX, LOGON_FLAG, ICON_DISPLAY, MENU_CLASS, REMARK)
values ('bbbManage', '菜单', 'menuController.do?list', '0', '3', ' ', 1, 'menuManage', '1', '1101', '0', '1', ' ', ' ');

insert into TMENU (MENU_CODE, MENU_NAME, MENU_URL, MENU_TYPE, MENU_LEVEL, MENU_ICON, ORDER_NO, PARENT_MENU_CODE, OPEN_FLAG, TREE_IDX, LOGON_FLAG, ICON_DISPLAY, MENU_CLASS, REMARK)
values ('aaaManage', '菜单', 'menuController.do?list', '0', '3', ' ', 1, 'menuManage', '1', '1101', '0', '1', ' ', ' ');

insert into TMENU (MENU_CODE, MENU_NAME, MENU_URL, MENU_TYPE, MENU_LEVEL, MENU_ICON, ORDER_NO, PARENT_MENU_CODE, OPEN_FLAG, TREE_IDX, LOGON_FLAG, ICON_DISPLAY, MENU_CLASS, REMARK)
values ('aaa1Manage', '菜单', 'menuController.do?list', '0', '3', ' ', 2, 'menuManage', '1', '1102', '0', '1', ' ', ' ');

insert into TMENU (MENU_CODE, MENU_NAME, MENU_URL, MENU_TYPE, MENU_LEVEL, MENU_ICON, ORDER_NO, PARENT_MENU_CODE, OPEN_FLAG, TREE_IDX, LOGON_FLAG, ICON_DISPLAY, MENU_CLASS, REMARK)
values ('aaa2Manage', '菜单', 'menuController.do?list', '0', '3', ' ', 3, 'menuManage', '1', '1103', '0', '1', ' ', ' ');

insert into TMENU (MENU_CODE, MENU_NAME, MENU_URL, MENU_TYPE, MENU_LEVEL, MENU_ICON, ORDER_NO, PARENT_MENU_CODE, OPEN_FLAG, TREE_IDX, LOGON_FLAG, ICON_DISPLAY, MENU_CLASS, REMARK)
values ('cccManage', '菜单', 'menuController.do?list', '0', '3', ' ', 1, 'menuManage', '1', '1101', '0', '1', ' ', ' ');

insert into TMENU (MENU_CODE, MENU_NAME, MENU_URL, MENU_TYPE, MENU_LEVEL, MENU_ICON, ORDER_NO, PARENT_MENU_CODE, OPEN_FLAG, TREE_IDX, LOGON_FLAG, ICON_DISPLAY, MENU_CLASS, REMARK)
values ('bbb2Manage', '菜单', 'menuController.do?list', '0', '3', ' ', 3, 'menuManage', '1', '1103', '0', '1', ' ', ' ');

insert into TMENU (MENU_CODE, MENU_NAME, MENU_URL, MENU_TYPE, MENU_LEVEL, MENU_ICON, ORDER_NO, PARENT_MENU_CODE, OPEN_FLAG, TREE_IDX, LOGON_FLAG, ICON_DISPLAY, MENU_CLASS, REMARK)
values ('bbb1Manage', '菜单', 'menuController.do?list', '0', '3', ' ', 2, 'menuManage', '1', '1102', '0', '1', ' ', ' ');

insert into TMENU (MENU_CODE, MENU_NAME, MENU_URL, MENU_TYPE, MENU_LEVEL, MENU_ICON, ORDER_NO, PARENT_MENU_CODE, OPEN_FLAG, TREE_IDX, LOGON_FLAG, ICON_DISPLAY, MENU_CLASS, REMARK)
values ('ccc1Manage', '菜单', 'menuController.do?list', '0', '3', ' ', 2, 'menuManage', '1', '1102', '0', '1', ' ', ' ');

insert into TMENU (MENU_CODE, MENU_NAME, MENU_URL, MENU_TYPE, MENU_LEVEL, MENU_ICON, ORDER_NO, PARENT_MENU_CODE, OPEN_FLAG, TREE_IDX, LOGON_FLAG, ICON_DISPLAY, MENU_CLASS, REMARK)
values ('signProdManage', '产品签约', 'signProdController.do?list', '0', '1', ' ', 7, 'systemManage', '1', ' ', '1', '1', ' ', ' ');

insert into TMENU (MENU_CODE, MENU_NAME, MENU_URL, MENU_TYPE, MENU_LEVEL, MENU_ICON, ORDER_NO, PARENT_MENU_CODE, OPEN_FLAG, TREE_IDX, LOGON_FLAG, ICON_DISPLAY, MENU_CLASS, REMARK)
values ('operationManage', '功能菜单维护', 'operationController.do?list', '0', '2', ' ', 6, 'systemManage', '1', ' ', '1', '1', ' ', ' ');

insert into TMENU (MENU_CODE, MENU_NAME, MENU_URL, MENU_TYPE, MENU_LEVEL, MENU_ICON, ORDER_NO, PARENT_MENU_CODE, OPEN_FLAG, TREE_IDX, LOGON_FLAG, ICON_DISPLAY, MENU_CLASS, REMARK)
values ('busiDateManage', '营业时间表维护', 'busiDateController.do?list', '0', '2', ' ', 5, 'systemManage', '1', ' ', '1', '1', ' ', ' ');


insert into TMENU (MENU_CODE, MENU_NAME, MENU_URL, MENU_TYPE, MENU_LEVEL, MENU_ICON, ORDER_NO, PARENT_MENU_CODE, OPEN_FLAG, TREE_IDX, LOGON_FLAG, ICON_DISPLAY, MENU_CLASS, REMARK)
values ('cacheItem', '缓存信息', 'cacheController.do?listCache', '0', '1', ' ', 11, 'systemManage', '1', ' ', '0', '1', ' ', ' ');


insert into TMENU (menu_code, menu_name, menu_url, menu_type, menu_level, menu_icon, order_no, parent_menu_code, open_flag, tree_idx, logon_flag, icon_display, menu_class, remark)
values ('rgctEcdsStatusManage', '系统运营', 'rgctEcdsStatusController.do?list', '0', '1', ' ', 15, 'systemManage', '1', ' ', '1', '1', ' ', ' ');

insert into TMENU (menu_code, menu_name, menu_url, menu_type, menu_level, menu_icon, order_no, parent_menu_code, open_flag, tree_idx, logon_flag, icon_display, menu_class, remark)
values ('workingAdsManage', '经营机构维护', 'workingAdsController.do?list', '0', '1', ' ', 16, 'systemManage', '1', ' ', '1', '1', ' ', ' ');

insert into TMENU (menu_code, menu_name, menu_url, menu_type, menu_level, menu_icon, order_no, parent_menu_code, open_flag, tree_idx, logon_flag, icon_display, menu_class, remark)
values ('delayRuleManage', '顺延规则维护', 'delayRuleController.do?list', '0', '1', ' ', 18, 'systemManage', '1', ' ', '1', '1', ' ', ' ');

insert into TMENU (menu_code, menu_name, menu_url, menu_type, menu_level, menu_icon, order_no, parent_menu_code, open_flag, tree_idx, logon_flag, icon_display, menu_class, remark)
values ('rgctExceptionDraftManage', '异常报文管理', 'rgctExceptionDraftController.do?list', '0', '1', ' ', 17, 'systemManage', '1', ' ', '1', '1', ' ', ' ');

prompt Done.
