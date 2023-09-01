CREATE TABLE tb_dentists (
    id VARCHAR(255) PRIMARY KEY,
    cro VARCHAR(6) NOT NULL UNIQUE,
    specialty VARCHAR(23) NOT NULL,
    FOREIGN KEY (id) REFERENCES tb_users(id)
);