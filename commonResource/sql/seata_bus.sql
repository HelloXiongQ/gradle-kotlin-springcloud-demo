# seata 测试 account 数据库
CREATE DATABASE IF NOT EXISTS seata_account;

USE seata_account;

# 日志回滚表（seata AT 模式的回滚提交依据）
CREATE TABLE IF NOT EXISTS undo_log
(
    branch_id     bigint       NOT NULL COMMENT 'branch transaction id',
    xid           varchar(128) NOT NULL COMMENT 'global transaction id',
    context       varchar(128) NOT NULL COMMENT 'undo_log context,such as serialization',
    rollback_info longblob     NOT NULL COMMENT 'rollback info',
    log_status    int          NOT NULL COMMENT '0:normal status,1:defense status',
    log_created   datetime(6)  NOT NULL COMMENT 'create datetime',
    log_modified  datetime(6)  NOT NULL COMMENT 'modify datetime',
    CONSTRAINT ux_undo_log
        UNIQUE (xid, branch_id)
)
    COMMENT 'AT transaction mode undo table';

CREATE INDEX ix_log_created
    ON undo_log (log_created);

# account 业务
CREATE TABLE IF NOT EXISTS t_account
(
    id      bigint auto_increment
        primary key,
    user_id bigint      null comment '用户ID',
    total   decimal(11) null comment '总额度',
    used    decimal(11) null comment '已用账户余额',
    residue decimal(11) null comment '余额'
);

INSERT INTO t_account (id, user_id, total, used, residue) VALUES (1, 1, 1000, 20, 980);

###############################################################

#seata测试 order业务
CREATE DATABASE IF NOT EXISTS seata_order;

USE seata_order;

# 日志回滚表（seata AT 模式的回滚提交依据）
CREATE TABLE IF NOT EXISTS undo_log
(
    branch_id     bigint       NOT NULL COMMENT 'branch transaction id',
    xid           varchar(128) NOT NULL COMMENT 'global transaction id',
    context       varchar(128) NOT NULL COMMENT 'undo_log context,such as serialization',
    rollback_info longblob     NOT NULL COMMENT 'rollback info',
    log_status    int          NOT NULL COMMENT '0:normal status,1:defense status',
    log_created   datetime(6)  NOT NULL COMMENT 'create datetime',
    log_modified  datetime(6)  NOT NULL COMMENT 'modify datetime',
    CONSTRAINT ux_undo_log
        UNIQUE (xid, branch_id)
)
    COMMENT 'AT transaction mode undo table';

CREATE INDEX ix_log_created
    ON undo_log (log_created);

# order 业务表

CREATE TABLE IF NOT EXISTS t_order
(
    id         BIGINT AUTO_INCREMENT
        PRIMARY KEY,
    user_id    BIGINT      NULL COMMENT '用户ID',
    product_id BIGINT      NULL COMMENT '产品ID',
    count      INT         NULL COMMENT '数量',
    money      DECIMAL(11) NULL COMMENT '金额',
    status     INT         NULL COMMENT '订单状态: 0:创建中, 1:已完结'
);

INSERT INTO t_order (id, user_id, product_id, count, money, status) VALUES (17, 1, 1, 1, 1, 1);
INSERT INTO t_order (id, user_id, product_id, count, money, status) VALUES (23, 1, 1, 1, 10, 1);

###############################################################

#seata测试 storage 业务
CREATE DATABASE IF NOT EXISTS seata_storage;

USE seata_storage;

# 日志回滚表（seata AT 模式的回滚提交依据）
CREATE TABLE IF NOT EXISTS undo_log
(
    branch_id     bigint       NOT NULL COMMENT 'branch transaction id',
    xid           varchar(128) NOT NULL COMMENT 'global transaction id',
    context       varchar(128) NOT NULL COMMENT 'undo_log context,such as serialization',
    rollback_info longblob     NOT NULL COMMENT 'rollback info',
    log_status    int          NOT NULL COMMENT '0:normal status,1:defense status',
    log_created   datetime(6)  NOT NULL COMMENT 'create datetime',
    log_modified  datetime(6)  NOT NULL COMMENT 'modify datetime',
    CONSTRAINT ux_undo_log
        UNIQUE (xid, branch_id)
)
    COMMENT 'AT transaction mode undo table';

CREATE INDEX ix_log_created
    ON undo_log (log_created);

# storage 业务表
CREATE TABLE IF NOT EXISTS t_storage
(
    id         BIGINT AUTO_INCREMENT
        PRIMARY KEY,
    product_id BIGINT      NULL COMMENT '产品ID',
    total      DECIMAL(11) NULL COMMENT '总库存',
    used       DECIMAL(11) NULL COMMENT '已用库存',
    residue    DECIMAL(11) NULL COMMENT '剩余库存'
);

INSERT INTO t_storage (id, product_id, total, used, residue) VALUES (1, 1, 100, 2, 98);
