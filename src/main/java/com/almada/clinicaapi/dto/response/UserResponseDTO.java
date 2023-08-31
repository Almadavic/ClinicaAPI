package com.almada.clinicaapi.dto.response;


import com.almada.clinicaapi.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;


@JsonPropertyOrder(value = {"id", "login", "email", "name", "cellphone", "gender", "address"})
@Getter
public class UserResponseDTO extends RepresentationModel<UserResponseDTO> {

    @JsonProperty(value = "id")
    private final String id;

    @JsonProperty(value = "login")
    private final String login;

    @JsonProperty(value = "email")
    private final String email;

    @JsonProperty(value = "name")
    private final String name;

    @JsonProperty(value = "cellphone")
    private final String cellphone;

    @JsonProperty(value = "gender")
    private final String gender;

    @JsonProperty(value = "address")
    private final AddressResponseDTO address;

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.login = user.getUsername();
        this.email = user.getEmail();
        this.name = user.getName();
        this.cellphone = user.getCellphone();
        this.gender = user.getGender().toString();
        this.address = new AddressResponseDTO(user.getAddress());
    }

}
