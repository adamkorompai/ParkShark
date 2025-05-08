-- Country Codes (no sequence here, uses code as PK)
INSERT INTO country_code (code, country_name) VALUES
    ('BE', 'Belgium'),
    ('FR', 'France'),
    ('DE', 'Germany');

-- License Plates
INSERT INTO license_plate (plate_number, country_code) VALUES
   ('1-ABC-123', 'BE'),
   ('2-XYZ-789', 'FR'),
   ('3-QWE-456', 'DE');

-- Membership Levels
INSERT INTO membership_leveL (name, monthly_cost, reduction_percent, max_parking_duration) VALUES
   ('BRONZE', 0, 0, 4),
   ('SILVER', 10.0, 20, 6),
   ('GOLD', 40.0, 30, 24);

-- Postal Codes
INSERT INTO postal_code (id, code, label) VALUES
    (NEXTVAL('postal_code_seq'), '1000', 'Brussels'),
    (NEXTVAL('postal_code_seq'), '75000', 'Paris'),
    (NEXTVAL('postal_code_seq'), '10115', 'Berlin');

-- Addresses
INSERT INTO address (id, street_name, street_number, postal_code_id) VALUES
    (NEXTVAL('address_seq'), 'Main Street', '1A', 1),
    (NEXTVAL('address_seq'), 'Rue Lafayette', '22', 2),
    (NEXTVAL('address_seq'), 'Alexanderplatz', '5', 3),
    (NEXTVAL('address_seq'), 'Park Lane', '10', 1);

-- Divisions (Companies ParkShark owns)
INSERT INTO division (id, name, original_name, director, parent_division_id) VALUES
    (NEXTVAL('division_seq'), 'ParkShark HQ', 'ParkShark', 'Elena De Groot', NULL),  -- Root
    (NEXTVAL('division_seq'), 'EasyPark Belgium', 'EasyPark BE', 'Laurent Dupont', 1),
    (NEXTVAL('division_seq'), 'Paris AutoPark', 'Paris AutoPark', 'Claire Moreau', 1),
    (NEXTVAL('division_seq'), 'Berlin SchnellPark', 'Berlin SchnellPark GmbH', 'Fritz Bauer', 1);

-- Contacts
INSERT INTO contact (id, name, email, phone_number, telephone_number, address_id) VALUES
    (NEXTVAL('contact_seq'), 'Alice Brown', 'alice@example.com', '+32471234567', NULL, 1),
    (NEXTVAL('contact_seq'), 'Bob Martin', 'bob@example.com', NULL, '+33123456789', 2);

-- Parking Lots
INSERT INTO parking_lot (id, name, category, capacity, price_per_hour, address_id, division_id) VALUES
    (NEXTVAL('parking_lot_seq'), 'Central Brussels Parking', 'UNDERGROUND', 200, 2.50, 4, 2),
    (NEXTVAL('parking_lot_seq'), 'Paris East Parking', 'ABOVE_GROUND', 100, 1.50, 2, 3),
    (NEXTVAL('parking_lot_seq'), 'Berlin Mitte Garage', 'UNDERGROUND', 150, 2.00, 3, 4);

-- Users
INSERT INTO users (id, first_name, last_name, telephone_number, mobile_number, email, role, registration_date, address_id, plate_number, membership_level) VALUES
    (NEXTVAL('user_seq'), 'Tom', 'White', NULL, '+32475555555', 'tom.white@example.com', 'MEMBER', '2024-01-10', 1, '1-ABC-123', 'SILVER'),
    (NEXTVAL('user_seq'), 'Linda', 'Green', '+33111222333', NULL, 'linda.green@example.com', 'MANAGER', '2023-11-20', 2, '2-XYZ-789', 'GOLD'),
    (NEXTVAL('user_seq'), 'Hans', 'Müller', NULL, '+491511112222', 'hans.mueller@example.de', 'MEMBER', '2024-03-05', 3, '3-QWE-456', 'BRONZE');

-- Allocations
INSERT INTO allocation (id, status, start_time, end_time, user_id, parking_lot_id, plate_number) VALUES
     (NEXTVAL('allocation_seq'), 'ACTIVE', '2025-05-01', NULL, 1, 1, '1-ABC-123'),
     (NEXTVAL('allocation_seq'), 'FINISHED', '2025-04-01', '2025-04-02', 2, 2, '2-XYZ-789'),
     (NEXTVAL('allocation_seq'), 'ACTIVE', '2025-05-05', NULL, 3, 3, '3-QWE-456');
