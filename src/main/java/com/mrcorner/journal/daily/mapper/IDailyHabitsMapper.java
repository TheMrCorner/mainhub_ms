package com.mrcorner.journal.daily.mapper;


import com.mrcorner.journal.daily.dto.DailyHabitsDto;
import com.mrcorner.journal.daily.dto.DailyPreviewDto;
import com.mrcorner.journal.daily.model.DailyHabit;
import com.mrcorner.journal.daily.model.DailyPreview;
import com.mrcorner.journal.utils.HabitStatus;
import com.mrcorner.journal.utils.IEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IDailyHabitsMapper extends IEntityMapper<DailyHabitsDto, DailyHabit> {

    @Mapping(target = "dbInsDate", ignore = true)
    @Mapping(target = "dbDelDate", ignore = true)
    @Mapping(target = "dbModDate", ignore = true)
    @Mapping(target = "status", source = "status", qualifiedByName = "toShortInt")
    DailyHabit toEntity(DailyHabitsDto dto);

    @Mapping(target = "habit", ignore = true)
    @Mapping(target = "status", source = "status", qualifiedByName = "toHabitStatus")
    DailyHabitsDto toDto (DailyHabit entity);

    List<DailyHabit> toEntityList(List<DailyHabitsDto> dtos);

    List<DailyHabitsDto> toDtoList(List<DailyHabit> entities);

    @Named("toShortInt")
    default short toShortInt(HabitStatus value){
        return (short) value.ordinal();
    } // toShortInt

    @Named("toHabitStatus")
    default HabitStatus toHabitStatus(short value){
        return HabitStatus.values()[value];
    } // toHabitStatus

} // IDailyHabitsMapper
