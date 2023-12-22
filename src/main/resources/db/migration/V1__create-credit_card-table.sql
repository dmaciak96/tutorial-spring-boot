create table if not exists credit_card
(
    id                 serial primary key,
    credit_card_number varchar(20) not null,
    ccv                varchar(20) not null,
    expiration_date    varchar(7)  not null
);