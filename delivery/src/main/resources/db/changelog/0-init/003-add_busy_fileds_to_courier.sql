SET search_path TO delivery, public;

ALTER TABLE couriers
    ADD COLUMN busy BOOLEAN NOT NULL DEFAULT FALSE,
    ADD COLUMN busy_until TIMESTAMP;

CREATE INDEX idx_couriers_busy ON couriers (busy);
CREATE INDEX idx_orders_create_date ON orders (create_date);

