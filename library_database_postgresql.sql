-- ============================== --
-- DDL (Data Definition Language) --
-- ============================== --

-- 1. INDEPENDENT TABLES --

CREATE TABLE category (
	id SERIAL PRIMARY KEY,
	category_name VARCHAR(100) NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE publisher (
	id SERIAL PRIMARY KEY,
	publisher_name VARCHAR(100) NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE author (
	id SERIAL PRIMARY KEY,
	first_name VARCHAR(100) NOT NULL,
	last_name VARCHAR(100) NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE member_status (
	id SERIAL PRIMARY KEY,
	status_value VARCHAR(50) NOT NULL UNIQUE,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE librarian (
	id SERIAL PRIMARY KEY,
	first_name VARCHAR(100) NOT NULL,
	last_name VARCHAR(100) NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. DEPENDENT TABLES--

CREATE TABLE member (
	id SERIAL PRIMARY KEY,
	first_name VARCHAR(100) NOT NULL,
	last_name VARCHAR(100) NOT NULL,
	tckn CHAR(11) UNIQUE NOT NULL,
	status_id INTEGER REFERENCES member_status(id) ON DELETE RESTRICT,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE book (
	id SERIAL PRIMARY KEY,
	title VARCHAR(100) NOT NULL,
	category_id INTEGER REFERENCES category(id) ON DELETE SET NULL,
	publication_date DATE,
	copies_owned INTEGER DEFAULT 1 CHECK (copies_owned >= 0),
	barcode_no VARCHAR(50) UNIQUE NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE publisher_book (
	id SERIAL PRIMARY KEY,
	publisher_id INTEGER REFERENCES publisher(id) ON DELETE CASCADE,
	book_id INTEGER REFERENCES book(id) ON DELETE CASCADE,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE book_author (
	id SERIAL PRIMARY KEY,
	book_id INTEGER REFERENCES book(id) ON DELETE CASCADE,
	author_id INTEGER REFERENCES author(id) ON DELETE CASCADE,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	UNIQUE (book_id, author_id)
);

CREATE TABLE loan (
	id SERIAL PRIMARY KEY,
	book_id INTEGER REFERENCES book(id) ON DELETE RESTRICT,
	member_id INTEGER REFERENCES member(id) ON DELETE RESTRICT,
	loan_date DATE NOT NULL DEFAULT CURRENT_DATE,
	return_date DATE, -- If it's null, the book hasn't been returned yet.
	librarian_id INTEGER REFERENCES librarian(id) ON DELETE SET NULL
);

CREATE TABLE fine (
	id SERIAL PRIMARY KEY,
	member_id INTEGER REFERENCES member(id) ON DELETE CASCADE,
	loan_id INTEGER REFERENCES loan(id) ON DELETE CASCADE,
	fine_date DATE NOT NULL DEFAULT CURRENT_DATE,
	fine_amount DECIMAL(10, 2) NOT NULL CHECK (fine_amount > 0),
	librarian_id INTEGER REFERENCES librarian(id) ON DELETE SET NULL
);

CREATE TABLE fine_payment (
	id SERIAL PRIMARY KEY,
	member_id INTEGER REFERENCES member(id) ON DELETE CASCADE,
	payment_amount DECIMAL(10, 2) NOT NULL CHECK (payment_amount > 0),
	payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- ================================ --
-- DML (Data Manipulation Language) --
-- ================================ --

-- ADDING --
-- Add Categories
INSERT INTO category (category_name) VALUES ('Bilgisayar Bilimleri');
INSERT INTO category (category_name) VALUES ('Edebiyat');
INSERT INTO category (category_name) VALUES ('Tarih');
INSERT INTO category (category_name) VALUES ('Felsefe');
INSERT INTO category (category_name) VALUES ('Bilim Kurgu');

-- Add Publisher
INSERT INTO publisher (publisher_name) VALUES ('İndigo Kitap');
INSERT INTO publisher (publisher_name) VALUES ('İş Bankası Kültür Yayınları');
INSERT INTO publisher (publisher_name) VALUES ('Can Yayınları');
INSERT INTO publisher (publisher_name) VALUES ('Pegasus Yayınları');
INSERT INTO publisher (publisher_name) VALUES ('İthaki Yayınları');

-- Add Author
INSERT INTO author (first_name, last_name) VALUES ('Martin', 'Fowler');
INSERT INTO author (first_name, last_name) VALUES ('Sabahattin', 'Ali');
INSERT INTO author (first_name, last_name) VALUES ('İlber', 'Ortaylı');
INSERT INTO author (first_name, last_name) VALUES ('George', 'Orwell');
INSERT INTO author (first_name, last_name) VALUES ('Isaac', 'Asimov');

-- Add Member Status
INSERT INTO member_status (status_value) VALUES ('Aktif');
INSERT INTO member_status (status_value) VALUES ('Pasif');
INSERT INTO member_status (status_value) VALUES ('Cezalı');
INSERT INTO member_status (status_value) VALUES ('Beklemede');
INSERT INTO member_status (status_value) VALUES ('İptal');

-- Add Librarian
INSERT INTO librarian (first_name, last_name) VALUES ('Ahmet', 'Yılmaz');
INSERT INTO librarian (first_name, last_name) VALUES ('Ayşe', 'Demir');
INSERT INTO librarian (first_name, last_name) VALUES ('Fatma', 'Çelik');
INSERT INTO librarian (first_name, last_name) VALUES ('Mehmet', 'Kaya');
INSERT INTO librarian (first_name, last_name) VALUES ('Zeynep', 'Şahin');

-- Add Members
INSERT INTO member (first_name, last_name, tckn, status_id) VALUES ('Deniz', 'Göncü', '12345678901', 1);
INSERT INTO member (first_name, last_name, tckn, status_id) VALUES ('Barış', 'Polat', '23456789012', 1);
INSERT INTO member (first_name, last_name, tckn, status_id) VALUES ('Hikmet', 'Dağıstanlı', '34567890123', 3);
INSERT INTO member (first_name, last_name, tckn, status_id) VALUES ('Deniz', 'Kurt', '45678901234', 1);
INSERT INTO member (first_name, last_name, tckn, status_id) VALUES ('Sude', 'Ürüt', '56789012345', 2);

-- Add Book
INSERT INTO book (title, category_id, publication_date, copies_owned, barcode_no) VALUES ('Clean Code', 1, '2008-08-01', 5, 'BRC1001');
INSERT INTO book (title, category_id, publication_date, copies_owned, barcode_no) VALUES ('Kürk Mantolu Madonna', 2, '1943-01-01', 10, 'BRC1002');
INSERT INTO book (title, category_id, publication_date, copies_owned, barcode_no) VALUES ('Türklerin Tarihi', 3, '2015-04-10', 3, 'BRC1003');
INSERT INTO book (title, category_id, publication_date, copies_owned, barcode_no) VALUES ('1984', 5, '1949-06-08', 8, 'BRC1004');
INSERT INTO book (title, category_id, publication_date, copies_owned, barcode_no) VALUES ('Vakıf', 5, '1951-01-01', 4, 'BRC1005');

-- Publisher_Book (Many to many)
INSERT INTO publisher_book (publisher_id, book_id) VALUES (1, 1);
INSERT INTO publisher_book (publisher_id, book_id) VALUES (2, 2);
INSERT INTO publisher_book (publisher_id, book_id) VALUES (2, 3);
INSERT INTO publisher_book (publisher_id, book_id) VALUES (3, 4);
INSERT INTO publisher_book (publisher_id, book_id) VALUES (5, 5);

-- Book_Author (Many to many)
INSERT INTO book_author (book_id, author_id) VALUES (1, 1);
INSERT INTO book_author (book_id, author_id) VALUES (2, 2);
INSERT INTO book_author (book_id, author_id) VALUES (3, 3);
INSERT INTO book_author (book_id, author_id) VALUES (4, 4);
INSERT INTO book_author (book_id, author_id) VALUES (5, 5);

-- Loan
INSERT INTO loan (book_id, member_id, loan_date, return_date, librarian_id) VALUES (1, 1, '2023-09-01', '2023-09-15', 1);
INSERT INTO loan (book_id, member_id, loan_date, return_date, librarian_id) VALUES (2, 2, '2023-09-10', NULL, 2);
INSERT INTO loan (book_id, member_id, loan_date, return_date, librarian_id) VALUES (4, 3, '2023-08-01', '2023-08-30', 1);
INSERT INTO loan (book_id, member_id, loan_date, return_date, librarian_id) VALUES (3, 4, '2023-09-20', NULL, 3);
INSERT INTO loan (book_id, member_id, loan_date, return_date, librarian_id) VALUES (5, 5, '2023-07-15', '2023-07-25', 2);

-- Fine 
INSERT INTO fine (member_id, loan_id, fine_date, fine_amount, librarian_id) VALUES (3, 3, '2023-08-16', 15.50, 1);
INSERT INTO fine (member_id, loan_id, fine_date, fine_amount, librarian_id) VALUES (2, 2, '2023-09-25', 10.00, 2);
INSERT INTO fine (member_id, loan_id, fine_date, fine_amount, librarian_id) VALUES (3, 3, '2023-08-20', 5.00, 1);
INSERT INTO fine (member_id, loan_id, fine_date, fine_amount, librarian_id) VALUES (4, 4, '2023-10-05', 25.00, 3);
INSERT INTO fine (member_id, loan_id, fine_date, fine_amount, librarian_id) VALUES (1, 1, '2023-09-16', 2.50, 1);

-- Fine Payment
INSERT INTO fine_payment (member_id, payment_amount, payment_date) VALUES (3, 10.00, '2023-08-18');
INSERT INTO fine_payment (member_id, payment_amount, payment_date) VALUES (3, 10.50, '2023-08-22');
INSERT INTO fine_payment (member_id, payment_amount, payment_date) VALUES (1, 2.50, '2023-09-17');
INSERT INTO fine_payment (member_id, payment_amount, payment_date) VALUES (2, 5.00, '2023-09-26');
INSERT INTO fine_payment (member_id, payment_amount, payment_date) VALUES (4, 25.00, '2023-10-06');


-- UPDATING --
-- TCKN Updating --
UPDATE member
SET tckn = '11111111111' , updated_at = CURRENT_TIMESTAMP
WHERE id= 5;

-- Book Stock Updating --
UPDATE book 
SET copies_owned = copies_owned + 2, updated_at = CURRENT_TIMESTAMP
WHERE title = '1984';

-- DELETING --
-- Deleting a cancelled member from the system --
-- (It may be subject to FK restrictions if it is linked to a Loan or Fine; --
-- for example, it will work if id=5 has no penalties, or penalties must be cleared first.) --
DELETE FROM member_status
WHERE status_value = 'İptal';


-- SELECT --
-- Aggregate func. total book number in library --
SELECT SUM(copies_owned) AS "Toplam Kitap Stoğu" 
FROM book;

-- Number of Books by Category and Average Publication Year --
SELECT 
    c.category_name, 
    COUNT(b.id) AS "Farklı Kitap Sayısı", 
    SUM(b.copies_owned) AS "Toplam Stok"
FROM category c
LEFT JOIN book b ON c.id = b.category_id
GROUP BY c.category_name
ORDER BY "Toplam Stok" DESC;

-- The total amount of fines paid by a member --
SELECT 
    m.first_name, 
    m.last_name, 
    SUM(fp.payment_amount) AS "Toplam Ödenen Ceza"
FROM member m
INNER JOIN fine_payment fp ON m.id = fp.member_id
WHERE m.id = 3
GROUP BY m.first_name, m.last_name;

--  Members who received books that have not yet been returned (Return_date NULL) --
SELECT m.first_name, m.last_name, b.title, l.loan_date
FROM loan l
INNER JOIN member m ON l.member_id = m.id
INNER JOIN book b ON l.book_id = b.id
WHERE l.return_date IS NULL;

