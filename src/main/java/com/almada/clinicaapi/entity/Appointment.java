package com.almada.clinicaapi.entity;

import com.almada.clinicaapi.enumerated.WorkDayEnum;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "tb_appointments")
@Getter
@Setter
public class Appointment implements Serializable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private String id = UUID.randomUUID().toString();

    @Column(name = "procedure", nullable = false)
    private String procedure;

    @Column(name = "appointment_date", nullable = false)
    private LocalDate appointmentDate;

    @Column(name = "time_start", nullable = false)
    private LocalTime timeStart;

    @Column(name = "time_end", nullable = false)
    private LocalTime timeEnd;

    @Column(name = "week_day", nullable = false)
    @Enumerated(EnumType.STRING)
    private WorkDayEnum weekDay;

    @ManyToOne
    @JoinColumn(name = "dentist_id", foreignKey = @ForeignKey(name = "fk_appointment_dentist"), nullable = false)
    private Dentist dentist;

    @ManyToOne
    @JoinColumn(name = "patient_id", foreignKey = @ForeignKey(name = "fk_appointment_patient"), nullable = false)
    private Patient patient;

    @Builder
    public Appointment(@NonNull String procedure, @NonNull LocalDate appointmentDate, @NonNull LocalTime timeStart, @NonNull LocalTime timeEnd,
                       Dentist dentist, Patient patient) {

        this.procedure = procedure;
        this.appointmentDate = appointmentDate;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.dentist = dentist;
        this.patient = patient;
    }

}
