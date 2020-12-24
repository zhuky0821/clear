drop table if exists tfilecolumnconfig;
create table tfilecolumnconfig(
    table_name varchar(32),
    column_name varchar(32),
    begin_pos int,
    end_pos int,
    constraint tfilecolumnconfig_0 primary key(table_name, column_name)
);