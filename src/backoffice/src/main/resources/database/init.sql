CREATE TABLE IF NOT EXISTS client
(
    id      uuid,
    name    VARCHAR(255) NOT NULL,
    email   VARCHAR(255) NOT NULL,
    phone   VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS product
(
    id    uuid,
    name  VARCHAR(255)   NOT NULL,
    slug  VARCHAR(255)   NOT NULL UNIQUE,
    stock INT            NOT NULL,
    price DECIMAL(10, 2) NOT NULL,

    PRIMARY KEY (id)
);