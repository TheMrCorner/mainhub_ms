package com.mrcorner.journal.daily.mapper;


import com.mrcorner.journal.daily.dto.DailyHabitsDto;
import com.mrcorner.journal.daily.dto.DailyMealPrepDto;
import com.mrcorner.journal.daily.model.DailyHabit;
import com.mrcorner.journal.daily.model.DailyMealPrep;
import com.mrcorner.journal.utils.IEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IDailyMealPrepMapper extends IEntityMapper<DailyMealPrepDto, DailyMealPrep> {

    @Mapping(target = "dbDelDate", ignore = true)
    @Mapping(target = "dbInsDate", ignore = true)
    @Mapping(target = "dbModDate", ignore = true)
    DailyMealPrep toEntity(DailyMealPrepDto dto);

    DailyMealPrepDto toDto (DailyMealPrep entity);

    List<DailyMealPrep> toEntityList(List<DailyMealPrepDto> dtos);

    List<DailyMealPrepDto> toDtoList(List<DailyMealPrep> entities);
}
