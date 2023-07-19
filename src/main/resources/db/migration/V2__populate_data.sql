INSERT INTO supplier (id,name) VALUES
  ('131e4cdd-bb78-4769-a0c7-cb948a9f1231','Supplier 1'),
  ('131e4cdd-bb78-4769-a0c7-cb948a9f1232','Supplier 2'),
  ('131e4cdd-bb78-4769-a0c7-cb948a9f1233','Supplier 3');

INSERT INTO product_category (id,name, description) VALUES
    ('231e4cdd-bb78-4769-a0c7-cb948a9f1231','Category 1','Category 1 Description'),
    ('231e4cdd-bb78-4769-a0c7-cb948a9f1232','Category 2','Category 2 Description'),
    ('231e4cdd-bb78-4769-a0c7-cb948a9f1233','Category 3','Category 3 Description');

INSERT INTO product (id,name, price,weight, description, supplier_id, category_id) VALUES
  ('331e4cdd-bb78-4769-a0c7-cb948a9f1231','Product 1', 9.99,10, 'Product 1 description', '131e4cdd-bb78-4769-a0c7-cb948a9f1231', '231e4cdd-bb78-4769-a0c7-cb948a9f1231'),
  ('331e4cdd-bb78-4769-a0c7-cb948a9f1232','Product 2', 19.99,9, 'Product 2 description', '131e4cdd-bb78-4769-a0c7-cb948a9f1232', '231e4cdd-bb78-4769-a0c7-cb948a9f1232');

INSERT INTO location (id,address_city, address_country, address_street, name) VALUES
    ('431e4cdd-bb78-4769-a0c7-cb948a9f1231','Cluj', 'Romania', 'croitorilor nr 11-12', 'Centru'),
    ('431e4cdd-bb78-4769-a0c7-cb948a9f1232','Floresti', 'Romania', 'str eroilor', 'MetropolitanArea');

INSERT INTO stock (id,quantity, location_id, product_id) VALUES
    ('531e4cdd-bb78-4769-a0c7-cb948a9f1231',10, '431e4cdd-bb78-4769-a0c7-cb948a9f1231', '331e4cdd-bb78-4769-a0c7-cb948a9f1231'),
    ('531e4cdd-bb78-4769-a0c7-cb948a9f1232',20, '431e4cdd-bb78-4769-a0c7-cb948a9f1232', '331e4cdd-bb78-4769-a0c7-cb948a9f1232');

INSERT INTO customer (id,email_address, first_name, last_name, password, username) VALUES
    ('631e4cdd-bb78-4769-a0c7-cb948a9f1231','johndoe@microsoft.com', 'John', 'Doe', 'sa', 'johndoe'),
    ('631e4cdd-bb78-4769-a0c7-cb948a9f1232','seanjohn@cloudflare.com', 'Sean', 'John', 'sa', 'seanjean');

INSERT INTO orders (id,address_city, address_country, address_street, created_at, customer_id) VALUES
    ('731e4cdd-bb78-4769-a0c7-cb948a9f1231','Zalau', 'Romania', 'h47 B.P.Hasdeu street', '2023-04-17 18:47:52.69', '631e4cdd-bb78-4769-a0c7-cb948a9f1231'),
    ('731e4cdd-bb78-4769-a0c7-cb948a9f1232','Cluj', 'Romania', 'Croitorilor 12', '2023-06-10 18:47:52.69', '631e4cdd-bb78-4769-a0c7-cb948a9f1232');

INSERT INTO revenue (id,date, sum, location_id) VALUES
     ('831e4cdd-bb78-4769-a0c7-cb948a9f1231','2023-03-02 ', 12.19, '431e4cdd-bb78-4769-a0c7-cb948a9f1231'),
     ('831e4cdd-bb78-4769-a0c7-cb948a9f1232','2023-09-23 ', 56.43, '431e4cdd-bb78-4769-a0c7-cb948a9f1232');

INSERT INTO order_detail (id,quantity, order_id, product_id,shipped_from) VALUES
     ('931e4cdd-bb78-4769-a0c7-cb948a9f1231',1,'731e4cdd-bb78-4769-a0c7-cb948a9f1231', '331e4cdd-bb78-4769-a0c7-cb948a9f1231','431e4cdd-bb78-4769-a0c7-cb948a9f1231'),
     ('931e4cdd-bb78-4769-a0c7-cb948a9f1232',1,'731e4cdd-bb78-4769-a0c7-cb948a9f1231', '331e4cdd-bb78-4769-a0c7-cb948a9f1232','431e4cdd-bb78-4769-a0c7-cb948a9f1232');