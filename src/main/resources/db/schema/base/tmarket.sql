drop table if exists tmarket;
create table tmarket(
	tradeday_type_id int,
	mkt_id int,
	business_date int,
	next_business_date int,
	pre_business_date int,
	constraint tmarket_0 primary key(mkt_id)
);