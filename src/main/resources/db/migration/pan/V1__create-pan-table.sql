create table if not exists credit_card_pan
(
    id                 serial primary key,
    credit_card_number varchar(255) not null
);