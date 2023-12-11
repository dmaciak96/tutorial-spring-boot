alter table order_line
    add column product_id uuid;

alter table order_line
    ADD CONSTRAINT fk_orders_products FOREIGN KEY (product_id) REFERENCES product(id);
