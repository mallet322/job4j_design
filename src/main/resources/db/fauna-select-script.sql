select s.* from students s  where s.name like '%fish';
select f.* from fauna f where f.avg_age between 10000 and 21000 order by f.avg_age asc;
select f.* from fauna f where f.discovery_date is null;
select f.* from fauna f where EXTRACT(ISOYEAR FROM f.discovery_date) < 1950;