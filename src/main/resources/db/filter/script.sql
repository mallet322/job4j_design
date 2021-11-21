select p.*
  from product p
  join type t on p.type_id = t.id
 where t.name = 'СЫР';
--
select p.*
  from product p
 where p."name" like '%мороженое%';
--
select p.*
  from product p
 where p.expired_date < now();
--
select p.*
  from product p
 where p.price = (select max(p2.price) as max_price
                    from product p2);
--
select t.name,
       p.*
  from product p
  join type t on p.type_id = t.id
  where t.name in ('СЫР', 'МОЛОКО')
  order by p.type_id, p.id;
--
select t.name,
       count(p.type_id)
  from type t
  join product p on t.id = p.type_id
 group by p.type_id, t.name
having count(p.type_id) < 10;
--
select p.name as product_name,
       t.name as type_name,
       p.expired_date,
       p.price
  from product p
  join type t on p.type_id = t.id
 order by p.type_id, p.id;

