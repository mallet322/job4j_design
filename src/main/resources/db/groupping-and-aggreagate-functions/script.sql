select d.name,
       avg(d.price) as avg_price
  from devices d
group by d.name;
---
select p.name,
       count(d.name) as device_count,
       round(avg(d.price)::numeric, 1) as avg_price
  from devices_people dp
   join people p on dp.people_id = p.id
   join devices d on dp.device_id = d.id
  group by p.name
  order by avg_price asc;
---
select p.name,
       count(d.name) as device_count,
       round(avg(d.price)::numeric, 1) as avg_price
from devices_people dp
         join people p
              on dp.people_id = p.id
         join devices d
              on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000
order by avg_price asc;