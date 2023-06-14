package com.project.clinicaapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.project.clinicaapi.entity.User;
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
        this.role = user.getAuthorities().toString();
        this.enabled = user.isEnabled();
    }

}
