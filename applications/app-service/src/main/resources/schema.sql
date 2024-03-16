
-- Creación de la tabla Persona
CREATE TABLE IF NOT EXISTS  Person (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    gender VARCHAR(20),
    age INT,
    document_id VARCHAR(20) NOT NULL,
    address VARCHAR(255),
    phone_number VARCHAR(20),
    state BOOLEAN NOT NULL
);

-- Creación de la tabla Cliente
CREATE TABLE IF NOT EXISTS  Client (
    client_id SERIAL PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    state BOOLEAN NOT NULL,
    person_id INT NOT NULL,
    FOREIGN KEY (person_id) REFERENCES Person(id)
);