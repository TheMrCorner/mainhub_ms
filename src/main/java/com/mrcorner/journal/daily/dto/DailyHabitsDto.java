package com.mrcorner.journal.daily.dto;

import com.mrcorner.journal.monthly.dto.HabitDto;
import com.mrcorner.journal.utils.HabitStatus;
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
public class DailyHabitsDto {
    private int idDailyHabit;
    private int idHabit;
    private int idDay;
    private HabitDto habit;
    private HabitStatus status;
}
