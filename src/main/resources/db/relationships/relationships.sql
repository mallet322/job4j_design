--------------------
/* one to one */
--------------------
create table customer
(
    id   serial primary key,
    name varchar(255)
);

create table phone
(
    id           serial primary key,
    manufacturer varchar(255),
    model        varchar(255),
    price        smallint
);

create table customers_phones
(
    id          serial primary key,
    customer_id integer references customer (id) unique,
    phone_id    integer references phone (id) unique
);

drop table customers_phones;
drop table customer;
drop table phone;
---------------------
/* one to many */
---------------------
create table phone
(
    id           serial primary key,
    manufacturer varchar(255),
    model        varchar(255),
    price        smallint
);

create table customer
(
    id       serial primary key,
    name     varchar(255),
    phone_id integer,

    constraint fk_phone
        foreign key (phone_id)
            references phone (id)
);

drop table customer;
drop table phone;
----------------------
/* many to many */
----------------------
create table customer
(
    id   serial primary key,
    name varchar(255)
);

create table phone
(
    id           serial primary key,
    manufacturer varchar(255),
    model        varchar(255),
    price        smallint
);

create table customers_phones
(
    id          serial primary key,
    customer_id integer,
    phone_id    integer,

    constraint fk_customer
        foreign key (customer_id)
            references customer (id),

    constraint fk_phone
        foreign key (phone_id)
            references phone (id)
);

drop table customers_phones;
drop table customer;
drop table phone;