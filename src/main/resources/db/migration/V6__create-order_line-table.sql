create table if not exists order_line
(
    id             uuid primary key default gen_random_uuid(),
    created_date   timestamp    not null,
    updated_date   timestamp,
    quantity_ordered int,
    order_header_id uuid,
    foreign key (order_header_id) references order_header (id)
);