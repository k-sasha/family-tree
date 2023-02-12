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

--changeset alex:2
create table if not exists relationships (
                                             parent_id bigint not null,
                                             child_id bigint not null,
                                             primary key (parent_id,  child_id),
    parent_id bigint references family_tree(id)
    child_id bigint references family_tree(id)
    );
--rollback drop table relationships;