SET search_path TO delivery, public;

CREATE TYPE transport_enum AS ENUM ('FOOT', 'MOTORCYCLE', 'CAR');

CREATE TABLE couriers (
                          id UUID PRIMARY KEY,
                          fio VARCHAR(255) NOT NULL UNIQUE,
                          transport transport_enum NOT NULL
);

CREATE TABLE orders (
                        id UUID PRIMARY KEY,
                        shop_order_id UUID NOT NULL,
                        size INTEGER NOT NULL CHECK (size BETWEEN 1 AND 3),
                        create_date TIMESTAMP NOT NULL,
                        location_x INTEGER NOT NULL,
                        location_y INTEGER NOT NULL
);