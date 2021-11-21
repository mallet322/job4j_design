create table body
(
    id   serial primary key,
    name varchar(255)
);

create table engine
(
    id   serial primary key,
    name varchar(255)
);

create table transmission
(
    id   serial primary key,
    name varchar(255)
);

create table car
(
    id              serial primary key,
    name            varchar(255),
    body_id         int,
    engine_id       int,
    transmission_id int,
    constraint fk_body_car foreign key (body_id) references body (id),
    constraint fk_engine_car foreign key (engine_id) references engine (id),
    constraint fk_transmission_car foreign key (transmission_id) references transmission (id)
);
--
insert into body(name) values ('sedan');
insert into body(name) values ('hatchback');
insert into body(name) values ('crossover');

insert into engine(name) values ('v6');
insert into engine(name) values ('v8');
insert into engine(name) values ('v12');

insert into transmission(name) values ('Manual transmission');
insert into transmission(name) values ('Manual transmission');
insert into transmission(name) values ('Robotic transmission');

insert into car(name, body_id, engine_id, transmission_id) values ('Lada', 1, 1, 1);
insert into car(name, body_id, engine_id, transmission_id) values ('Volkswagen', 1, 2, 1);
insert into car(name, body_id, engine_id, transmission_id) values ('Volkswagen', 1, 3, 3);
insert into car(name, body_id, engine_id, transmission_id) values ('Toyota', 1, 1, 1);
insert into car(name, body_id, engine_id, transmission_id) values ('Toyota', 1, 3, 3);
insert into car(name, body_id, engine_id, transmission_id) values ('Toyota', 1, 2, 3);
insert into car(name, body_id, engine_id, transmission_id) values ('Toyota', 1, 1, 3);
insert into car(name) values ('BMW');
insert into car(name) values ('Mercedes');
insert into car(name) values ('Range rover');