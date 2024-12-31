CREATE TABLE IF NOT EXISTS cashbooks(
    cashbook_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    year_reference INTEGER NOT NULL CHECK (year_reference > 0),
    document VARCHAR(14) NOT NULL CHECK (TRIM(document) <> ''),
    CONSTRAINT uk_cashbooks_year_document UNIQUE (year_reference, document)
)

