CREATE TABLE IF NOT EXISTS users
(
    id         UUID NOT NULL,
    name       VARCHAR(255),
    email      VARCHAR(255),
    password   VARCHAR(255),
    role       VARCHAR(255),
    company_id UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_COMPANY FOREIGN KEY (company_id) REFERENCES company (id);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);