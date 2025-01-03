-- FUNCTION THAT RETURNS TRUE IF A GIVEN EMAIL EXISTS
SELECT exists_email('albert.doe@example.com');

CREATE OR REPLACE FUNCTION exists_email(in_email varchar) RETURNS bool AS
$$
DECLARE
	existsEmail bool := false;
BEGIN
    -- checks here -> if the username exists
	IF EXISTS (SELECT 1 FROM appusers WHERE email = in_email) THEN
        existsEmail := true;
		RETURN existsEmail;
    END IF;

	RETURN existsEmail;

END
$$ LANGUAGE plpgsql;


-- FUNCTION THAT RETURNS TRUE IF A GIVEN USERNAME EXISTS
SELECT exists_user('albert3_doe');

CREATE OR REPLACE FUNCTION exists_user(in_username varchar) RETURNS bool AS
$$
DECLARE
	existsUser bool := false;
BEGIN
    -- checks here -> if the username exists
	IF EXISTS (SELECT 1 FROM appusers WHERE username = in_username) THEN
        existsUser := true;
		RETURN existsUser;
    END IF;

	RETURN existsUser;

END
$$ LANGUAGE plpgsql;



-- FUNCTION THAT RETURNS ALL THE USERS NAMES OF THE TABLE APPUSERS

SELECT * FROM show_usernames();

CREATE OR REPLACE FUNCTION show_usernames() RETURNS SETOF varchar AS
$$
DECLARE
	cursor1 CURSOR FOR SELECT username FROM appusers;
	username_i varchar;

BEGIN
	OPEN cursor1;
	LOOP
		FETCH cursor1 INTO username_i;
		EXIT WHEN NOT FOUND;
		RETURN NEXT username_i;
	END LOOP;
	CLOSE cursor1;
	RETURN;
END;
$$ 
LANGUAGE plpgsql;



-- SHOW APPUSERS TABLE
SELECT * from appusers;


-- FUNCTION TO DELETE USERS

--Function Call
SELECT delete_user('ALBCOSRUI');

--Function definition
CREATE OR REPLACE FUNCTION delete_user(in_username varchar) RETURNS void AS
$$
DECLARE

BEGIN
    -- checks here -> if the username exists
	IF NOT EXISTS (SELECT 1 FROM appusers WHERE username = in_username) THEN
        RAISE EXCEPTION 'User with username % does not exist', in_username;
    END IF;

	-- Delete the user with the corresponding user name from Appusers table
	DELETE FROM appusers WHERE username = in_username;
	RAISE NOTICE 'User with username % has been deleted', in_username;
END
$$ LANGUAGE plpgsql;




-- FUNCTION TO ADD USERS


INSERT INTO users (appuser)
VALUES (
    ('johndoe', 'johndoe@example.com', 'hashedpassword', 'John', 'Doe', 
    '1990-01-01', '1234567890', '2024-01-01 10:00:00', '2024-01-01 10:00:00', null, true, true)
);

-- Function Call
SELECT add_user((
	'albessrt_doe', 
    'albersst.doe@example.com', 
    'hashed_password_123', 
    'John', 
    'Doe', 
    '1990-01-01', 
    '555-1234', 
    current_timestamp, 
    current_timestamp,
	current_timestamp, 
    true, 
    true
)::appuser);

--Function definition
CREATE OR REPLACE FUNCTION add_user(in_user appuser) RETURNS void AS
$$
DECLARE

BEGIN
    -- checks here -> if the username or email already exists
	IF EXISTS (SELECT 1 FROM appusers WHERE username = in_user.username) THEN
        RAISE EXCEPTION 'Username % already exists', in_user.username;
    END IF;

	IF EXISTS (SELECT 1 FROM appusers WHERE email = in_user.email) THEN
        RAISE EXCEPTION 'Email % already exists', in_user.email;
    END IF;

	-- Insert the new user into the 'appusers' table
	INSERT INTO appusers (username, email, password_hash, first_name, last_name, date_of_birth, 
						phone_number, created_at, updated_at, last_login, is_active, is_verified) 
	VALUES (
	in_user.username, in_user.email, in_user.password_hash, in_user.first_name, in_user.last_name, 
	in_user.date_of_birth, in_user.phone_number, in_user.created_at, in_user.updated_at, in_user.last_login, in_user.is_active, in_user.is_verified
	);
	RAISE NOTICE 'User with username % has been created', in_user.username;

END
$$ LANGUAGE plpgsql;

