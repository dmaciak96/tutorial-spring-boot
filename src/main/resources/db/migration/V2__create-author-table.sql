create table if not exists author
(
    id        serial PRIMARY KEY,
    first_name     varchar(255) not null,
    last_name       varchar(255) not null
)