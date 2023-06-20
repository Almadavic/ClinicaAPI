package com.project.clinicaapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.project.clinicaapi.entity.WorkDay;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@JsonPropertyOrder(value = {"id", "workday"})
@EqualsAndHashCode(of = "id")
public class WorkDayResponseDTO {

    @JsonProperty(value = "id")
    private final Long id;

    @JsonProperty(value = "workday")
    private final String workDay;

    public WorkDayResponseDTO(WorkDay workDay) {
        this.id = workDay.getId();
        this.workDay = workDay.getWorkDay().toString();
    }

}
