package com.mrcorner.journal.daily.mapper;


import com.mrcorner.journal.daily.dto.DailyPreviewDto;
import com.mrcorner.journal.daily.dto.DailyReviewDto;
import com.mrcorner.journal.daily.model.DailyPreview;
import com.mrcorner.journal.daily.model.DailyReview;
import com.mrcorner.journal.utils.HabitStatus;
import com.mrcorner.journal.utils.IEntityMapper;
import com.mrcorner.journal.utils.MoodEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IDailyReviewMapper extends IEntityMapper<DailyReviewDto, DailyReview> {


    @Mapping(target = "dbInsDate", ignore = true)
    @Mapping(target = "dbDelDate", ignore = true)
    @Mapping(target = "dbModDate", ignore = true)
    @Mapping(target = "mood", source = "mood", qualifiedByName = "toShortInt")
    DailyReview toEntity(DailyReviewDto dto);


    @Mapping(target = "dailyHabits", ignore = true)
    @Mapping(target = "mood", source = "mood", qualifiedByName = "toMoodStatus")
    DailyReviewDto toDto (DailyReview entity);

    List<DailyReview> toEntityList(List<DailyReviewDto> dtos);

    List<DailyReviewDto> toDtoList(List<DailyReview> entities);

    @Named("toShortInt")
    default short toShortInt(MoodEnum value){
        return (short) value.ordinal();
    } // toShortInt

    @Named("toMoodStatus")
    default MoodEnum toMoodStatus(short value){
        return MoodEnum.values()[value];
    } // toHabitStatus

}
