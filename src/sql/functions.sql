CREATE FUNCTION adduser(in_user appuser) RETURNS void AS
$$
DECLARE

BEGIN
    -- checks here -> if the username or email already exists
	IF EXISTS (SELECT 1 FROM users WHERE username = in_user.username) THEN
        RAISE EXCEPTION 'Username % already exists', in_user.username;
    END IF;

	IF EXISTS (SELECT 1 FROM users WHERE email = in_user.email) THEN
        RAISE EXCEPTION 'Email % already exists', in_user.email;
    END IF;

	-- Insert the new user into the 'users' table
	INSERT INTO users (username, email, password_hash, first_name, last_name, date_of_birth, 
						phone_number, created_at, updated_at, is_active, is_verified) 
	VALUES (
	in_user.username, in_user.email, in_user.password_hash, in_user.first_name, in_user.last_name, 
	in_user.date_of_birth, in_user.phone_number, in_user.created_at, in_user.updated_at, in_user.is_active, in_user.is_verified
	);


END
$$ LANGUAGE plpgsql;

