package com.almada.clinicaapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@Table(name = "tb_enable_account")
@Getter
@Setter
public class EnableAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @Column(name = "code", length = 20, nullable = false, unique = true)
    private String code;

    public EnableAccount(User user, String code) {
        this.user = user;
        this.code = code;
    }

}
