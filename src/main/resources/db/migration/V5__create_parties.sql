CREATE TABLE parties(
    id UUID PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    event_date TIMESTAMP WITH TIME ZONE NOT NULL,
    location VARCHAR(255) NOT NULL,
    visibility VARCHAR(20) NOT NULL,
    host_id UUID NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE,

    CONSTRAINT fk_party_host FOREIGN KEY (host_id) REFERENCES users (id)
)