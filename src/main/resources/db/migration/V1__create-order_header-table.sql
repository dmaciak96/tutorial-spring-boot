create table if not exists order_header (
    id uuid primary key default gen_random_uuid(),
    customer_name varchar(255) not null
);