ALTER TABLE warehouse.orders
ADD COLUMN IF NOT EXISTS status VARCHAR(10);

UPDATE warehouse.orders
SET status = 'SUCCESS';