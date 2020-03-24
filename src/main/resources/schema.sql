DROP TABLE IF EXISTS book;
CREATE TABLE book (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(45) DEFAULT NULL,
  author varchar(45) DEFAULT NULL,
  barcode varchar(45) DEFAULT NULL,
  quantity int(11) DEFAULT NULL,
  price double(20) DEFAULT NULL,
  year int(10) DEFAULT NULL,
  science_index int(2) DEFAULT NULL,
  PRIMARY KEY (id)
)