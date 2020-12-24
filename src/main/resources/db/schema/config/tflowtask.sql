drop table if exists tflowtask;
create table tflowtask(
    task_id int,
    flow_id int,
    product_id int,
    business_date int,
    next_business_date int,
    task_status int,
    remark varchar(256),
    constraint tflowtask_0 primary key(task_id)
);
