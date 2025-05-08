CREATE SEQUENCE division_seq
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE division (
                          id BIGINT PRIMARY KEY NOT NULL,
                          name VARCHAR(255) NOT NULL,
                          original_name VARCHAR(255) NOT NULL,
                          director VARCHAR(255) NOT NULL,
                          parent_division_id BIGINT,
                          CONSTRAINT fk_parent_division
                              FOREIGN KEY (parent_division_id)
                                  REFERENCES division (id)
);

CREATE SEQUENCE postal_code_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE postal_code (
                             id BIGINT PRIMARY KEY NOT NULL,
                             code VARCHAR(10) NOT NULL,
                             label VARCHAR(100) NOT NULL
);

CREATE SEQUENCE address_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE address (
                         id BIGINT PRIMARY KEY NOT NULL,
                         street_name VARCHAR(255) NOT NULL,
                         street_number VARCHAR(50) NOT NULL,
                         postal_code_id BIGINT NOT NULL,
                         CONSTRAINT fk_address_postal_code
                             FOREIGN KEY (postal_code_id)
                                 REFERENCES postal_code(id)
);

CREATE SEQUENCE contact_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE contact (
    id BIGINT PRIMARY KEY NOT NULL,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    phone_number VARCHAR(50),
    telephone_number VARCHAR(50),
    address_id BIGINT NOT NULL,
    CONSTRAINT fk_contact_address
        FOREIGN KEY (address_id)
        REFERENCES address(id)
);

CREATE SEQUENCE parking_lot_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE parking_lot (
                             id BIGINT PRIMARY KEY NOT NULL,
                             name VARCHAR(255) NOT NULL,
                             category VARCHAR(50) NOT NULL CHECK (category IN ('UNDERGROUND', 'ABOVE_GROUND')),
                             capacity INTEGER NOT NULL,
                             price_per_hour DECIMAL(10, 2) NOT NULL,
                             contact_person_id BIGINT NOT NULL,
                             address_id BIGINT NOT NULL,
                             division_id BIGINT,
                             CONSTRAINT fk_contact_person_id
                                 FOREIGN KEY (contact_person_id)
                                     REFERENCES contact(id),
                             CONSTRAINT fk_parking_lot_address
                                 FOREIGN KEY (address_id)
                                     REFERENCES address(id),
                             CONSTRAINT fk_parking_lot_division
                                 FOREIGN KEY (division_id)
                                     REFERENCES division(id)

);

CREATE TABLE country_code (
    code VARCHAR(5) PRIMARY KEY NOT NULL,
    country_name VARCHAR(50) NOT NULL
);

CREATE TABLE license_plate (
    plate_number VARCHAR(50) PRIMARY KEY NOT NULL,
    country_code VARCHAR(5) NOT NULL,
    CONSTRAINT fk_license_plate_country FOREIGN KEY (country_code) REFERENCES country_code(code)
);

CREATE TABLE membership_leveL (
    name VARCHAR(50) PRIMARY KEY NOT NULL,
    monthly_cost DOUBLE PRECISION NOT NULL,
    reduction_percent INT,
    max_parking_duration INT
);

CREATE SEQUENCE user_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE users (
    id BIGINT PRIMARY KEY NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    telephone_number VARCHAR(50),
    mobile_number VARCHAR(50),
    email VARCHAR(50) NOT NULL,
    role VARCHAR(50) NOT NULL,
    registration_date DATE NOT NULL,
    address_id BIGINT NOT NULL,
    plate_number VARCHAR(50) NOT NULL,
    membership_level VARCHAR(50) NOT NULL,
    CONSTRAINT fk_user_address FOREIGN KEY (address_id) REFERENCES address(id),
    CONSTRAINT fk_user_license_plate FOREIGN KEY (plate_number) REFERENCES license_plate(plate_number),
    CONSTRAINT fk_user_membership FOREIGN KEY (membership_level) REFERENCES membership_leveL(name)
);

CREATE SEQUENCE allocation_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE allocation (
    id BIGINT PRIMARY KEY NOT NULL,
    status VARCHAR(50) NOT NULL,
    start_time DATE NOT NULL,
    end_time DATE,
    user_id BIGINT NOT NULL,
    parking_lot_id BIGINT NOT NULL,
    plate_number VARCHAR(50) NOT NULL,
    CONSTRAINT fk_allocation_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_allocation_parking_lot FOREIGN KEY (parking_lot_id) REFERENCES parking_lot(id),
    CONSTRAINT fk_allocation_license_plate FOREIGN KEY (plate_number) REFERENCES license_plate(plate_number)
);
