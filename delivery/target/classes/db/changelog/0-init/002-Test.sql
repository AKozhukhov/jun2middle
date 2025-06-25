CREATE SEQUENCE IF NOT EXISTS delivery.test_test_id_seq;
create table if not exists delivery.test
(

    test_id   bigint primary key default nextval('delivery.test_test_id_seq'),
    var1 text,
    var2 uuid
)