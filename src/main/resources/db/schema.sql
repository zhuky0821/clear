
-- 基础表
drop table if exists  tproduct;

create table tproduct (
	product_id int,
	product_code varchar(32),
	product_name varchar(64),
	product_share decimal,
	constraint tproduct_0 primary key (product_id)
);


drop table if exists  tunit;

create table tunit (
	product_id int,
	unit_id int,
	unit_code varchar(32),
	unit_name varchar(64),
	default_flag int,
	constraint tunit_0 primary key(unit_id)
);

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

drop table if exists tsecurity;

create table tsecurity(
	security_id int,
	security_code varchar(32),
	mkt_id int,
	security_name varchar(64),
	last_proce decimal,
	security_type int,
	asset_type int,
	constraint tsecurity_0 primary key(security_id)
);

create unique index tsecurity_1 on tsecurity (security_code, mkt_id);

drop table if exists tbond;
create table tbond(
	security_id int,
	bond_interest decimal,
	constraint tbond_0 primary key(security_id)
);

drop table if exists treporatio;
create table treporatio(
	security_id int,
	repo_param_type int,
	repo_param_value int,
	constraint treporatio_0 primary key(security_id)
);

drop table if exists tshareholder;
create table tshareholder(
	shareholder_id varchar(64),
	mkt_id int,
	shareholder_name varchar(64),
	bind_seat varchar(32),
	constraint tshareholder_0 primary key(shareholder_id, mkt_id)
);


-- 业务表

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

drop table if exists ttmpcurrents;
create table ttmpcurrents(
    pos_str varchar(32),
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
    receivable_interest_bal decimal,
    hg_interest decimal,
    trade_date int,
    legal_buyback_date int,
    actual_buyback_date int,
    constraint ttmpcurrents_0 primary key (pos_str)
);

create index ttmpcurrents_1 on ttmpcurrents(product_id);

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

create index tjournal_1 on ttmpcurrents(product_id);

drop table if exists tjsmx;
create table tjsmx
(
  scdm varchar2(2) default ' ',
  jllx varchar2(3) default ' ',
  jyfs varchar2(3) default ' ',
  jsfs varchar2(3) default ' ',
  ywlx varchar2(3) default ' ',
  qsbz varchar2(3) default ' ',
  ghlx varchar2(3) default ' ',
  jsbh varchar2(16) default ' ',
  cjbh varchar2(32) default ' ',
  sqbh varchar2(32) default ' ',
  wtbh varchar2(16) default ' ',
  jyrq int default 0 not null,
  qsrq int default 0 not null,
  jsrq int default 0 not null,
  qtrq int default 0 not null,
  wtsj varchar2(6) default ' ',
  cjsj varchar2(10) default ' ',
  xwh1 varchar2(6) default ' ',
  xwh2 varchar2(6) default ' ',
  xwhy varchar2(8) default ' ',
  jshy varchar2(8) default ' ',
  tghy varchar2(8) default ' ',
  zqzh varchar2(32) default ' ',
  zqdm1 varchar2(8) default ' ',
  zqdm2 varchar2(8) default ' ',
  zqlb varchar2(2) default ' ',
  ltlx varchar2(2) default ' ',
  qylb varchar2(2) default ' ',
  gpnf varchar2(4) default ' ',
  mmbz varchar2(2) default ' ',
  sl decimal default 0 not null,
  cjsl decimal default 0 not null,
  zjzh varchar2(32) default ' ',
  bz varchar2(3) default ' ',
  jg1 decimal default 0 not null,
  jg2 decimal default 0 not null,
  qsje decimal default 0 not null,
  yhs decimal default 0 not null,
  jsf decimal default 0 not null,
  ghf decimal default 0 not null,
  zgf decimal default 0 not null,
  sxf decimal default 0 not null,
  qtje1 decimal default 0 not null,
  qtje2 decimal default 0 not null,
  qtje3 decimal default 0 not null,
  sjsf decimal default 0 not null,
  jgdm decimal default ' ',
  fjsm varchar2(128) default ' ',
  record_id int default 0 not null,
  constraint tjsmx_0 primary key (record_id)
);

create index tjsmx_1 on tjsmx(zqzh);

