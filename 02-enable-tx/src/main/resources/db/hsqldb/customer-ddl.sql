DROP TABLE customer IF EXISTS;

CREATE TABLE customer (
  id         INTEGER IDENTITY PRIMARY KEY,
  customer_name VARCHAR(30)
);
CREATE INDEX cust_last_name ON customer (customer_name);
