CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

INSERT INTO admin (admin_id, admin_name, admin_email, password, created_date, last_modified_date)
VALUES
    (uuid_generate_v4(), 'Admin2', 'random@gmail.com', '$2a$12$tKr.TrThP0oDpbpnZvdmQOjgvLGV9B.ufh1F8H/b5HNtIfQ4I21Uu', NOW(), NOW()),
    (uuid_generate_v4(), 'Admin3', 'djoleing@gmail.com', '$2a$12$Pgov.63JFbYUgyxSzKA.He8ctpLLJQG1f2RUFA8amsNkJHyMgTkNy', NOW(), NOW()),
    (uuid_generate_v4(), 'Admin4', 'testmail@gmail.com', '$2a$12$/mSWL8NqjjUdTkknJvPmdOLunPU.uJNo4BoG/a0RedHHf8YkEw2KS', NOW(), NOW()),
    (uuid_generate_v4(), 'Djordje', 'djoler001@gmail.com', '$2a$10$5kI9jOvXGaJF8wVVtFO5aOyTysqBLKMfyE9lCO1ylFdq8.nn9p0Jq', NOW(), NOW());
