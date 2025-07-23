INSERT INTO products(id, name, size, count) VALUES
('11b67f58-f0f3-470f-95ff-d9348a000995', 'product1', 2, 10),
('22b67f58-f0f3-470f-95ff-d9348a000995', 'product2', 2, 12),
('33b67f58-f0f3-470f-95ff-d9348a000995', 'product3', 1, 14),
('44b67f58-f0f3-470f-95ff-d9348a000995', 'product4', 3, 16);

INSERT INTO orders(id, shop_order_id, product_id, status) VALUES
('56767f58-f0f3-470f-95ff-d9348a000888', '53367f58-f0f3-470f-95ff-d9348a000995', '11b67f58-f0f3-470f-95ff-d9348a000995', 'NEW'),
('88667f58-f0f3-470f-95ff-d9348a000777', '54467f58-f0f3-470f-95ff-d9348a000995', '44b67f58-f0f3-470f-95ff-d9348a000995', 'NEW');
