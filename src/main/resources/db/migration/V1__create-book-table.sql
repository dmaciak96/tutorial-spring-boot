create table if not exists book
(
    id        serial PRIMARY KEY,
    title     varchar(255) not null,
    isbn      varchar(255) not null,
    publisher varchar(255) not null
)