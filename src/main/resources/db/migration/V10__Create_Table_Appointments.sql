CREATE TABLE tb_appointments(
id VARCHAR(255) PRIMARY KEY,
procedure VARCHAR(255) NOT NULL,
appointment_date DATE NOT NULL,
time_start TIME NOT NULL,
time_end TIME NOT NULL,
week_day VARCHAR(13) NOT NULL,
dentist_id VARCHAR(255) NOT NULL,
patient_id VARCHAR(255) NOT NULL,
FOREIGN KEY (dentist_id) REFERENCES tb_dentists(id),
FOREIGN KEY (patient_id) REFERENCES tb_patients(id)
);