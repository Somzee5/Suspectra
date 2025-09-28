-- Setup script for ThirdEye v2 database
-- Run this in SQLite Browser or any SQLite client

-- Create the login_data table if it doesn't exist
CREATE TABLE IF NOT EXISTS login_data (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    email TEXT NOT NULL,
    password TEXT NOT NULL
);

-- Insert multiple login credentials
INSERT OR REPLACE INTO login_data (email, password) VALUES ('akash29595@gmail.com', 'imakashsahu');
INSERT OR REPLACE INTO login_data (email, password) VALUES ('sohampatilsp55@gmail.com', 'test123');

-- Verify the data
SELECT * FROM login_data;
