CREATE TABLE division (
                          id UUID PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          original_name VARCHAR(255) NOT NULL,
                          director VARCHAR(255) NOT NULL,
                          parent_division_id UUID,
                          CONSTRAINT fk_parent_division
                              FOREIGN KEY (parent_division_id)
                                  REFERENCES division (id)
                                  ON DELETE SET NULL
);