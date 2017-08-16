create database example;
use example;

CREATE TABLE greeting_message (
     id INT NOT NULL AUTO_INCREMENT,
     name VARCHAR(60) NOT NULL,
     email VARCHAR(100),
     message VARCHAR(300),
     creation_date DATETIME,
     updated_date DATETIME,
     PRIMARY KEY (id)
);

-- for testing
create database example_test;
use example_test;