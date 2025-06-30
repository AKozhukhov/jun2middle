CREATE TABLE IF NOT EXISTS warehouse.products
(
    id       UUID PRIMARY KEY,
    name     VARCHAR(255) NOT NULL UNIQUE,
    size     INT NOT NULL CHECK (size >= 1 AND size <= 3),
    count    INT NOT NULL
);

CREATE INDEX idx_products_name_hash ON warehouse.products USING HASH (name);