drop table if exists  tcombistock;

create table tcombistock (
	product_id int,
	unit_id int,
	combi_id int,
	security_id int,
	invest_type int,
	position_type int,
	bind_seat varchar(32),
	shareholder_id varchar(32),
	begin_qty decimal,
	current_qty decimal,
	begin_cost decimal,
	current_cost decimal,
	begin_profit decimal,
	current_profit decimal,
	begin_interest_cost decimal,
	begin_interest_profit decimal,
	current_interest_cost decimal,
	current_interest_profit decimal,
	constraint tcombistock_0 primary key (product_id)
);

create index tcombistock_1 on tcombistock(product_id);