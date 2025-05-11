ALTER TABLE users
ADD COLUMN password VARCHAR(255) NOT NULL;

UPDATE users
SET password = 'password';
