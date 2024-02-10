package com.mrcorner.journal.daily.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(doNotUseGetters = true)
public class DailyPreviewDto {
    private int idDay;
    private LocalDate currentDay;
    private String gratefulData;
    private String goal;
    private String greatData;
    private DailyTargetDto dailyTargets;
    private List<DailyEventDto> dailyEvents;
    private DailyMealPrepDto mealPrep;
} // DailyPreviewDto
