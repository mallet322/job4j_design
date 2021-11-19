create table users
(
    id      serial primary key,
    name    varchar(255),
    role_id integer
);

create table item
(
    id          serial primary key,
    item        varchar(255),
    user_id     integer,
    state_id    integer,
    category_id integer
);

create table category
(
    id       serial primary key,
    category varchar(255)
);

create table state
(
    id    serial primary key,
    state varchar(255)
);

create table comments
(
    id      serial primary key,
    comment varchar(255),
    item_id integer
);

create table attachs
(
    id      serial primary key,
    attach  varchar(255),
    item_id integer
);

create table role
(
    id   serial primary key,
    role varchar(255)
);

create table rule
(
    id   serial primary key,
    rule varchar(255)
);

create table roles_rules
(
    id      serial primary key,
    role_id integer,
    rule_id integer
);

alter table item add foreign key (user_id) references users (id);
alter table item add foreign key (category_id) references category (id);
alter table item add foreign key (state_id) references state (id);
alter table comments add foreign key (item_id) references item (id);
alter table attachs add foreign key (item_id) references item (id);
alter table users add foreign key (role_id) references role (id);
alter table roles_rules add foreign key (role_id) references role (id);
alter table roles_rules add foreign key (rule_id) references rule (id);