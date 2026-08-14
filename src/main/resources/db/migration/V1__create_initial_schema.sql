CREATE TABLE app_metadata (
    id UUID PRIMARY KEY,
    key VARCHAR(255) NOT NULL UNIQUE,
    value VARCHAR(255),
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL
);