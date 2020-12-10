delete from tfilecolumnconfig;
delete from tproduct;
delete from tunit;
delete from tcombi;
delete from tsecurity;
delete from tbond;
delete from treporatio;
delete from tshareholder;
delete from tmarket;
delete from ttradedate;
delete from torder;

SET STREAMING ON;

insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','scdm', 0, 1);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','jllx', 2, 4);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','jyfs', 5, 7);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','jsfs', 8, 10);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','ywlx', 11, 13);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','qsbz', 14, 16);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','ghlx', 17, 19);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','jsbh', 20, 35);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','cjbh', 36, 51);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','sqbh', 52, 67);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','wtbh', 68, 83);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','jyrq', 84, 91);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','qsrq', 92, 99);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','jsrq', 100, 107);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','qtrq', 108, 115);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','wtsj', 116, 121);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','cjsj', 122, 127);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','xwh1', 128, 132);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','xwh2', 133, 137);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','xwhy', 138, 145);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','jshy', 146, 153);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','tghy', 154, 161);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','zqzh', 162, 171);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','zqdm1', 172, 177);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','zqdm2', 178, 183);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','zqlb', 184, 185);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','ltlx', 186, 186);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','qylb', 187, 188);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','gpnf', 189, 192);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','mmbz', 193, 193);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','sl', 194, 209);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','cjsl', 210, 225);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','zjzh', 226, 250);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','bz', 251, 253);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','jg1', 254, 270);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','jg2', 271, 287);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','qsje', 288, 306);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','yhs', 307, 323);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','jsf', 324, 340);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','ghf', 341, 357);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','zgf', 358, 374);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','sxf', 375, 391);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','qtje1', 392, 410);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','qtje2', 411, 429);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','qtje3', 430, 448);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','sjsf', 449, 467);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','jgdm', 468, 471);
insert into tfilecolumnconfig (table_name, column_name, begin_pos, end_pos) values ('tjsmx','fjsm', 472, 511);


insert into tproduct (product_id, product_code, product_name, product_share) values (1, '66601', '测试产品1', 100000);
insert into tproduct (product_id, product_code, product_name, product_share) values (2, '66602', '测试产品2', 100000);
insert into tproduct (product_id, product_code, product_name, product_share) values (3, '66603', '测试产品3', 100000);
insert into tproduct (product_id, product_code, product_name, product_share) values (4, '66604', '测试产品4', 100000);
insert into tproduct (product_id, product_code, product_name, product_share) values (5, '66605', '测试产品5', 100000);


insert into tunit (product_id, unit_id, unit_code, unit_name, default_flag) values (1, 101, '101', '产品1单元1', 1);
insert into tunit (product_id, unit_id, unit_code, unit_name, default_flag) values (1, 102, '102', '产品1单元2', 0);
insert into tunit (product_id, unit_id, unit_code, unit_name, default_flag) values (2, 201, '201', '产品2单元1', 1);
insert into tunit (product_id, unit_id, unit_code, unit_name, default_flag) values (3, 301, '301', '产品3单元1', 1);
insert into tunit (product_id, unit_id, unit_code, unit_name, default_flag) values (4, 401, '401', '产品4单元1', 1);
insert into tunit (product_id, unit_id, unit_code, unit_name, default_flag) values (5, 501, '501', '产品5单元1', 1);

insert into tcombi (product_id, unit_id, combi_id, default_flag, combi_code, combi_name) values (1, 101, 10101, 1, '10101', '产品1单元1组合1');
insert into tcombi (product_id, unit_id, combi_id, default_flag, combi_code, combi_name) values (1, 101, 10102, 0, '10102', '产品1单元1组合2');
insert into tcombi (product_id, unit_id, combi_id, default_flag, combi_code, combi_name) values (1, 102, 10201, 1, '10201', '产品1单元2组合1');
insert into tcombi (product_id, unit_id, combi_id, default_flag, combi_code, combi_name) values (1, 102, 10202, 0, '10202', '产品1单元2组合2');
insert into tcombi (product_id, unit_id, combi_id, default_flag, combi_code, combi_name) values (2, 201, 20101, 1, '20101', '产品2单元1组合1');
insert into tcombi (product_id, unit_id, combi_id, default_flag, combi_code, combi_name) values (3, 301, 30101, 1, '30101', '产品3单元1组合1');
insert into tcombi (product_id, unit_id, combi_id, default_flag, combi_code, combi_name) values (4, 401, 40101, 1, '40101', '产品4单元1组合1');
insert into tcombi (product_id, unit_id, combi_id, default_flag, combi_code, combi_name) values (5, 501, 50101, 1, '50101', '产品5单元1组合1');

insert into torder (product_id, unit_id, combi_id, report_serial_id, security_id, shareholder_id, invest_type, side_code)
    values (1, 101, 10101, 101011, 60000101, 'holder11', 1, 1);
insert into torder (product_id, unit_id, combi_id, report_serial_id, security_id, shareholder_id, invest_type, side_code)
    values (1, 101, 10101, 101012, 10000101, 'holder11', 1, 1);
