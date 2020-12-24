drop table if exists  tunit;

create table tunit (
	product_id int,
	unit_id int,
	unit_code varchar(32),
	unit_name varchar(64),
	default_flag int,
	constraint tunit_0 primary key(unit_id)
);