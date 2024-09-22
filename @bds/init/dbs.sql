CREATE TABLE Customers (
    customerId SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL, 
    password_hash VARCHAR(255) NOT NULL,
    phone VARCHAR(15),                   
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE Products (
    productId SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    stock INTEGER DEFAULT 0,
    description TEXT,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

