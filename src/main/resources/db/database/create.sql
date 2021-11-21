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
    rule_id integer,
    constraint fk_roles_rules_role
        foreign key (role_id)
            references role (id),
    constraint fk_rule
        foreign key (rule_id)
            references rule (id)
);

create table users
(
    id      serial primary key,
    name    varchar(255),
    role_id integer,
    constraint fk_users_role
        foreign key (role_id)
            references role (id)
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

create table item
(
    id          serial primary key,
    item        varchar(255),
    user_id     integer,
    category_id integer,
    state_id    integer,
    constraint fk_category
        foreign key (category_id)
            references category (id),
    constraint fk_state
        foreign key (state_id)
            references state (id),
    constraint fk_users
        foreign key (user_id)
            references users (id)
);

create table comments
(
    id      serial primary key,
    comment varchar(255),
    item_id integer,
    constraint fk_comments_items
        foreign key (item_id)
            references item (id)
);

create table attachs
(
    id      serial primary key,
    attach  varchar(255),
    item_id integer,
    constraint fk_attachs_items
        foreign key (item_id)
            references item (id)
);