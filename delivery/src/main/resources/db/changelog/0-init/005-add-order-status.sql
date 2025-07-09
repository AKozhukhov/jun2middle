SET search_path TO delivery, public;

CREATE TYPE order_status_enum AS ENUM ('NEW', 'DELIVERY', 'SUCCESS', 'ERROR');

ALTER TABLE orders
    ADD COLUMN status order_status_enum NOT NULL DEFAULT 'NEW',
    ADD COLUMN arrival_time TIMESTAMP,
    ADD COLUMN courier_id UUID REFERENCES couriers(id);

UPDATE orders SET status = 'SUCCESS';

CREATE INDEX idx_orders_status ON orders (status);