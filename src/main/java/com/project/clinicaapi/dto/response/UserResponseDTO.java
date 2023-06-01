package com.project.clinicaapi.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.project.clinicaapi.entity.User;
import lombok.Getter;


@JsonPropertyOrder(value = {"id", "login", "email", "name", "cellphone", "enabled", "gender", "role", "address"})
@Getter
public class UserResponseDTO {

    @JsonProperty(value = "id")
    private final String id;

    @JsonProperty(value = "login")
    private final String login;

    @JsonProperty(value = "email")
    private final String email;

    @JsonProperty(value = "name")
    private final String name;

    @JsonProperty(value = "callphone")
    private final String cellphone;

    @JsonProperty(value = "enabled")
    private final boolean enabled;

    @JsonProperty(value = "gender")
    private final String gender;

    @JsonProperty(value = "role")
    private final String role;

    @JsonProperty(value = "address")
    private final AddressResponseDTO address;

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.login = user.getUsername();
        this.email = user.getEmail();
        this.name = user.getName();
        this.cellphone = user.getCellphone();
        this.enabled = user.isEnabled();
        this.gender = user.getGender().toString();
        this.role = user.getAuthorities().toString();
        this.address = new AddressResponseDTO(user.getAddress());
    }

}
