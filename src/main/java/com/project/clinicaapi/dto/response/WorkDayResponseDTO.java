package com.project.clinicaapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.project.clinicaapi.entity.WorkDay;
import lombok.Getter;

@Getter
@JsonPropertyOrder(value = {"id", "workday"})
public class WorkDayResponseDTO {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "workday")
    private String workDay;

    public WorkDayResponseDTO(WorkDay workDay) {
        this.id = workDay.getId();
        this.workDay = workDay.getWorkDay().toString();
    }

}
