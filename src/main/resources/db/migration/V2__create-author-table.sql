create table if not exists author
(
    id         varchar(255) PRIMARY KEY,
    first_name varchar(255) not null,
    last_name  varchar(255) not null
)