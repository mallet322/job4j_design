create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into devices(name, price) values('Phone', 10000);
insert into devices(name, price) values('PC', 30000);
insert into devices(name, price) values('TV', 20000);
insert into devices(name, price) values('Fridge', 17000);
insert into devices(name, price) values('Washer', 18000);
insert into devices(name, price) values('Аir-conditioner', 1000);
--
insert into people(name) values('Mike');
insert into people(name) values('Jim');
insert into people(name) values('Pam');
--
insert into devices_people(device_id, people_id) values(trunc(random() * 6 + 1), trunc(random() * 3 + 1));
insert into devices_people(device_id, people_id) values(trunc(random() * 6 + 1), trunc(random() * 3 + 1));
insert into devices_people(device_id, people_id) values(trunc(random() * 6 + 1), trunc(random() * 3 + 1));
insert into devices_people(device_id, people_id) values(trunc(random() * 6 + 1), trunc(random() * 3 + 1));
insert into devices_people(device_id, people_id) values(trunc(random() * 6 + 1), trunc(random() * 3 + 1));
insert into devices_people(device_id, people_id) values(trunc(random() * 6 + 1), trunc(random() * 3 + 1));
insert into devices_people(device_id, people_id) values(trunc(random() * 6 + 1), trunc(random() * 3 + 1));
insert into devices_people(device_id, people_id) values(trunc(random() * 6 + 1), trunc(random() * 3 + 1));
insert into devices_people(device_id, people_id) values(trunc(random() * 6 + 1), trunc(random() * 3 + 1));

