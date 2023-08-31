CREATE TABLE tb_dentists (
    id VARCHAR(255) PRIMARY KEY,
    cro VARCHAR(6) NOT NULL UNIQUE,
    specialty VARCHAR(23) NOT NULL
);

alter table if exists tb_dentists
       add constraint tb_dentists_tb_users
       foreign key (id)
       references tb_users;