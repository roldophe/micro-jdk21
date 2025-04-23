CREATE TABLE IF NOT EXISTS cards (
    card_id SERIAL PRIMARY KEY,
    mobile_number VARCHAR(15) NOT NULL UNIQUE,
    card_number VARCHAR(100) NOT NULL,
    card_type VARCHAR(100) NOT NULL,
    total_limit INT NOT NULL,
    amount_used INT NOT NULL DEFAULT 0,
    available_amount INT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(20) NOT NULL,
    updated_at TIMESTAMP DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL
);