CREATE DATABASE IF NOT EXISTS shop_db;
USE shop_db;

DROP TABLE IF EXISTS product;

CREATE TABLE product
(
    id               BIGINT(20) NOT NULL AUTO_INCREMENT,
    title            varchar(45) DEFAULT NULL,
    creator          varchar(45) DEFAULT NULL,
    description      text        DEFAULT NULL,
    copies           int(11)     DEFAULT NULL,
    copies_available int(11)     DEFAULT NULL,
    category         varchar(11) DEFAULT NULL,
    img              MEDIUMBLOB  DEFAULT NULL,
    PRIMARY KEY (id)
) engine = InnoDB
  auto_increment = 1
  default charset = utf8mb3;