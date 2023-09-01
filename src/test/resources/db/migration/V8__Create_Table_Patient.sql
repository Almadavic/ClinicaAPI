CREATE TABLE tb_patients (
    id VARCHAR(255) PRIMARY KEY,
    cpf VARCHAR(14) NOT NULL UNIQUE
);

alter table if exists tb_patients
       add constraint tb_patients_tb_users
       foreign key (id)
       references tb_users;

