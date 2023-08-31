package com.almada.clinicaapi.dto.response;

import com.almada.clinicaapi.entity.WorkDay;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@JsonPropertyOrder(value = {"index", "workday"})
@EqualsAndHashCode
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
