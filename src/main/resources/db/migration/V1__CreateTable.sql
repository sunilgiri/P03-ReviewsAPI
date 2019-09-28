CREATE TABLE comment (
  id bigint(20) NOT NULL,
  text varchar(255) NOT NULL,
  user_id varchar(255) DEFAULT NULL,
  review_id bigint(20) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE review (
  id bigint(20) NOT NULL,
  date datetime DEFAULT NULL,
  login varchar(255) DEFAULT NULL,
  rating int(11) DEFAULT NULL,
  text varchar(255) NOT NULL,
  product_id bigint(20) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE product (
  id bigint(20) NOT NULL ,
  description varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);
