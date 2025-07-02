create table if not exists shop.products
(
    id uuid primary key,
    warehouse_id uuid,
    name varchar(255),
    size int
)