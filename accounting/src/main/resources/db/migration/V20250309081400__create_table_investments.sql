CREATE TABLE IF NOT EXISTS investments(
    investment_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    cashbook_id UUID NOT NULL,
    description VARCHAR(250) NOT NULL CHECK (TRIM(description) <> ''),
    amount DECIMAL(19, 2) NOT NULL,
    type_action VARCHAR(25) NOT NULL CHECK (TRIM(type_action) <> ''),
    date_operation DATE NOT NULL,
    category VARCHAR(25) NOT NULL CHECK (TRIM(category) <> ''),
    supplier VARCHAR(100) NOT NULL CHECK (TRIM(supplier) <> ''),
    FOREIGN KEY (cashbook_id) REFERENCES cashbooks(cashbook_id) ON DELETE CASCADE
)

