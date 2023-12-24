create table if not exists credit_card_holder
(
    id         serial primary key,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    zip_code   varchar(255) not null
);