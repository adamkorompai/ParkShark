CREATE TABLE postal_code (
    id UUID PRIMARY KEY,
    code VARCHAR(10) NOT NULL,
    label VARCHAR(100) NOT NULL
);

CREATE TABLE address (
    id UUID PRIMARY KEY,
    street_name VARCHAR(255) NOT NULL,
    street_number VARCHAR(50) NOT NULL,
    postal_code_id UUID NOT NULL,
    CONSTRAINT fk_address_postal_code
        FOREIGN KEY (postal_code_id)
        REFERENCES postal_code(id)
);

CREATE TABLE parking_lot (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(50) NOT NULL CHECK (category IN ('UNDERGROUND', 'ABOVE_GROUND')),
    capacity INTEGER NOT NULL,
    price_per_hour DECIMAL(10, 2) NOT NULL,
    address_id UUID NOT NULL,
    division_id UUID NOT NULL,
    CONSTRAINT fk_parking_lot_address
        FOREIGN KEY (address_id)
        REFERENCES address(id),
    CONSTRAINT fk_parking_lot_division
        FOREIGN KEY (division_id)
        REFERENCES division(id)
);
