drop table if exists torder;
create table torder(
    product_id int,
    unit_id int,
    combi_id int,
    report_serial_id int,
    security_id int,
    shareholder_id varchar (32),
    invest_type int,
    side_code int,
    constraint torder_0 primary key(report_serial_id)
);
create index torder_1 on torder(product_id);