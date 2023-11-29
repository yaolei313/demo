SET NAMES utf8mb4;

create database if not exists study;

create user 'study'@'%' identified by 'study123';

grant all privileges on study.* to 'study'@'%';

flush privileges;

use study;

create table if not exists `user` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `nickname` varchar(32) NOT NULL COMMENT '昵称',
    `email` varchar(64) NOT NULL COMMENT '邮箱',
    `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '状态',
    `create_time` bigint(20) not null comment '创建时间',
    `update_time` bigint(20) not null comment '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `unq_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;