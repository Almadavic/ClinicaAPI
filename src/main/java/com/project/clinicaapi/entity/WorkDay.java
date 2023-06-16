package com.project.clinicaapi.entity;

import com.project.clinicaapi.enumerated.WorkDayEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "tb_workdays")
@NoArgsConstructor
@Getter
@Setter
public class WorkDay implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "work_day", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private WorkDayEnum workDay;

    @OneToMany(mappedBy = "weekDay")
    private final List<Appointment> appointments = new ArrayList<>();

    @ManyToMany(mappedBy = "workDays")
    private final List<Dentist> dentists = new ArrayList<>();

    public WorkDay(WorkDayEnum workDay) {
        this.workDay = workDay;
    }

}
