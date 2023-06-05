package com.project.clinicaapi.entity;

import com.project.clinicaapi.enumerated.Gender;
import com.project.clinicaapi.enumerated.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@NoArgsConstructor
@Table(name = "tb_secreataries")
@Getter
@Setter
public class Secretary extends User{

    @Column(name = "registration", nullable = false, unique = true)
    private String registration;

    @Builder(builderMethodName = "secretaryBuilder")
    public Secretary(@NonNull String login, @NonNull String email, @NonNull String name, @NonNull String cellphone, @NonNull String password, boolean enabled,
                     @NonNull Gender gender, @NonNull String country, @NonNull String state, @NonNull String city, @NonNull String registration) {
        super(login, email, name, cellphone, password, enabled, gender, country, state, city);
        setRole(Role.SECRETARY);
        setEnabled(true);
        this.registration = registration;
    }

}
