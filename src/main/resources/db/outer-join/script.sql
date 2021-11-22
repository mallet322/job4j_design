select * from departments d left join emploers e ON d.id = e.department_id order by d.id;
select * from departments d right join emploers e ON d.id = e.department_id order by d.id;
select * from departments d full join emploers e ON d.id = e.department_id order by d.id;
--
select e.* from emploers e left join departments d on e.department_id = d.id where e.department_id is null;
--
select d.*, e.* from departments d left join emploers e on d.id = e.department_id order by d.id;
select d.*, e.* from emploers e right join departments d on e.department_id = d.id order by d.id;
--
select t1.name as name_1,
       t2.name as name_2,
       (t1.gender || ' + ' || t2.gender) as gender_pair
  from teens t1
 cross join teens t2
where t1.gender != t2.gender;