package com.mrcorner.journal.daily.dto;

import com.mrcorner.journal.utils.TargetDurationEnum;
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
public class DailyTargetDto {
    private int idTarget;
    private int idDay;
    private String firstTarget;
    private TargetDurationEnum ftDuration;
    private String secondTarget;
    private TargetDurationEnum stDuration;
    private String thirdTarget;
    private TargetDurationEnum ttDuration;
} // DailyTargetDto