package com.mrcorner.journal.daily.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(doNotUseGetters = true)
public class DailyEventDto {
    private int idEvent;
    private int idDay;
    private LocalTime iniHour;
    private LocalTime endHour;
    private boolean important;
    private String title;
    private String description;
} // DailyEventDto
