CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE roles(
    id UUID PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO roles (id, name)
VALUES
    (gen_random_uuid(), 'CUSTOMER'),
    (gen_random_uuid(), 'PROVIDER'),
    (gen_random_uuid(), 'ADMIN');