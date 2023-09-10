# 学生表
set character_set_client = 'utf8';
drop table if exists t_student;
create table t_student
(
    sno       int primary key,
    sname     varchar(255) not null,
    ssex      varchar(255) not null,
    telephone varchar(255) not null
);
insert into t_student(sno, sname, ssex, telephone)
values (001, 'tom', '男', '1111111');
insert into t_student(sno, sname, ssex, telephone)
values (002, 'jack', '男', '1112222');
insert into t_student(sno, sname, ssex, telephone)
values (003, '张三', '男', '1113333');
commit;
select *
from t_student;