# 用户表
set character_set_client = 'utf8';
drop table if exists t_user;
create table t_user(
    id int primary key,
    username varchar(255) not null,
    password varchar(255) not null
);
insert into t_user (id, username, password)
values (01, 'admin', '123');
insert into t_user(id, username, password)
values (02, 'tom', '123321');
commit;
select * from t_user;