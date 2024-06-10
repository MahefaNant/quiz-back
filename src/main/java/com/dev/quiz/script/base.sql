-- create database quiz

create table admin(
    id serial primary key ,
    mail varchar(50) unique ,
    password varchar(100)
);

create table candidate(
    id serial primary key ,
    mail varchar(50) unique ,
    name varchar(100) ,
    password varchar(100) ,
    canActive boolean default false
);

create table type(
    id serial primary key ,
    name varchar(100) ,
    description text
);

create table question(
    id serial primary key ,
    id_type int not null ,
    question text ,
    isinprogres boolean default true
);

alter table question add foreign key (id_type) references type(id);

create table response(
    id serial primary key ,
    id_question int not null ,
    response text ,
    istrue boolean default false
);

alter table response add foreign key(id_question) references question(id);

create table response_candidate(
    id serial primary key ,
    id_candidate int not null ,
    id_response int not null ,
    session_date timestamp not null default now()
);

alter table response_candidate add foreign key (id_candidate) references candidate(id);
alter table response_candidate add foreign key (id_response) references response(id);