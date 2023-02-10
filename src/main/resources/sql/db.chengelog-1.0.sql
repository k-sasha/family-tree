--liquibase formatted sql

--changeset alex:1
create table if not exists family_tree (
    id bigserial primary key not null,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    gender varchar(6),
    date_of_birth data
    );

--rollback drop table family_tree;
