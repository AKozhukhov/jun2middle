CREATE TABLE IF NOT EXISTS warehouse.orders
(
    id                UUID PRIMARY KEY,
    shop_order_id     UUID NOT NULL,
    product_id        UUID NOT NULL
);