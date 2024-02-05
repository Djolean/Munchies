ALTER TABLE password_reset_token
    ADD COLUMN admin_id VARCHAR (255);

ALTER TABLE password_reset_token
    ADD CONSTRAINT fk_password_reset_token_admin
        FOREIGN KEY (admin_id)
            REFERENCES admin(admin_id);
