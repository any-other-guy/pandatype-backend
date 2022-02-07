use pandatype_db;

DROP TABLE IF EXISTS user_entities_table;

create table user_entities_table
(
    userId  bigint auto_increment,
    email    varchar(60) not null unique,
    username varchar(18) not null unique,
    password varchar(60) not null,
    constraint user_entities_pk
        primary key (userId)
);

INSERT INTO user_entities_table
VALUES (1, "anyotherguy@pandatype.com", "Any-Other-Guy", "PASSWORD");