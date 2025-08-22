INSERT INTO roles (role_name, created_at, created_by)
VALUES ('ADMIN', NOW(), 'DBA');

INSERT INTO roles (role_name, created_at, created_by)
VALUES ('USER', NOW(), 'DBA');


INSERT INTO person (name, email, mobile_number, pwd, role_id, created_at, created_by)
VALUES ('Admin', 'admin@eazyschool.com', '3443434343', 'hashed_pwd', 1, CURRENT_DATE, 'DBA');
