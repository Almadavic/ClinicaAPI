package com.almada.clinicaapi.entity;

import com.almada.clinicaapi.enumerated.WorkDayEnum;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "index", nullable = false, unique = true)
    private Integer index;

    @OneToMany(mappedBy = "weekDay")
    private final List<Appointment> appointments = new ArrayList<>();

    @ManyToMany(mappedBy = "workDays")
    private final List<Dentist> dentists = new ArrayList<>();

    @Builder
    public WorkDay(WorkDayEnum workDay) {
        this.workDay = workDay;
        this.index = workDay.getIndex();
    }

}
