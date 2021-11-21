create table brand
(
    id            serial primary key,
    brand_name    varchar(255),
    brand_country varchar(255)
);

create table car
(
    id       serial primary key,
    car_name varchar(255),
    brand_id int references brand (id) unique
);

insert into brand(brand_name, brand_country) values ('LADA', 'Russia');
insert into brand(brand_name, brand_country) values ('Volkswagen', 'Germany');
insert into brand(brand_name, brand_country) values ('Nissan', 'Japan');

insert into car(car_name, brand_id) values ('Vesta', 1);
insert into car(car_name, brand_id) values ('Tiguan', 2);
insert into car(car_name, brand_id) values ('X-trail', 3);
insert into car(car_name) values ('Duster');
insert into car(car_name) values ('Camry');