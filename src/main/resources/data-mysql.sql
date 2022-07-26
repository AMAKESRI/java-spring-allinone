--Users
INSERT INTO users (id, email, password, first_name, last_name) values
    (1, 'admin@xyz.com', '$2a$10$Of7RQjpeJzLA1jMBMpZRVe9pPAz0Cva6sBYCIFDtfYqWUJv0hRWK6', 'admin', 'test'),
    (2, 'user@xyz.com', '$2a$10$Of7RQjpeJzLA1jMBMpZRVe9pPAz0Cva6sBYCIFDtfYqWUJv0hRWK6', 'user', 'test');

-- roles
INSERT INTO roles (id, name) values (1, 'ROLE_ADMIN'), (2, 'ROLE_USER');

-- user roles
INSERT INTO user_role (user_id, role_id) values (1, 1), (2, 2);