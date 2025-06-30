CREATE TABLE IF NOT EXISTS warehouse.products
(
    id       UUID PRIMARY KEY,
    name     VARCHAR(255) NOT NULL UNIQUE,
    size     INT NOT NULL,
    count    INT NOT NULL
);