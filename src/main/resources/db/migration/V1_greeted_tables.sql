create table
    greeted (
        id bigserial primary key,
        username varchar not null unique
    );

insert into
    greeted (username)
values
    ('Vizier'),
    ('Ivorjour');