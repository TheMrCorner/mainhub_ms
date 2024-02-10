package com.mrcorner.journal.monthly.dto;

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
public class HabitDto {

    private int idHabit;
    private String title;
    private String description;

} // HabitDto

