package com.mrcorner.journal.daily.dto;

import com.mrcorner.journal.utils.MoodEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(doNotUseGetters = true)
public class DailyReviewDto {
    private int idDailyReview;
    private int idDay;
    private String notes;
    private short planToReality;
    private short winDay;
    private MoodEnum mood;
    private boolean ftComplete;
    private boolean stComplete;
    private boolean ttComplete;
    private List<DailyHabitsDto> dailyHabits;
} // DailyReviewDto
