create table locations
(
    id          serial primary key,
    address     varchar(255),
    postal_code varchar(255)
);
-----------
create table departments
(
    id              serial primary key,
    department_name varchar(255),
    person_id       integer,
    location_id    integer,
    constraint fk_location
        foreign key (location_id)
            references locations (id)
);
-----------
create table persons
(
    id            serial primary key,
    name          varchar(255),
    age           smallint,
    department_id integer,
    constraint fk_department
        foreign key (department_id)
            references departments (id)
);
-----------
insert into locations(address, postal_code)
values ('Russia, Moscow, ul. Baumanskaya 2-ya, 5/1', '105005');
insert into locations(address, postal_code)
values ('Russia, St. Petersburg, Kronverksky Pr. 49, bldg. A', '197101');
-----------
insert into departments(department_name, person_id, locataion_id)
values ('Manager', 1, 1);
insert into departments(department_name, person_id, locataion_id)
values ('QA Engineer', 2, 2);
insert into departments(department_name, person_id, locataion_id)
values ('Java Developer', 3, 2);
-----------
insert into persons(name, age, department_id)
values ('Michael', 40, 1);
insert into persons(name, age, department_id)
values ('Dwight', 35, 2);
insert into persons(name, age, department_id)
values ('Jim', 30, 3);
-----------
update locations
set address = 'Russia, St. Petersburg, Kronverksky Pr. 49'
where id = 2;
-----------
truncate departments cascade;
truncate locations cascade;
-----------
drop table persons;
drop table departments;
drop table locations;
-----------
create table locations
(
    id          serial primary key,
    address     varchar(255),
    postal_code varchar(255)
);
-----------
create table departments
(
    id              serial primary key,
    department_name varchar(255),
    person_id       integer,
    location_id    integer,
    constraint fk_location
        foreign key (location_id)
            references locations (id)
);
-----------
create table persons
(
    id            serial primary key,
    name          varchar(255),
    age           smallint,
    department_id integer,
    constraint fk_department
        foreign key (department_id)
            references departments (id)
);
-----------
insert into locations(address, postal_code)
values ('Russia, Moscow, ul. Baumanskaya 2-ya, 5/1', '105005');
insert into locations(address, postal_code)
values ('Russia, St. Petersburg, Kronverksky Pr. 49, bldg. A', '197101');
-----------
insert into departments(department_name, person_id, locataion_id)
values ('Manager', 1, 1);
insert into departments(department_name, person_id, locataion_id)
values ('QA Engenieer', 2, 2);
insert into departments(department_name, person_id, locataion_id)
values ('Java Developer', 3, 2);
-----------
insert into persons(name, age, department_id)
values ('Michael', 40, 1);
insert into persons(name, age, department_id)
values ('Dwight', 35, 2);
insert into persons(name, age, department_id)
values ('Jim', 30, 3);
-----------
update locations
set address = 'Russia, St. Petersburg, Kronverksky Pr. 49'
where id = 2;
-----------
truncate departments cascade;
truncate locations cascade;
-----------
drop table persons;
drop table departments;
drop table locations;