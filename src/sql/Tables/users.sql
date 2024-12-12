CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,               -- Unique identifier for each user (auto-incremented)
    username VARCHAR(50) UNIQUE NOT NULL,      -- Unique username for the user
    email VARCHAR(100) UNIQUE NOT NULL,        -- Unique email address for the user
    password_hash TEXT NOT NULL,               -- Hashed password for security purposes
    first_name VARCHAR(100),                   -- User's first name
    last_name VARCHAR(100),                    -- User's last name
    date_of_birth DATE,                        -- User's date of birth (optional)
    phone_number VARCHAR(20),                  -- User's phone number (optional)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Date and time when the user account was created
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Date and time when the user account was last updated
    last_login TIMESTAMP,                      -- Date and time of the user's last login (optional)
    is_active BOOLEAN DEFAULT TRUE,            -- Whether the user account is active
    is_verified BOOLEAN DEFAULT FALSE          -- Whether the user's email or account is verified
);

select * from users;