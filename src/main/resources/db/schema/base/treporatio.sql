drop table if exists treporatio;
create table treporatio(
	security_id int,
	repo_param_type int,
	repo_param_value int,
	constraint treporatio_0 primary key(security_id)
);