select c.* from car c join brand b on c.brand_id = b.id;
select b.* from brand b join car c on b.id = c.brand_id;
select b.brand_name, c.car_name, b.brand_country from brand b join car c on b.id = c.brand_id;
select c.* from car c where c.brand_id is null;