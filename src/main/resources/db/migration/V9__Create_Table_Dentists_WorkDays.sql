CREATE TABLE tb_dentists_workdays(
dentist_id UUID NOT NULL,
workday_id BIGINT NOT NULL,
FOREIGN KEY (dentist_id) REFERENCES tb_dentists(id),
FOREIGN KEY (workday_id) REFERENCES tb_workdays(id)
);