
INSERT INTO users(username,password, email) VALUES ('sarah1', 'abc123', 'test@test');

INSERT INTO users(username, password, email) VALUES ('sarah2', 'abc1234', 'test@test');

INSERT INTO markers(title, lat, lng, info, user_id) VALUES ('test', 12.00, 12.00, 'test', 2);


INSERT INTO markers(title, lat, lng, info, user_id) VALUES ('test', 12.00, 12.00, 'test', 1);

INSERT INTO roles(name) VALUES ('ADMIN');

INSERT INTO users_roles(user_id, role_id) VALUES(1,1);
INSERT INTO users_roles(user_id, role_id) VALUES(2,1);


SELECT * FROM users;