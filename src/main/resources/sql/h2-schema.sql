drop table if exists operation_log;
create table operation_log
(
    id            int primary key auto_increment,
    op_ip         varchar(255) not null,
    op_type       varchar(255) not null,
    op_desc       varchar(255) not null,
    op_request    varchar(255) not null,
    op_result     varchar(255) not null,
    op_start_time datetime     not null,
    op_end_time   datetime     not null,
    user_id       varchar(255)
);
