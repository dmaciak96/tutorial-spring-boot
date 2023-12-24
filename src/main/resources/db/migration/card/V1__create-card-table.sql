create table if not exists credit_card
(
    id              serial primary key,
    ccv             varchar(255) not null,
    expiration_date varchar(255) not null
);