CREATE TABLE tb_change_password(
id SERIAL PRIMARY KEY,
code VARCHAR(20) NOT NULL UNIQUE,
user_id VARCHAR(255),
FOREIGN KEY (user_id) REFERENCES tb_users(id)
);