CREATE SEQUENCE IF NOT EXISTS shop.test_test_id_seq;
create table if not exists shop.test
(

    test_id   bigint primary key default nextval('shop.test_test_id_seq'),
    var1 text,
    var2 uuid
)