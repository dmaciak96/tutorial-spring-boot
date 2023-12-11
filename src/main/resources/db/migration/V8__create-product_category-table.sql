create table if not exists category
(
    id           uuid primary key default gen_random_uuid(),
    created_date timestamp not null,
    updated_date timestamp,
    description  varchar(50)
);

create table if not exists product_category
(
    product_id uuid not null,
    category_id uuid not null,
    PRIMARY KEY(product_id, category_id),
    foreign key (product_id) references product (id),
    foreign key (category_id) references category (id)
);

insert into product (description, product_status, created_date, updated_date)
values ('PRODUCT1', 'NEW', now(), now());

insert into product (description, product_status, created_date, updated_date)
values ('PRODUCT2', 'NEW', now(), now());

insert into product (description, product_status, created_date, updated_date)
values ('PRODUCT3', 'NEW', now(), now());

insert into product (description, product_status, created_date, updated_date)
values ('PRODUCT4', 'NEW', now(), now());

insert into category (description, created_date, updated_date) VALUES
    ('CAT1', now(), now());

insert into category (description, created_date, updated_date) VALUES
    ('CAT2', now(), now());

insert into category (description, created_date, updated_date) VALUES
    ('CAT3', now(), now());

insert into product_category (product_id, category_id)
SELECT p.id, c.id FROM product p, category c
where p.description = 'PRODUCT1' and c.description = 'CAT1';

insert into product_category (product_id, category_id)
SELECT p.id, c.id FROM product p, category c
where p.description = 'PRODUCT2' and c.description = 'CAT1';

insert into product_category (product_id, category_id)
SELECT p.id, c.id FROM product p, category c
where p.description = 'PRODUCT1' and c.description = 'CAT3';

insert into product_category (product_id, category_id)
SELECT p.id, c.id FROM product p, category c
where p.description = 'PRODUCT4' and c.description = 'CAT3';