create sequence hibernate_sequence start 1 increment 1;

create table resume
(id int8 not null, fio varchar(255) not null, position varchar(255) not null, resume_text varchar(2048) not null, salary int4, primary key (id));

create table user_role
(user_id int8 not null, roles varchar(255));

create table usr
(id int8 not null, activation_code varchar(255), active boolean not null, email varchar(255), password varchar(255) not null, username varchar(255) not null, primary key (id));

create table vacancy
(id int8 not null, company varchar(255) not null, position varchar(255) not null, vacancy_text varchar(2048) not null, primary key (id));

alter table if exists user_role add constraint user_role_fk foreign key (user_id) references usr;
