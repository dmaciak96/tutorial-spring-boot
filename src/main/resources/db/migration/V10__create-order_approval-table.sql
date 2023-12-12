create table if not exists order_approval
(
    id           uuid primary key default gen_random_uuid(),
    created_date timestamp not null,
    updated_date timestamp,
    approved_by  varchar(50)
);

alter table order_header
    add column order_approval_id uuid;

alter table order_header
    ADD CONSTRAINT fk_orders_approvals FOREIGN KEY (order_approval_id) REFERENCES order_approval(id);