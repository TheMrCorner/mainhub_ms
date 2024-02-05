CREATE TABLE journal.refresh_tokens (
    id bigserial PRIMARY KEY,
    token VARCHAR(255) NOT NULL,
    expiry_date TIMESTAMP NOT NULL,
    id_user BIGINT,
    FOREIGN KEY (id_user) REFERENCES journal.users (id_user)
);
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE journal.refresh_tokens TO exe_journal;