create table
    greetings (
        id bigserial primary key,
        uui uuid default uuid_generated_v4 (),
        username varchar not null unique
    );

insert into
    greetings (uui, username)
values
    ('86c790b1-19be-41c1-bf38-d22c6fe2ced1', 'Vizier'),
    (
        '103fc1ea-ab5f-476e-88fa-5bfaeb11c06a',
        'Ivorjour'
    );