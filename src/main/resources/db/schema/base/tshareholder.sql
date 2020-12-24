drop table if exists tshareholder;
create table tshareholder(
	shareholder_id varchar(64),
	mkt_id int,
	shareholder_name varchar(64),
	bind_seat varchar(32),
	combi_id int,
	use_product_list varchar(256),
	constraint tshareholder_0 primary key(shareholder_id, mkt_id)
) WITH "template=partitioned, ATOMICITY = TRANSACTIONAL_SNAPSHOT ,BACKUPS=0, CACHE_NAME=tshareholder, key_type=zhuky.clear.entity.ignite.TshareholderKey, value_type=zhuky.clear.entity.ignite.TshareholderValue";
