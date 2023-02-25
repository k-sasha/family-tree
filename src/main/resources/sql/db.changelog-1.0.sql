--liquibase formatted sql

--changeset alex:1
create table if not exists family_tree (
    id bigserial primary key not null,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    gender varchar(6),
    date_of_birth date
    );

CREATE SEQUENCE human_seq
     INCREMENT  BY  1
     MAXVALUE 1000
     START  WITH 1 ;

--  drop table family_tree;
-- drop sequence human_seq;

--changeset alex:2
create table if not exists relationships (
    parent_id bigint not null references family_tree(id),
    child_id bigint not null references family_tree(id),
    primary key (parent_id,child_id)
    );

-- drop table relationships;