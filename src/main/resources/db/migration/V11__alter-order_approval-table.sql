alter table order_approval
    add column order_header_id uuid;

alter table order_approval
    ADD CONSTRAINT fk_approvals_orders FOREIGN KEY (order_header_id) REFERENCES order_header(id);