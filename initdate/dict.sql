delete from tdict_group where dict_group_code='K_YORN';
insert into TDICT_GROUP (DICT_GROUP_CODE, KIND_CODE, DICT_GROUP_NAME, KERNAL_FLAG, REMARK)
values ('K_YORN', '0', '是否标志', '1', ' ');

delete from TDICT_ITEM where dict_group_code='K_YORN';
insert into TDICT_ITEM (DICT_GROUP_CODE, DICT_ITEM_CODE, DICT_ITEM_VALUE)
values ('K_YORN', '0', '否');
insert into TDICT_ITEM (DICT_GROUP_CODE, DICT_ITEM_CODE, DICT_ITEM_VALUE)
values ('K_YORN', '1', '是');

delete from tdict_group where dict_group_code='K_QYBZ';
insert into TDICT_GROUP (DICT_GROUP_CODE, KIND_CODE, DICT_GROUP_NAME, KERNAL_FLAG, REMARK)
values ('K_QYBZ', '0', '启用标志', '1', ' ');

delete from TDICT_ITEM where dict_group_code='K_QYBZ';
insert into TDICT_ITEM (DICT_GROUP_CODE, DICT_ITEM_CODE, DICT_ITEM_VALUE)
values ('K_QYBZ', '0', '未启用');
insert into TDICT_ITEM (DICT_GROUP_CODE, DICT_ITEM_CODE, DICT_ITEM_VALUE)
values ('K_QYBZ', '1', '启用');


delete from tdict_group where dict_group_code='K_KTBZ';
insert into TDICT_GROUP (DICT_GROUP_CODE, KIND_CODE, DICT_GROUP_NAME, KERNAL_FLAG, REMARK)
values ('K_KTBZ', '0', '开通标志', '1', ' ');

delete from TDICT_ITEM where dict_group_code='K_KTBZ';
insert into TDICT_ITEM (DICT_GROUP_CODE, DICT_ITEM_CODE, DICT_ITEM_VALUE)
values ('K_KTBZ', '0', '未开通');
insert into TDICT_ITEM (DICT_GROUP_CODE, DICT_ITEM_CODE, DICT_ITEM_VALUE)
values ('K_KTBZ', '1', '已开通');


delete from tdict_group where dict_group_code='K_PJCPLX';
insert into TDICT_GROUP (DICT_GROUP_CODE, KIND_CODE, DICT_GROUP_NAME, KERNAL_FLAG, REMARK)
values ('K_PJCPLX', '0', '产品类型标识', '1', ' ');

delete from TDICT_ITEM where dict_group_code='K_PJCPLX';
insert into TDICT_ITEM (DICT_GROUP_CODE, DICT_ITEM_CODE, DICT_ITEM_VALUE)
values ('K_PJCPLX', '0', '贴现产品');
insert into TDICT_ITEM (DICT_GROUP_CODE, DICT_ITEM_CODE, DICT_ITEM_VALUE)
values ('K_PJCPLX', '1', '转贴现产品');

delete from tdict_group where dict_group_code='K_USERSTATUS';
insert into TDICT_GROUP (DICT_GROUP_CODE, KIND_CODE, DICT_GROUP_NAME, KERNAL_FLAG, REMARK)
values ('K_USERSTATUS', '0', '用户状态', '1', ' ');

delete from TDICT_ITEM where dict_group_code='K_USERSTATUS';
insert into TDICT_ITEM (DICT_GROUP_CODE, DICT_ITEM_CODE, DICT_ITEM_VALUE)
values ('K_USERSTATUS', '0', '正常');
insert into TDICT_ITEM (DICT_GROUP_CODE, DICT_ITEM_CODE, DICT_ITEM_VALUE)
values ('K_USERSTATUS', '1', '注销');
insert into TDICT_ITEM (DICT_GROUP_CODE, DICT_ITEM_CODE, DICT_ITEM_VALUE)
values ('K_USERSTATUS', '2', '锁定');

delete from tdict_group where dict_group_code='K_LOGONSTATUS';
insert into TDICT_GROUP (DICT_GROUP_CODE, KIND_CODE, DICT_GROUP_NAME, KERNAL_FLAG, REMARK)
values ('K_LOGONSTATUS', '0', '登录状态', '1', ' ');

delete from TDICT_ITEM where dict_group_code='K_LOGONSTATUS';
insert into TDICT_ITEM (DICT_GROUP_CODE, DICT_ITEM_CODE, DICT_ITEM_VALUE)
values ('K_LOGONSTATUS', '0', '签退');
insert into TDICT_ITEM (DICT_GROUP_CODE, DICT_ITEM_CODE, DICT_ITEM_VALUE)
values ('K_LOGONSTATUS', '1', '登录');
insert into TDICT_ITEM (DICT_GROUP_CODE, DICT_ITEM_CODE, DICT_ITEM_VALUE)
values ('K_LOGONSTATUS', '2', '非正常签退');

