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

DROP TABLE IF EXISTS review;

CREATE TABLE IF NOT EXISTS review
(
    id          BIGINT(20) NOT NULL AUTO_INCREMENT,
    user_email  varchar(45)   DEFAULT NULL,
    date        datetime(6)   DEFAULT NULL,
    rating      decimal(3, 2) DEFAULT NULL,
    product_id  BIGINT(20)    DEFAULT NULL,
    review_text text          DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  auto_increment = 1
  default charset = utf8mb3;

DROP TABLE IF EXISTS product_order;

CREATE TABLE IF NOT EXISTS product_order
(
    id          BIGINT(20) NOT NULL AUTO_INCREMENT,
    user_email  varchar(45) DEFAULT NULL,
    order_date  varchar(45) DEFAULT NULL,
    return_date varchar(45) DEFAULT NULL,
    product_id  BIGINT(20)  DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  auto_increment = 1
  default charset = utf8mb3;
