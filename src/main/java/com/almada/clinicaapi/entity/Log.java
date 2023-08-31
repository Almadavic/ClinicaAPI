package com.almada.clinicaapi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_logs")
@NoArgsConstructor
@Getter
@Setter
public class Log implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "event_time", nullable = false)
    @CreationTimestamp
    private LocalDateTime eventTime;

    @Column(name = "user_name", nullable = false)
    private String user;

    @Column(name = "event", nullable = false)
    private String event;

    @Builder
    public Log(String user, String event) {
        this.user = user;
        this.event = event;
    }

}
