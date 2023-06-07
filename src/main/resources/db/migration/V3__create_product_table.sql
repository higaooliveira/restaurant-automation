CREATE TABLE product
(
    id          UUID         NOT NULL,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    type        VARCHAR(255) NOT NULL,
    price       BIGINT       NOT NULL,
    company_id  UUID         NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    updated_at  TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_COMPANY FOREIGN KEY (company_id) REFERENCES company (id);