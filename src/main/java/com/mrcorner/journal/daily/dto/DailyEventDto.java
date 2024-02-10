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
public class DailyEventDto {
    private int idEvent;
    private int idDay;
    private String iniHour;
    private String endHour;
    private boolean important;
    private String title;
    private String description;
} // DailyEventDto
