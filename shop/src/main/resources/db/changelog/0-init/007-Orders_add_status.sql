ALTER TABLE shop.orders
ADD COLUMN IF NOT EXISTS status VARCHAR(255) NOT NULL,
ADD COLUMN IF NOT EXISTS description VARCHAR(255);