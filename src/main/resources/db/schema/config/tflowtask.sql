drop table if exists tflowtask;
create table tflowtask(
    flow_id int,
    product_id int,
    business_date int,
    constraint tflowtask_0 primary key(flow_id, product_id)
);
