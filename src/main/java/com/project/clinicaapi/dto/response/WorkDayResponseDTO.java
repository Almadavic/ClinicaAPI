package com.project.clinicaapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.project.clinicaapi.entity.WorkDay;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@JsonPropertyOrder(value = {"index", "workday"})
@EqualsAndHashCode(of = "index")
public class WorkDayResponseDTO {

    @JsonProperty(value = "index")
    private final Integer index;

    @JsonProperty(value = "workday")
    private final String workDay;

    public WorkDayResponseDTO(WorkDay workDay) {
        this.index = workDay.getIndex();
        this.workDay = workDay.getWorkDay().toString();
    }

}
