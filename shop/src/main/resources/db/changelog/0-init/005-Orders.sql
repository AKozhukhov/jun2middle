create table if not exists shop.orders
(
    id uuid primary key,
    product_id uuid references shop.products (id) on update cascade on delete set null,
    user_id uuid references shop.users (id) on update cascade on delete set null
)