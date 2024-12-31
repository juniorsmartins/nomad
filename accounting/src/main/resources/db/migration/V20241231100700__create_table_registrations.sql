CREATE TABLE IF NOT EXISTS registrations(
    registration_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    cashbook_id UUID NOT NULL,
    description TEXT NOT NULL CHECK (TRIM(description) <> ''),
    amount DECIMAL(19, 2) NOT NULL,
    type_operation VARCHAR(25) NOT NULL CHECK (TRIM(type_operation) <> ''),
    date_operation DATE NOT NULL,
    cost_center VARCHAR(25) NOT NULL CHECK (TRIM(cost_center) <> ''),
    supplier VARCHAR(100) NOT NULL CHECK (TRIM(supplier) <> ''),
    FOREIGN KEY (cashbook_id) REFERENCES cashbooks(cashbook_id) ON DELETE CASCADE
)

