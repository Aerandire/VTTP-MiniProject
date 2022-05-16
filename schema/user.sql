CREATE table user (
    id bigint not null auto_increment,
    email varchar(45) not null,
    password varchar(64) not null,
    name varchar(20) not null,

    primary key(id),
    unique (email)
)