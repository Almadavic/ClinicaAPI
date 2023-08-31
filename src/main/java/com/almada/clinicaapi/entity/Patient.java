package com.almada.clinicaapi.entity;

import com.almada.clinicaapi.enumerated.Gender;
import com.almada.clinicaapi.enumerated.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "tb_patients")
@Getter
@Setter
public class Patient extends User {

    @Column(name = "cpf", length = 14, nullable = false, unique = true)
    private String cpf;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments = new ArrayList<>();

    @Builder(builderMethodName = "patientBuilder")
    public Patient(@NonNull String login, @NonNull String email, @NonNull String name, @NonNull String cellphone, String password, @NonNull Gender gender,
                   @NonNull String country, @NonNull String state, @NonNull String city, @NonNull String cpf) {
        super(login, email, name, cellphone, password, gender, country, state, city);
        setRole(Role.PATIENT);
        this.cpf = cpf;
    }

}
