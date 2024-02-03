CREATE TABLE admin_verification_token (
                                          token_id VARCHAR(255) PRIMARY KEY,
                                          admin_id VARCHAR(255) NOT NULL,
                                          token VARCHAR(255) UNIQUE NOT NULL,
                                          expiry_date TIMESTAMP NOT NULL,
                                          CONSTRAINT fk_admin_verification_token_admin_id FOREIGN KEY (admin_id) REFERENCES admin (admin_id)
);