delete from tdict_group where dict_group_code='K_PRDTYPE';
insert into TDICT_GROUP (DICT_GROUP_CODE, KIND_CODE, DICT_GROUP_NAME, KERNAL_FLAG, REMARK)
values ('K_PRDTYPE', '0', '服务产品编号', '1', ' ');

delete from TDICT_ITEM where dict_group_code='K_PRDTYPE';
insert into TDICT_ITEM (DICT_GROUP_CODE, DICT_ITEM_CODE, DICT_ITEM_VALUE)
values ('K_PRDTYPE', 'S511001001', '票据池');
insert into TDICT_ITEM (DICT_GROUP_CODE, DICT_ITEM_CODE, DICT_ITEM_VALUE)
values ('K_PRDTYPE', 'S511002001', '电子票据');
insert into TDICT_ITEM (DICT_GROUP_CODE, DICT_ITEM_CODE, DICT_ITEM_VALUE)
values ('K_PRDTYPE', 'S511003001', '银行承兑汇票');
insert into TDICT_ITEM (DICT_GROUP_CODE, DICT_ITEM_CODE, DICT_ITEM_VALUE)
values ('K_PRDTYPE', 'S511004001', '银行承兑汇票(商票)');

delete from tdict_group where dict_group_code='K_SIGNSTATUS';
insert into TDICT_GROUP (DICT_GROUP_CODE, KIND_CODE, DICT_GROUP_NAME, KERNAL_FLAG, REMARK)
values ('K_SIGNSTATUS', '0', '签约状态', '1', ' ');

delete from TDICT_ITEM where dict_group_code='K_SIGNSTATUS';
insert into TDICT_ITEM (DICT_GROUP_CODE, DICT_ITEM_CODE, DICT_ITEM_VALUE)
values ('K_SIGNSTATUS', '01', '签约');
insert into TDICT_ITEM (DICT_GROUP_CODE, DICT_ITEM_CODE, DICT_ITEM_VALUE)
values ('K_SIGNSTATUS', '02', '解约');

delete from tdict_group where dict_group_code='K_OPENFLAG';
insert into TDICT_GROUP (DICT_GROUP_CODE, KIND_CODE, DICT_GROUP_NAME, KERNAL_FLAG, REMARK)
values ('K_OPENFLAG', '0', '开通标志', '1', ' ');

delete from TDICT_ITEM where dict_group_code='K_OPENFLAG';
insert into TDICT_ITEM (DICT_GROUP_CODE, DICT_ITEM_CODE, DICT_ITEM_VALUE)
values ('K_OPENFLAG', '0', '不开通');
insert into TDICT_ITEM (DICT_GROUP_CODE, DICT_ITEM_CODE, DICT_ITEM_VALUE)
values ('K_OPENFLAG', '1', '开通');

delete from tdict_group where dict_group_code='K_SYSSTATUS';
insert into TDICT_GROUP (DICT_GROUP_CODE, KIND_CODE, DICT_GROUP_NAME, KERNAL_FLAG, REMARK)
values ('K_SYSSTATUS', '0', '系统状态', '1', ' ');

delete from TDICT_ITEM where dict_group_code='K_SYSSTATUS';
insert into TDICT_ITEM (DICT_GROUP_CODE, DICT_ITEM_CODE, DICT_ITEM_VALUE)
values ('K_SYSSTATUS', '0', '正常');
insert into TDICT_ITEM (DICT_GROUP_CODE, DICT_ITEM_CODE, DICT_ITEM_VALUE)
values ('K_SYSSTATUS', '1', '暂停交易');



delete from tdict_group where dict_group_code='K_BILL_TYPE';
insert into TDICT_GROUP (DICT_GROUP_CODE, KIND_CODE, DICT_GROUP_NAME, KERNAL_FLAG, REMARK)
values ('K_BILL_TYPE', '0', '票据类型', '1', ' ');


delete from TDICT_ITEM where dict_group_code='K_BILL_TYPE';
insert into TDICT_ITEM (DICT_GROUP_CODE, DICT_ITEM_CODE, DICT_ITEM_VALUE)
values ('K_BILL_TYPE', '1', '银票');
insert into TDICT_ITEM (DICT_GROUP_CODE, DICT_ITEM_CODE, DICT_ITEM_VALUE)
values ('K_BILL_TYPE', '2', '商票');



