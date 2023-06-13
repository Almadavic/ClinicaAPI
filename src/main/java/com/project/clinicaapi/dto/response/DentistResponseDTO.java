package com.project.clinicaapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.project.clinicaapi.entity.Dentist;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.entity.WorkDay;
import lombok.Getter;

import java.util.List;

@Getter
@JsonPropertyOrder(value = {"id", "login", "email", "name", "cellphone", "enabled", "gender", "cro", "speciality", "workdays", "role", "address"})
public class DentistResponseDTO extends UserResponseDTO{

    @JsonProperty(value = "cro")
    private final String cro;

    @JsonProperty(value = "speciality")
    private final String speciality;

    @JsonProperty(value = "workdays")
    private final List<WorkDayResponseDTO> workDays;

    public DentistResponseDTO(User user) {
        super(user);
        Dentist dentist = (Dentist) user;
        this.cro = dentist.getCro();
        this.speciality = dentist.getSpecialty().toString();
        workDays = convertList(dentist.getWorkDays());
    }

    private List<WorkDayResponseDTO> convertList (List<WorkDay>workDaysList) {
        return workDaysList.stream().map(WorkDayResponseDTO::new).toList();
    }

}
