package com.project.clinicaapi.entity;

import com.project.clinicaapi.enumerated.Role;
import com.project.clinicaapi.enumerated.Situation;
import com.project.clinicaapi.vo.Address;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tb_users")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id", nullable = false, unique = true)
    private final String id = UUID.randomUUID().toString();

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cellphone", length = 16)
    private String cellphone;

    @Column(name = "password")
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "situation", nullable = false)
    @Enumerated(EnumType.STRING)
    private Situation situation;

    @Embedded
    private Address address = new Address();

}
