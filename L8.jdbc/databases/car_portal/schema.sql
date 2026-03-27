-- check if the role exists and create it if it does not
DO $$
BEGIN
    IF NOT EXISTS (SELECT FROM pg_catalog.pg_roles WHERE rolname = 'car_portal_app') THEN
        CREATE ROLE car_portal_app LOGIN;
    END IF;
END
$$;

-- drop the database if it exists
DROP DATABASE IF EXISTS car_portal;

-- adjust the locale settings based on your operating system before running this script
CREATE DATABASE car_portal
    WITH
    OWNER = car_portal_app
    ENCODING = 'UTF8'
    LC_COLLATE = 'C.UTF-8'
    LC_CTYPE = 'C.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

-- connect to the newly created database to continue setup
-- Note: The \c command is specific to PSQL utility and may not work in all SQL execution environments.
--   If running this script outside of PSQL, you should manually connect to the "car_portal" database
--   before proceeding with the following commands.
\c car_portal

-- pgmemcache package should be installed before this command;
--    sudo apt install postgresql-14-pgmemcache (if you are using postgres 14)
CREATE EXTENSION IF NOT EXISTS pgmemcache;

CREATE SCHEMA IF NOT EXISTS car_portal_app AUTHORIZATION car_portal_app;

SET search_path to car_portal_app;
SET ROLE car_portal_app;

CREATE TABLE account (
	account_id SERIAL PRIMARY KEY,
	first_name TEXT NOT NULL,
	last_name TEXT NOT NULL,
	email TEXT NOT NULL UNIQUE,
	password TEXT NOT NULL,
	CHECK(first_name !~ '\s' AND last_name !~ '\s'),
	CHECK (email ~* '^\w+@\w+[.]\w+$'),
	CHECK (char_length(password)>=8)
);

CREATE TABLE account_history (
	account_history_id BIGSERIAL PRIMARY KEY,
	account_id INT NOT NULL REFERENCES account(account_id),
	search_key TEXT NOT NULL,
	search_date DATE NOT NULL,
	UNIQUE (account_id, search_key, search_date)
);

CREATE TABLE seller_account (
	seller_account_id SERIAL PRIMARY KEY,
	account_id INT NOT NULL REFERENCES account(account_id),
	total_rank FLOAT,
	number_of_advertisement INT,
	street_name TEXT NOT NULL,
	street_number TEXT NOT NULL,
	zip_code TEXT NOT NULL,
	city TEXT NOT NULL
);

CREATE TABLE car_model
(
	car_model_id SERIAL PRIMARY KEY,
	make text,
	model text,
	UNIQUE (make, model)
);

CREATE TABLE car (
	car_id SERIAL PRIMARY KEY,
	number_of_owners INT NOT NULL,
	registration_number TEXT UNIQUE NOT NULL,
	manufacture_year INT NOT NULL,
	number_of_doors INT DEFAULT 5 NOT NULL,
	car_model_id INT NOT NULL REFERENCES car_model (car_model_id),
	mileage INT
);

CREATE TABLE advertisement(
	advertisement_id SERIAL PRIMARY KEY,
	advertisement_date TIMESTAMP WITH TIME ZONE NOT  NULL,
	car_id INT NOT NULL REFERENCES car(car_id),
	seller_account_id INT NOT NULL REFERENCES seller_account (seller_account_id)
);

CREATE TABLE advertisement_picture(
	advertisement_picture_id SERIAL PRIMARY KEY,
	advertisement_id INT REFERENCES advertisement(advertisement_id),
	picture_location TEXT UNIQUE
);

CREATE TABLE advertisement_rating (
	advertisement_rating_id SERIAL PRIMARY KEY,
	advertisement_id INT NOT NULL REFERENCES advertisement(advertisement_id),
	account_id INT NOT NULL REFERENCES account(account_id),
	advertisement_rating_date DATE NOT NULL,
	rank INT NOT NULL,
	review TEXT NOT NULL,
	CHECK (char_length(review)<= 200),
	CHECK (rank IN (1,2,3,4,5))
);

CREATE TABLE favorite_ads(
	account_id INT NOT NULL REFERENCES account(account_id),
	advertisement_id INT NOT NULL REFERENCES advertisement(advertisement_id),
	primary key(account_id,advertisement_id)
);
