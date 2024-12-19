
\c car_portal
SET search_path to car_portal_app;
SET role car_portal_app;

create table car_account_link
(
    account_id integer not null,
    car_id     integer not null
);

--alter table car_account_link
--    owner to postgres;

INSERT INTO car_portal_app.car_account_link (account_id, car_id) VALUES (1, 1);
INSERT INTO car_portal_app.car_account_link (account_id, car_id) VALUES (1, 2);
INSERT INTO car_portal_app.car_account_link (account_id, car_id) VALUES (1, 3);
INSERT INTO car_portal_app.car_account_link (account_id, car_id) VALUES (1, 4);
