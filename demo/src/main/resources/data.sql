

insert into course(id, name, create_date, last_updated_date) values(10001, 'JPA in 50 steps', sysdate(), sysdate());
insert into course(id, name, create_date, last_updated_date) values(10002, 'microservice in 20 steps', sysdate(), sysdate());
insert into course(id, name, create_date, last_updated_date) values(10003, 'Spring in 10 steps', sysdate(), sysdate());


insert into passport(id, number)  values(40001, 'E123456');
insert into passport(id, number)  values(40002, 'N123456');
insert into passport(id, number)  values(40003, 'L123456');


insert into student(id, name, passport_id)  values(20001, 'Manish', 40001);
insert into student(id, name, passport_id)  values(20002, 'Shikha', 40002);
insert into student(id, name, passport_id)  values(20003, 'Ishaani',40003);




insert into review(id, rating, description)  values(50001, '5', 'Great Course');
insert into review(id, rating, description)  values(50002, '4', 'Awesome Course');
insert into review(id, rating, description)  values(50003, '5', 'Wonderful Course');