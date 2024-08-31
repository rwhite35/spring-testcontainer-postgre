create table
    names (
        id bigserial primary key,
        uuid varchar(36) not null unique,
        username varchar not null unique
    );

insert into
    names (uuid, username)
values
    (
        '86c790b1-19be-41c1-bf38-d22c6fe2ced1',
        'Grand Vizier'
    ),
    (
        '103fc1ea-ab5f-476e-88fa-5bfaeb11c06a',
        'Ivorjour'
    );