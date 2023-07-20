CREATE TABLE IF NOT EXISTS product_category (
   id uuid not null,
   name VARCHAR(255) NULL,
   description VARCHAR(255) NULL,
   CONSTRAINT pk_product_category PRIMARY KEY (id)
);

CREATE UNIQUE INDEX uq_product_category_name ON product_category(name);

CREATE TABLE IF NOT EXISTS supplier(
id uuid NOT NULL,
name varchar(50),
CONSTRAINT pk_supplier PRIMARY KEY (id)
);

CREATE UNIQUE INDEX uq_supplier_name ON supplier(name);

CREATE TABLE IF NOT EXISTS product(
  id uuid NOT NULL,
  name VARCHAR(100) NOT NULL,
  description VARCHAR(255),
  price DECIMAL(10, 2) NOT NULL,
  weight DOUBLE PRECISION  NULL,
  img_url VARCHAR(255) NULL,
  supplier_id uuid NOT NULL,
  category_id uuid NOT NULL,
  CONSTRAINT pk_product PRIMARY KEY (id)
);
ALTER TABLE product ADD CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES product_category (id);
ALTER TABLE product ADD CONSTRAINT fk_product_supplier FOREIGN KEY (supplier_id) REFERENCES supplier (id);
CREATE UNIQUE INDEX uq_product_name ON product(name);


CREATE TABLE IF NOT EXISTS location(
    id uuid NOT NULL,
    name varchar(100) not null,
    address_country varchar(100) ,
    address_city varchar(100) ,
    address_county varchar(100),
    address_street varchar(100),
    CONSTRAINT pk_location PRIMARY KEY (id)
);
CREATE UNIQUE INDEX uq_location_name ON location(name);

CREATE TABLE IF NOT EXISTS customer(
    id uuid NOT NULL,
    first_name varchar(100) not null,
    last_name varchar(100) not null,
    username varchar(100) not null,
    password varchar(100) not null,
    email_address varchar(50) not null,
    CONSTRAINT pk_customer PRIMARY KEY (id)
);
CREATE UNIQUE INDEX uq_customer_username ON customer(username);


CREATE TABLE IF NOT EXISTS orders(
    id uuid NOT NULL,
    customer_id uuid NOT NULL,
    created_at timestamp,
    address_country varchar(60) NOT NULL,
    address_city varchar(60) NOT NULL,
    address_county varchar(60) ,
    address_street varchar(60),
    CONSTRAINT pk_orders PRIMARY KEY (id)
);

ALTER TABLE orders ADD CONSTRAINT fk_order_customer foreign key(customer_id) REFERENCES customer (id);

CREATE TABLE IF NOT EXISTS stock(
    id uuid NOT NULL,
    product_id uuid NOT NULL,
    location_id uuid NOT NULL,
    quantity int NOT NULL,
    CONSTRAINT pk_stock PRIMARY KEY (id)
);

ALTER TABLE stock ADD CONSTRAINT fk_stock_product FOREIGN KEY(product_id) REFERENCES product(id);
ALTER TABLE stock ADD CONSTRAINT fk_stock_location FOREIGN KEY(location_id) REFERENCES location(id);

CREATE TABLE IF NOT EXISTS order_detail(
    id uuid NOT NULL,
    order_id uuid NOT NULL,
    product_id uuid NOT NULL,
    quantity int NOT NULL,
    shipped_from uuid NOT NULL,
    CONSTRAINT pk_order_detail PRIMARY KEY (id)
);

ALTER TABLE order_detail ADD CONSTRAINT fk_order_detail_order FOREIGN KEY (order_id) REFERENCES orders(id);
ALTER TABLE order_detail ADD CONSTRAINT fk_order_detail_product FOREIGN KEY (product_id) REFERENCES product(id);
ALTER TABLE order_detail ADD CONSTRAINT fk_order_detail_location FOREIGN KEY(shipped_from) REFERENCES location (id);

CREATE TABLE IF NOT EXISTS revenue(
    id uuid NOT NULL,
    location_id uuid NOT NULL,
    date date NOT NULL,
    sum decimal NOT NULL,
    CONSTRAINT pk_revenue PRIMARY KEY (id)
);
ALTER TABLE revenue ADD CONSTRAINT fk_revenue_location FOREIGN KEY (location_id) REFERENCES location(id)


