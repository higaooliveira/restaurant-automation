CREATE TABLE IF NOT EXISTS company
(
    id           UUID NOT NULL,
    social_name  VARCHAR(255),
    fantasy_name VARCHAR(255),
    phone        VARCHAR(255),
    document     VARCHAR(255),
    created_at   TIMESTAMP WITHOUT TIME ZONE,
    updated_at   TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_company PRIMARY KEY (id)
);

ALTER TABLE company
    ADD CONSTRAINT uc_company_document UNIQUE (document);