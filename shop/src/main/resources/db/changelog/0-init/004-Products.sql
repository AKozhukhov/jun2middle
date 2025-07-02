CREATE TABLE IF NOT EXISTS shop.products
(
    id UUID PRIMARY KEY,
    warehouse_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    size INT NOT NULL CHECK (size >= 1 AND size <= 3)
)