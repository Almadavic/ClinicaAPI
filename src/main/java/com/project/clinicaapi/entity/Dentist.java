package com.project.clinicaapi.entity;

import com.project.clinicaapi.enumerated.Gender;
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
            inverseJoinColumns = @JoinColumn(name = "workday_id"),
            indexes = {@Index(columnList = "dentist_id, workday_id", unique = true)})
    @Setter(AccessLevel.NONE)
    private final List<WorkDay> workDays = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "specialty", nullable = false)
    private Specialty specialty;

    @OneToMany(mappedBy = "dentist")
    private List<Appointment> appointments = new ArrayList<>();

    @Builder(builderMethodName = "dentistBuilder")
    public Dentist(@NonNull String login , @NonNull String email, @NonNull String name, @NonNull String cellphone, String password, @NonNull Gender gender,
                   @NonNull String country, @NonNull String state, @NonNull String city, @NonNull Specialty specialty) {
        super(login, email, name, cellphone, password, gender, country, state, city);
        setRole(Role.DENTIST);
        this.specialty = specialty;
    }

    public void addWorkDay(WorkDay workDay) {
        this.workDays.add(workDay);
    }

}
