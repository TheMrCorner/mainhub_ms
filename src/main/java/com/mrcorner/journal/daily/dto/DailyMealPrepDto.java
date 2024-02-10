package com.mrcorner.journal.daily.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(doNotUseGetters = true)
public class DailyMealPrepDto {
    private int idMealPrep;
    private int idDay;
    private String midday;
    private String supper;
} // DailyMealPrepDto
