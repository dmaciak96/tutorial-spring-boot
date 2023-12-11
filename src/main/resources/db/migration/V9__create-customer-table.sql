create table if not exists customer
(
    id            uuid primary key default gen_random_uuid(),
    created_date  timestamp not null,
    updated_date  timestamp,
    customer_name varchar(50),
    address       varchar(30),
    city          varchar(30),
    state         varchar(30),
    zip_code      varchar(30),
    phone         varchar(30),
    email         varchar(255)
);

alter table order_header
    add column customer_id uuid;

alter table order_header
    ADD CONSTRAINT fk_orders_customers FOREIGN KEY (customer_id) REFERENCES customer (id);

alter table order_header
    drop column customer_name;

