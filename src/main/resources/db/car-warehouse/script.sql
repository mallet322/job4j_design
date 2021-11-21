select c.name as car_name,
       b.name as body_name,
       e.name as engine_name,
       t.name as transmission_name
  from car c
  join body b on c.body_id = b.id
  join engine e on c.engine_id = e.id
  join transmission t ON c.transmission_id = t.id;
--
select c.name from body b right join car c on b.id = c.body_id where c.body_id is null;
select c.name from engine e right join car c on e.id = c.engine_id where c.engine_id is null;
select c.name from transmission t right join car c on t.id = c.transmission_id where c.transmission_id is null;