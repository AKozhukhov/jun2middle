CREATE TABLE IF NOT EXISTS warehouse.orders
(
    id                UUID PRIMARY KEY,
    shop_order_id     UUID NOT NULL UNIQUE,
    product_id        UUID NOT NULL
);

CREATE INDEX idx_orders_shop_order_id_hash ON warehouse.orders USING HASH (shop_order_id);