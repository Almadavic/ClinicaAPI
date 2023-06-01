package com.project.clinicaapi.entity;


import com.project.clinicaapi.enumerated.Gender;
import com.project.clinicaapi.enumerated.Role;
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
public class Patient extends User{

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments = new ArrayList<>();

    @Builder(builderMethodName = "patientBuilder")
    public Patient(@NonNull String login , @NonNull String email, @NonNull String name, @NonNull String cellphone, String password, boolean enabled,
                   @NonNull Gender gender, @NonNull String country, @NonNull String state, @NonNull String city) {
        super(login, email, name, cellphone, password, enabled, gender, country, state, city);
        setRole(Role.PATIENT);
    }

}
