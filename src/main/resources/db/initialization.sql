
CREATE DATABASE lib_db;

\c lib_db;

CREATE TABLE IF NOT EXISTS authors (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    birth_city VARCHAR(100),
    birth_date DATE NOT NULL,
    death_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS publishers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL UNIQUE,
    head_office_city VARCHAR(100) NOT NULL,
    foundation_year INTEGER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(300) NOT NULL,
    print_year INTEGER NOT NULL,
    author_id BIGINT NOT NULL,
    publisher_id BIGINT NOT NULL,
    created_date DATE DEFAULT CURRENT_DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE,
    FOREIGN KEY (publisher_id) REFERENCES publishers(id) ON DELETE CASCADE
);

CREATE INDEX idx_books_author ON books(author_id);
CREATE INDEX idx_books_publisher ON books(publisher_id);
CREATE INDEX idx_authors_name ON authors(last_name, first_name);
CREATE INDEX idx_publishers_name ON publishers(name);

INSERT INTO authors (first_name, last_name, birth_city, birth_date, death_date)
VALUES
    ('Лев', 'Толстой', 'Ясная Поляна', '1828-09-09', '1910-11-20'),
    ('Федор', 'Достоевский', 'Москва', '1821-11-11', '1881-02-09');

INSERT INTO publishers (name, head_office_city, foundation_year)
VALUES
    ('Эксмо', 'Москва', 1991),
    ('АСТ', 'Москва', 1990);

INSERT INTO books (title, print_year, author_id, publisher_id)
VALUES
    ('Война и мир', 2020, 1, 1),
    ('Преступление и наказание', 2021, 2, 2);
