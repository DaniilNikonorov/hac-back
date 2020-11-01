create table "user"
(
    id          varchar(50) primary key not null,
    login       varchar                 not null,
    password    varchar                 not null,
    role        varchar(20)             not null,
    first_name  varchar(50)             NOT NULL,
    second_name varchar(50)             NOT NULL,
    middle_name varchar(50),
    email varchar,
    phone_number varchar,
    edu varchar,
    birthday varchar
);
CREATE TABLE TEACHER
(
    id      varchar(50) PRIMARY KEY,
    user_id varchar(50) references "user" on delete cascade not null
);


CREATE TABLE task
(
    id          varchar(50) PRIMARY KEY,
    teacher_id  varchar(50) references teacher on delete cascade not null,
    name        varchar(50)                                      NOT NULL,
    description varchar                                          NOT NULL,
    legend      varchar                                          not null
);

create table test_task
(
    id      varchar(50) primary KEY,
    task_id varchar(50) references task on delete cascade not null,
    output  varchar                                       not null,
    input   varchar                                       not null
);

CREATE TABLE class
(
    id   varchar(50) PRIMARY KEY,
    name varchar(50) not null
);

create table schoolboy
(
    id       varchar(50) PRIMARY KEY,
    class_id varchar(50) references class                    not null,
    user_id  varchar(50) references "user" on delete cascade not null
);


create table task_class_cross
(
    task_id  varchar(50) references task on delete cascade   not null,
    class_id varchar(50) references class on delete cascade  not null
);


create table tries
(
    id           varchar(50) PRIMARY KEY,
    task_id      varchar(50) references task on delete cascade      not null,
    schoolboy_id varchar(50) references schoolboy on delete cascade not null,
    try_number   varchar(50)                                        not null,
    result       integer                                            not null,
    code         varchar                                            not null,
    output       varchar,
    input        varchar
);

insert into public."user"
values ('1', 'schoolboy', 'schoolboy', 'SCHOOLBOY', 'Даня', 'Никоноров', 'Владиславыч');

insert into public."user"
values ('2', 'teacher', 'teacher', 'TEACHER', 'Леха', 'Алексеев', 'Николаич');

insert into public."user"
values ('3', 'admin', 'admin', 'ADMIN', 'Васек', 'Лиманский');

insert into public.class
values ('1', '9А');

insert into public.schoolboy
values ('1', '1', '1');

insert into public.teacher
values ('1', '2');

insert into public.task
values ('1', '1', 'Тест', 'Тестим', 'Тестим');

insert into public.test_task
values ('1', '1', 'Тестовый инпут', 'Тестовый аутпут');

insert into public.task_class_cross
values ('1', '1');

insert into public.tries
values ('2', '1', '1', '1', '25', 'test', 'test', 'test');

insert into public.tries
values ('3', '1', '1', '1', '35', 'test', 'test', 'test');

insert into public.tries
values ('4', '1', '1', '1', '15', 'test', 'test', 'test');

insert into public.tries
values ('5', '1', '1', '1', '87', 'test', 'test', 'test');