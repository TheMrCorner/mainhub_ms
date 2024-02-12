package com.mrcorner.journal.daily.mapper;

import com.mrcorner.journal.daily.dto.DailyReviewDto;
import com.mrcorner.journal.daily.dto.DailyTargetDto;
import com.mrcorner.journal.daily.model.DailyReview;
import com.mrcorner.journal.daily.model.DailyTarget;
import com.mrcorner.journal.utils.IEntityMapper;
import com.mrcorner.journal.utils.MoodEnum;
import com.mrcorner.journal.utils.TargetDurationEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IDailyTargetMapper extends IEntityMapper<DailyTargetDto, DailyTarget> {


    @Mapping(target = "dbInsDate", ignore = true)
    @Mapping(target = "dbDelDate", ignore = true)
    @Mapping(target = "dbModDate", ignore = true)
    @Mapping(target = "ftDuration", source = "ftDuration", qualifiedByName = "toShortInt")
    @Mapping(target = "stDuration", source = "stDuration", qualifiedByName = "toShortInt")
    @Mapping(target = "ttDuration", source = "ttDuration", qualifiedByName = "toShortInt")
    DailyTarget toEntity(DailyTargetDto dto);

    @Mapping(target = "ftDuration", source = "ftDuration", qualifiedByName = "toDuration")
    @Mapping(target = "stDuration", source = "stDuration", qualifiedByName = "toDuration")
    @Mapping(target = "ttDuration", source = "ttDuration", qualifiedByName = "toDuration")
    DailyTargetDto toDto (DailyTarget entity);

    List<DailyTarget> toEntityList(List<DailyTargetDto> dtos);

    List<DailyTargetDto> toDtoList(List<DailyTarget> entities);

    @Named("toShortInt")
    default short toShortInt(TargetDurationEnum value){
        return (short) value.ordinal();
    } // toShortInt

    @Named("toDuration")
    default TargetDurationEnum toMoodStatus(short value){
        return TargetDurationEnum.values()[value];
    } // toHabitStatus

}
