ALTER TABLE warehouse.orders
ADD COLUMN status VARCHAR(10);

UPDATE warehouse.orders
SET status = 'SUCCESS';