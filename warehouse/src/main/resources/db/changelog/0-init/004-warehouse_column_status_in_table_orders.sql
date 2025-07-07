CREATE TYPE status_enum AS ENUM ('NEW', 'DELIVERY', 'SUCCESS', 'ERROR');

ALTER TABLE warehouse.orders
ADD COLUMN status status_enum;

UPDATE warehouse.orders
SET status = 'SUCCESS';