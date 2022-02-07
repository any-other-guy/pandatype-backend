use pandatype_db;
create table user_entities_table
(
    user_id  bigint auto_increment,
    email    varchar(60) not null unique,
    username varchar(18) not null unique,
    password varchar(60) not null,
    constraint user_entities_pk
        primary key (user_id)
);