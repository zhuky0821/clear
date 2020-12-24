drop table if exists tjournal;
create table tjournal(
    serial_id varchar(32),
    product_id int,
    unit_id int,
    combi_id int,
    security_id int,
    busin_flag int,
    invest_type int,
    position_type int,
    shareholder_id varchar(64),
    bind_seat varchar(32),
    subject_id int,
    subject_occur_amt decimal,
    occur_amt decimal,
    occur_qty decimal,
    remark varchar(256),
    report_serial_id int,
    security_type int,
    mkt_id  int,
    deal_id varchar(64),
    deal_price decimal,
    deal_qty decimal,
    deal_amt decimal,
    yj decimal,
    yhs decimal,
    ghf decimal,
    jsf decimal,
    qtfy decimal,
    zgf decimal,
    jsfwf decimal,
    jgf decimal,
    constraint tjournal_0 primary key (serial_id)
);

create index if not exists tjournal_1 on tjournal(product_id);