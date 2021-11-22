create table departments
(
    id   serial primary key,
    name varchar(255)
);

create table emploers
(
    id            serial primary key,
    name          varchar(255),
    department_id int,
    constraint fk_deps foreign key (department_id) references departments (id)
);

insert into departments(name) values('IT');
insert into departments(name) values('HR');
insert into departments(name) values('Manager');
insert into departments(name) values('Worker');

insert into emploers(name, department_id) values('Dean', 1);
insert into emploers(name, department_id) values('Jim',2);
insert into emploers(name, department_id) values('Andy', 4);
insert into emploers(name) values('Mike');
insert into emploers(name, department_id) values('Elias', 3);
insert into emploers(name) values('Sam');

create table teens
(
    id     serial primary key,
    name   varchar(255),
    gender varchar(255)
);

insert into teens(name, gender) values('Alex', 'Man');
insert into teens(name, gender) values('Sandy', 'Woman');
insert into teens(name, gender) values('J-hope', 'Man');
insert into teens(name, gender) values('Jimin', 'Man');
insert into teens(name, gender) values('Lane', 'Woman');
insert into teens(name, gender) values('Jessica', 'Woman');