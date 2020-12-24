drop table if exists ttradedate;
create table ttradedate(
	tradeday_type_id int,
	sys_date int,
	tradeday_flag int,
	settleday_flag int,
	constraint ttradedate_0 primary key(tradeday_type_id, sys_date)
);