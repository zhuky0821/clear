drop table if exists tsecurity;

create table tsecurity(
	security_id int,
	security_code varchar(32),
	mkt_id int,
	security_name varchar(64),
	last_price decimal,
	security_type int,
	asset_type int,
	constraint tsecurity_0 primary key(security_id)
);

create index if not exists tsecurity_1 on tsecurity (security_code, mkt_id);