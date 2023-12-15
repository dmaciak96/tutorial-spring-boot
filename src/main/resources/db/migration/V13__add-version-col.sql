alter table order_header
    add column version int default 0;

alter table order_line
    add column version int default 0;