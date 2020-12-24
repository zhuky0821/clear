drop table if exists tcombi;

create table tcombi(
	product_id int,
	unit_id int,
	combi_id int,
	default_flag int,
	combi_code varchar(32),
	combi_name varchar(64),
	constraint tcombi_0 primary key(combi_id)
);