CREATE TABLE IF NOT EXISTS shop.users
(
    id UUID PRIMARY KEY,
    fio VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255),
    location_x INT NOT NULL,
    location_y INT NOT NULL
)