insert into torder (product_id, unit_id, combi_id, report_serial_id, security_id, shareholder_id, invest_type, side_code)
    values (1, 101, 10101, 101013, 60000101, 'holder11', 1, 2);
insert into torder (product_id, unit_id, combi_id, report_serial_id, security_id, shareholder_id, invest_type, side_code)
    values (1, 101, 10101, 101014, 10000101, 'holder11', 1, 2);

insert into torder (product_id, unit_id, combi_id, report_serial_id, security_id, shareholder_id, invest_type, side_code)
    values (1, 101, 10102, 101021, 60000101, 'holder12', 1, 1);
insert into torder (product_id, unit_id, combi_id, report_serial_id, security_id, shareholder_id, invest_type, side_code)
    values (1, 101, 10102, 101022, 10000101, 'holder12', 1, 1);
insert into torder (product_id, unit_id, combi_id, report_serial_id, security_id, shareholder_id, invest_type, side_code)
    values (1, 101, 10102, 101023, 60000101, 'holder12', 1, 2);
insert into torder (product_id, unit_id, combi_id, report_serial_id, security_id, shareholder_id, invest_type, side_code)
    values (1, 101, 10102, 101024, 10000101, 'holder12', 1, 2);

insert into torder (product_id, unit_id, combi_id, report_serial_id, security_id, shareholder_id, invest_type, side_code)
    values (1, 102, 10201, 102011, 60000101, 'holder13', 1, 1);
insert into torder (product_id, unit_id, combi_id, report_serial_id, security_id, shareholder_id, invest_type, side_code)
    values (1, 102, 10201, 102012, 10000101, 'holder13', 1, 1);
insert into torder (product_id, unit_id, combi_id, report_serial_id, security_id, shareholder_id, invest_type, side_code)
    values (1, 102, 10201, 102013, 60000101, 'holder13', 1, 2);
insert into torder (product_id, unit_id, combi_id, report_serial_id, security_id, shareholder_id, invest_type, side_code)
    values (1, 102, 10201, 102014, 10000101, 'holder13', 1, 2);

insert into torder (product_id, unit_id, combi_id, report_serial_id, security_id, shareholder_id, invest_type, side_code)
    values (1, 102, 10202, 102021, 60000101, 'holder14', 1, 1);
insert into torder (product_id, unit_id, combi_id, report_serial_id, security_id, shareholder_id, invest_type, side_code)
    values (1, 102, 10202, 102022, 10000101, 'holder14', 1, 1);
insert into torder (product_id, unit_id, combi_id, report_serial_id, security_id, shareholder_id, invest_type, side_code)
    values (1, 102, 10202, 102023, 60000101, 'holder14', 1, 2);
insert into torder (product_id, unit_id, combi_id, report_serial_id, security_id, shareholder_id, invest_type, side_code)
    values (1, 102, 10202, 102024, 10000101, 'holder14', 1, 2);

insert into torder (product_id, unit_id, combi_id, report_serial_id, security_id, shareholder_id, invest_type, side_code)
    values (2, 201, 20101, 201011, 60000101, 'holder2', 1, 1);
insert into torder (product_id, unit_id, combi_id, report_serial_id, security_id, shareholder_id, invest_type, side_code)
    values (2, 201, 20101, 201012, 10000101, 'holder2', 1, 1);
insert into torder (product_id, unit_id, combi_id, report_serial_id, security_id, shareholder_id, invest_type, side_code)
    values (2, 201, 20101, 201013, 60000101, 'holder2', 1, 2);
insert into torder (product_id, unit_id, combi_id, report_serial_id, security_id, shareholder_id, invest_type, side_code)
    values (2, 201, 20101, 201014, 10000101, 'holder2', 1, 2);

insert into torder (product_id, unit_id, combi_id, report_serial_id, security_id, shareholder_id, invest_type, side_code)
    values (3, 301, 30101, 301011, 60000101, 'holder3', 1, 1);
insert into torder (product_id, unit_id, combi_id, report_serial_id, security_id, shareholder_id, invest_type, side_code)
    values (3, 301, 30101, 301012, 10000101, 'holder3', 1, 1);
insert into torder (product_id, unit_id, combi_id, report_serial_id, security_id, shareholder_id, invest_type, side_code)
    values (3, 301, 30101, 301013, 60000101, 'holder3', 1, 2);
insert into torder (product_id, unit_id, combi_id, report_serial_id, security_id, shareholder_id, invest_type, side_code)
    values (3, 301, 30101, 301014, 10000101, 'holder3', 1, 2);

insert into torder (product_id, unit_id, combi_id, report_serial_id, security_id, shareholder_id, invest_type, side_code)
    values (4, 401, 40101, 401011, 60000101, 'holder4', 1, 1);
insert into torder (product_id, unit_id, combi_id, report_serial_id, security_id, shareholder_id, invest_type, side_code)
    values (4, 401, 40101, 401012, 10000101, 'holder4', 1, 1);
