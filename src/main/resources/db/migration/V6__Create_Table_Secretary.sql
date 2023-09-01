CREATE TABLE tb_secretaries (
    id VARCHAR(255) PRIMARY KEY,
    registration VARCHAR(255) NOT NULL UNIQUE,
    FOREIGN KEY (id) REFERENCES tb_users(id)
);
