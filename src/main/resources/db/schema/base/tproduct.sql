drop table if exists  tproduct;

create table tproduct (
	product_id int,
	product_code varchar(32),
	product_name varchar(64),
	product_share decimal,
	constraint tproduct_0 primary key (product_id)
) WITH "ATOMICITY = TRANSACTIONAL_SNAPSHOT";