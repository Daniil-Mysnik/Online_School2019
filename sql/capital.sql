DROP DATABASE IF EXISTS capital;
CREATE DATABASE capital;
USE capital;

CREATE TABLE capitals (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(50) NOT NULL,
UNIQUE(name), PRIMARY KEY (id)) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO capitals (name) VALUES ("London");
INSERT INTO capitals (name) VALUES ("Berlin");
INSERT INTO capitals (name) VALUES ("Tallinn");
INSERT INTO capitals (name) VALUES ("Bangkok");
INSERT INTO capitals (name) VALUES ("Vienna");
INSERT INTO capitals (name) VALUES ("Minsk");
INSERT INTO capitals (name) VALUES ("Sofia");
INSERT INTO capitals (name) VALUES ("Hanoi");
INSERT INTO capitals (name) VALUES ("Athens");
INSERT INTO capitals (name) VALUES ("Cairo");