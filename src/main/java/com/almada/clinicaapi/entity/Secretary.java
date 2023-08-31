package com.almada.clinicaapi.entity;

import com.almada.clinicaapi.enumerated.Gender;
import com.almada.clinicaapi.enumerated.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@NoArgsConstructor
@Table(name = "tb_secretaries")
@Getter
@Setter
public class Secretary extends User {

    @Column(name = "registration", nullable = false, unique = true)
    private String registration;

    @Builder(builderMethodName = "secretaryBuilder")
    public Secretary(@NonNull String login, @NonNull String email, @NonNull String name, @NonNull String cellphone, @NonNull String password,
                     @NonNull Gender gender, @NonNull String country, @NonNull String state, @NonNull String city, @NonNull String registration) {
        super(login, email, name, cellphone, password, gender, country, state, city);
        setRole(Role.SECRETARY);
        setEnabled(true);
        this.registration = registration;
    }

}
