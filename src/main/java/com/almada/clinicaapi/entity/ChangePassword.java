package com.almada.clinicaapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name = "tb_change_password")
@Getter
@Setter
public class ChangePassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @Column(name = "code", length = 20, nullable = false, unique = true)
    private String code;

    public ChangePassword(User user, String code) {
        this.user = user;
        this.code = code;
    }

}
