create table if not exists product
(
    id             uuid primary key default gen_random_uuid(),
    created_date   timestamp    not null,
    updated_date   timestamp,
    description    varchar(255) not null,
    product_status varchar(30)  not null
);