insert into torder (product_id, unit_id, combi_id, report_serial_id, security_id, shareholder_id, invest_type, side_code)
    values (4, 401, 40101, 401013, 60000101, 'holder4', 1, 2);
insert into torder (product_id, unit_id, combi_id, report_serial_id, security_id, shareholder_id, invest_type, side_code)
    values (4, 401, 40101, 401014, 10000101, 'holder4', 1, 2);

insert into torder (product_id, unit_id, combi_id, report_serial_id, security_id, shareholder_id, invest_type, side_code)
    values (5, 501, 50101, 501011, 60000101, 'holder5', 1, 1);
insert into torder (product_id, unit_id, combi_id, report_serial_id, security_id, shareholder_id, invest_type, side_code)
    values (5, 501, 50101, 501012, 10000101, 'holder5', 1, 1);
insert into torder (product_id, unit_id, combi_id, report_serial_id, security_id, shareholder_id, invest_type, side_code)
    values (5, 501, 50101, 501013, 60000101, 'holder5', 1, 2);
insert into torder (product_id, unit_id, combi_id, report_serial_id, security_id, shareholder_id, invest_type, side_code)
    values (5, 501, 50101, 501014, 10000101, 'holder5', 1, 2);

insert into tshareholder(shareholder_id, mkt_id, shareholder_name, bind_seat, combi_id, use_product_list)
    values ('holder11', 1, 'holder11', 'seat1', 10101, ',1,');
insert into tshareholder(shareholder_id, mkt_id, shareholder_name, bind_seat, combi_id, use_product_list)
    values ('holder12', 1, 'holder12', 'seat1', 10102, ',1,');
insert into tshareholder(shareholder_id, mkt_id, shareholder_name, bind_seat, combi_id, use_product_list)
    values ('holder13', 1, 'holder13', 'seat1', 10201, ',1,');
insert into tshareholder(shareholder_id, mkt_id, shareholder_name, bind_seat, combi_id, use_product_list)
    values ('holder14', 1, 'holder14', 'seat1', 10202, ',1,');
insert into tshareholder(shareholder_id, mkt_id, shareholder_name, bind_seat, combi_id, use_product_list)
    values ('holder2', 1, 'holder2', 'seat2', 20101, ',2,');
insert into tshareholder(shareholder_id, mkt_id, shareholder_name, bind_seat, combi_id, use_product_list)
    values ('holder3', 1, 'holder3', 'seat3', 30101, ',3,');
insert into tshareholder(shareholder_id, mkt_id, shareholder_name, bind_seat, combi_id, use_product_list)
    values ('holder4', 1, 'holder4', 'seat4', 40101, ',4,');
insert into tshareholder(shareholder_id, mkt_id, shareholder_name, bind_seat, combi_id, use_product_list)
    values ('holder5', 1, 'holder5', 'seat5', 50101, ',5,');

insert into tsecurity(security_id, security_code, mkt_id, security_name, last_price, security_type, asset_type)
values (60000101, '600001', 1, '600001', 10, 1, 1);
insert into tsecurity(security_id, security_code, mkt_id, security_name, last_price, security_type, asset_type)
values (10000101, '100001', 1, '100001', 12, 61, 3);
insert into tsecurity(security_id, security_code, mkt_id, security_name, last_price, security_type, asset_type)
values (12400301, '124003', 1, '124003', 1, 201, 5);

insert into tbond(security_id, bond_interest) values (10000101, 0.1034);

insert into treporatio(security_id, repo_param_type, repo_param_value) values (12400301, 1, 3);

insert into tmarket(tradeday_type_id, mkt_id, business_date, next_business_date, pre_business_date) values (1, 1, 20201124, 20201123, 20201125);

insert into ttradedate(tradeday_type_id, sys_date, tradeday_flag, settleday_flag) values (1, 20201123, 1, 1);
insert into ttradedate(tradeday_type_id, sys_date, tradeday_flag, settleday_flag) values (1, 20201124, 1, 1);
insert into ttradedate(tradeday_type_id, sys_date, tradeday_flag, settleday_flag) values (1, 20201125, 1, 1);
insert into ttradedate(tradeday_type_id, sys_date, tradeday_flag, settleday_flag) values (1, 20201126, 1, 1);
insert into ttradedate(tradeday_type_id, sys_date, tradeday_flag, settleday_flag) values (1, 20201127, 1, 1);
insert into ttradedate(tradeday_type_id, sys_date, tradeday_flag, settleday_flag) values (1, 20201128, 0, 0);
insert into ttradedate(tradeday_type_id, sys_date, tradeday_flag, settleday_flag) values (1, 20201129, 0, 0);
insert into ttradedate(tradeday_type_id, sys_date, tradeday_flag, settleday_flag) values (1, 20201130, 1, 1);
insert into ttradedate(tradeday_type_id, sys_date, tradeday_flag, settleday_flag) values (1, 20201201, 1, 1);

