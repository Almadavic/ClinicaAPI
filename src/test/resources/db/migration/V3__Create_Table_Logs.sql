CREATE TABLE tb_logs(
id INT AUTO_INCREMENT PRIMARY KEY,
event VARCHAR(255) NOT NULL,
event_time TIMESTAMP(6) NOT NULL,
user_name VARCHAR(150) NOT NULL
);