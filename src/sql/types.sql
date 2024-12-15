CREATE TYPE appuser AS (
	username varchar,
	email varchar,
	password_hash text,
	first_name varchar,
	last_name varchar,
	date_of_birth date,
	phone_number varchar,
	created_at timestamp without time zone,
	updated_at timestamp without time zone,
	last_login timestamp without time zone,
	is_active boolean,
	is_verified boolean
);

SELECT column_name, data_type
FROM information_schema.columns
WHERE table_name = 'users';