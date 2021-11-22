select c.name as car_name,
       b.name as body_name,
       e.name as engine_name,
       t.name as transmission_name
  from car c
  join body b on c.body_id = b.id
  join engine e on c.engine_id = e.id
  join transmission t ON c.transmission_id = t.id;
--
select b.name from car c right join body b on c.body_id = b.id where c.body_id is null;
select e.name from car c right join engine e on c.engine_id = e.id where c.engine_id is null;
select t.name from car c right join transmission t on c.transmission_id = t.id  where c.transmission_id is null;