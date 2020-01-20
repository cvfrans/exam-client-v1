DROP TABLE IF EXISTS clientapp;
 
CREATE TABLE clientapp (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(30) NOT NULL,
  last_name VARCHAR(50) NULL,
  age INT,
  birthday DATE NULL
);
 
INSERT INTO clientapp (first_name, last_name, age, birthday) VALUES
  ('Juan', 'Chavez', 29, '1990-03-08'),
  ('Rebeca', 'Delpino', 15, '2014-04-27'),
  ('Maria', 'Gutierrez', 35, '1984-06-16')