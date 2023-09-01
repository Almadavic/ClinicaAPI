CREATE TABLE tb_patients (
    id VARCHAR(255) PRIMARY KEY,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    FOREIGN KEY (id) REFERENCES tb_users(id)
);

