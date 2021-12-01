select p.name as person_name,
       c.name as company_name
  from person p inner join company c on p.company_id = c.id
 where p.company_id != 5
 order by p.id;
------------------------------------
select c.name as company_name, count(p.name) total_persons
  from company c inner join person p on c.id = p.company_id
 group by c.name
having count(p.name) = (select count(p) total
                          from person p
                         group by p.company_id
                         order by total desc
                         limit 1);