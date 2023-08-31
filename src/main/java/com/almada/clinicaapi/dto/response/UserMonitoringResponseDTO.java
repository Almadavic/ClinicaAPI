package com.almada.clinicaapi.dto.response;

import com.almada.clinicaapi.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@JsonPropertyOrder(value = {"id", "login", "email", "name", "cellphone", "enabled", "gender", "role", "address"})
@Getter
public class UserMonitoringResponseDTO extends UserResponseDTO{

    @JsonProperty(value = "role")
    private final String role;

    @JsonProperty(value = "enabled")
    private final boolean enabled;

    public UserMonitoringResponseDTO(User user) {
        super(user);
        this.role = user.getRole().toString();
        this.enabled = user.isEnabled();
    }

}
