-- Create database
CREATE DATABASE jusvid;

-- Connect to the jusvid database
\c jusvid;

-- Table contact_msg
CREATE TABLE IF NOT EXISTS contact_msg (
  contact_id SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  mobile_num VARCHAR(10) NOT NULL,
  email VARCHAR(100) NOT NULL,
  subject VARCHAR(100) NOT NULL,
  message VARCHAR(500) NOT NULL,
  status VARCHAR(10) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  created_by VARCHAR(50) NOT NULL,
  updated_at TIMESTAMP DEFAULT NULL,
  updated_by VARCHAR(50) DEFAULT NULL
);

-- Table address
CREATE TABLE IF NOT EXISTS address (
  address_id SERIAL PRIMARY KEY,
  address1 VARCHAR(200) NOT NULL,
  address2 VARCHAR(200) DEFAULT NULL,
  city VARCHAR(50) NOT NULL,
  state VARCHAR(50) NOT NULL,
  zip_code INTEGER NOT NULL,
  created_at TIMESTAMP NOT NULL,
  created_by VARCHAR(50) NOT NULL,
  updated_at TIMESTAMP DEFAULT NULL,
  updated_by VARCHAR(50) DEFAULT NULL
);

-- Table person
CREATE TABLE IF NOT EXISTS person (
  person_id SERIAL PRIMARY KEY,
  nameofu VARCHAR(100) NOT NULL,
  email VARCHAR(50) NOT NULL,
  mobile_number VARCHAR(20) NOT NULL,
  pwd VARCHAR(200) NOT NULL,
  role_id INTEGER NOT NULL,
  address_id INTEGER,
  created_at TIMESTAMP NOT NULL,
  created_by VARCHAR(50) NOT NULL,
  updated_at TIMESTAMP DEFAULT NULL,
  updated_by VARCHAR(50) DEFAULT NULL,
  CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES roles(role_id),
  CONSTRAINT fk_address FOREIGN KEY (address_id) REFERENCES address(address_id)
);

-- Table video
CREATE TABLE IF NOT EXISTS video (
  id BIGSERIAL PRIMARY KEY,
  description VARCHAR(255),
  file_path VARCHAR(255),
  title VARCHAR(255),
  uploaded_by INTEGER,
  CONSTRAINT fk_uploaded_by FOREIGN KEY (uploaded_by) REFERENCES person(person_id)
);
