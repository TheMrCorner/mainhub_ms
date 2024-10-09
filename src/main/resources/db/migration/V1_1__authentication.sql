CREATE TABLE journal.users
(
    id_user    bigserial       NOT NULL,
    username   text            NOT NULL,
    password   text            NOT NULL,
    PRIMARY KEY(id_user)
);
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE journal.users TO exe_journal;

CREATE TABLE journal.users_roles
(
    id_roles   bigserial       NOT NULL,
    name       text            NOT NULL,
    id_user    bigint          NOT NULL,
    PRIMARY KEY(id_roles),
    FOREIGN KEY(id_user) REFERENCES journal.users(id_user)
);
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE journal.users TO exe_journal;
