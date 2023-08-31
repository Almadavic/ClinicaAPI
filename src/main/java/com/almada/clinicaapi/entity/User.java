package com.almada.clinicaapi.entity;

import com.almada.clinicaapi.enumerated.Gender;
import com.almada.clinicaapi.enumerated.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "tb_users")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    @Column(name = "id")
    private final String id = UUID.randomUUID().toString();

    @Column(name = "login", nullable = false, unique = true)
    @Getter(AccessLevel.NONE)
    private String login;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cellphone", length = 16, unique = true)
    private String cellphone;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "user")
    private EnableAccount enableAccount;

    @OneToOne(mappedBy = "user")
    private ChangePassword changePassword;

    @Embedded
    private Address address;

    @Builder
    public User(@NonNull String login, @NonNull String email, @NonNull String name, String cellphone, String password,
                @NonNull Gender gender, @NonNull String country, @NonNull String state, @NonNull String city) {
        this.login = login;
        this.email = email;
        this.name = name;
        this.cellphone = cellphone;
        this.password = password;
        this.gender = gender;
        this.address = new Address(country, state, city);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
