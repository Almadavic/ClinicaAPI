package com.project.clinicaapi.entity;

import com.project.clinicaapi.enumerated.Role;
import com.project.clinicaapi.enumerated.Specialty;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "tb_dentists")
@Getter
@Setter
public class Dentist extends User {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_dentists_workdays",
            joinColumns = @JoinColumn(name = "dentist_id"),
            inverseJoinColumns = @JoinColumn(name = "workday_id"))
    @Setter(AccessLevel.NONE)
    private final List<WorkDay> workDays = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "specialty", nullable = false)
    private Specialty specialty;

    @Builder(builderMethodName = "dentistBuilder")
    public Dentist(@NonNull String email, @NonNull String name, @NonNull String cellphone, String password, @NonNull Role role, boolean enabled,
                   @NonNull String country, @NonNull String state, @NonNull String city, @NonNull Specialty specialty) {
        super(email, name, cellphone, password, role, enabled, country, state, city);
        this.specialty = specialty;
    }

    public void addWorkDay(WorkDay workDay) {
        this.workDays.add(workDay);
    }

}
