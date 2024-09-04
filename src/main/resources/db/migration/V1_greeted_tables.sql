--
-- Table name: greeted, Schema: public, Database: test
-- version 1.0.1
--
drop table if exists greeted;

create table
    greeted (
        id bigserial primary key,
        username varchar not null unique
    );

insert into
    greeted (username)
values
    ('Vizier'),
    ('Victor');