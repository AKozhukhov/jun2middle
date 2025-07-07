CREATE TYPE order_status AS ENUM ('NEW','DELIVERY','SUCCESS','ERROR');

ALTER TABLE shop.orders
ADD COLUMN status order_status NOT NULL,
ADD COLUMN description VARCHAR(255);

UPDATE shop.orders
SET status = 'SUCCESS';