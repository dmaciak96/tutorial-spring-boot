create table if not exists student
(
    id         SERIAL PRIMARY KEY,
    first_name varchar(255) default null,
    last_name  varchar(255) default null,
    email      varchar(255) default null
)