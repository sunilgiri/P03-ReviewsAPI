CREATE TABLE comment (
  id bigint(20) auto_increment,
  text varchar(255) NOT NULL,
  login varchar(255) DEFAULT NULL,
  review_id bigint(20) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE review (
  id bigint(20) auto_increment,
  date datetime DEFAULT NULL,
  login varchar(255) DEFAULT NULL,
  rating int(11) DEFAULT NULL,
  text varchar(255) NOT NULL,
  product_id bigint(20) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE product (
  id bigint(20) auto_increment ,
  description varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);
