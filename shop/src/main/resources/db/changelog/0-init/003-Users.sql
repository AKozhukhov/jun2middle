create table if not exists shop.users
(
    id uuid primary key,
    fio varchar(255) unique,
    email varchar(255),
    location_x int,
    location_y int
)