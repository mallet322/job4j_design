select p.name as person_name,
       c.name as company_name
from person p join company c on p.company_id = c.id
where p.company_id != 5
order by p.id;
------------------------------------
with total_persons as
         (select p.company_id, count(p.company_id) as total
          from person p
                   join company c
                        on c.id = p.company_id
          group by p.company_id)

select t.company_name, t.total
from (select row_number() over(order by ts.total desc) as row_num,
             c.name as company_name,
             ts.total
      from total_persons ts join company c on c.id = ts.company_id) t
where t.row_num = 1;