# seata 本身需要的表结构
CREATE DATABASE IF NOT EXISTS seata;

use seata;

create table branch_table
(
    branch_id         bigint        not null
        primary key,
    xid               varchar(128)  not null,
    transaction_id    bigint        null,
    resource_group_id varchar(32)   null,
    resource_id       varchar(256)  null,
    branch_type       varchar(8)    null,
    status            tinyint       null,
    client_id         varchar(64)   null,
    application_data  varchar(2000) null,
    gmt_create        datetime(6)   null,
    gmt_modified      datetime(6)   null
);

create index idx_xid
    on branch_table (xid);

create table distributed_lock
(
    lock_key   char(20)    not null
        primary key,
    lock_value varchar(20) not null,
    expire     bigint      null
);

INSERT INTO distributed_lock (lock_key, lock_value, expire)
VALUES ('AsyncCommitting', ' ', 0);
INSERT INTO distributed_lock (lock_key, lock_value, expire)
VALUES ('RetryCommitting', ' ', 0);
INSERT INTO distributed_lock (lock_key, lock_value, expire)
VALUES ('RetryRollbacking', ' ', 0);
INSERT INTO distributed_lock (lock_key, lock_value, expire)
VALUES ('TxTimeoutCheck', ' ', 0);


create table global_table
(
    xid                       varchar(128)  not null
        primary key,
    transaction_id            bigint        null,
    status                    tinyint       not null,
    application_id            varchar(32)   null,
    transaction_service_group varchar(32)   null,
    transaction_name          varchar(128)  null,
    timeout                   int           null,
    begin_time                bigint        null,
    application_data          varchar(2000) null,
    gmt_create                datetime      null,
    gmt_modified              datetime      null
);

create index idx_status_gmt_modified
    on global_table (status, gmt_modified);

create index idx_transaction_id
    on global_table (transaction_id);


create table lock_table
(
    row_key        varchar(128)      not null
        primary key,
    xid            varchar(128)      null,
    transaction_id bigint            null,
    branch_id      bigint            not null,
    resource_id    varchar(256)      null,
    table_name     varchar(32)       null,
    pk             varchar(36)       null,
    status         tinyint default 0 not null comment '0:locked ,1:rollbacking',
    gmt_create     datetime          null,
    gmt_modified   datetime          null
);

create index idx_branch_id
    on lock_table (branch_id);

create index idx_status
    on lock_table (status);

create index idx_xid
    on lock_table (xid);


