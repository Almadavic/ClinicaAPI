CREATE TABLE tb_secretaries (
    id VARCHAR(255) PRIMARY KEY,
    registration VARCHAR(255) NOT NULL UNIQUE
);

alter table if exists tb_secreataries
       add constraint tb_secretaries_tb_users
       foreign key (id)
       references tb_users;