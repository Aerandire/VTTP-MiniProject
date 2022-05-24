CREATE table accounts (   
    acc_id bigint not null auto_increment,
    site varchar(64) not null,
    email varchar(45) not null,
    password varchar(64) not null,
    user_id bigint not null,

    primary key(acc_id),
    foreign key(user_id) references user(id)
)