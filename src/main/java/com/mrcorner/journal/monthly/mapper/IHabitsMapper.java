package com.mrcorner.journal.monthly.mapper;

import com.mrcorner.journal.daily.dto.DailyPreviewDto;
import com.mrcorner.journal.daily.model.DailyPreview;
import com.mrcorner.journal.monthly.dto.HabitDto;
import com.mrcorner.journal.monthly.model.Habits;
import com.mrcorner.journal.utils.IEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IHabitsMapper extends IEntityMapper<HabitDto, Habits> {

    @Mapping(target = "dbInsDate", ignore = true)
    @Mapping(target = "dbDelDate", ignore = true)
    @Mapping(target = "dbModDate", ignore = true)
    Habits toEntity(HabitDto dto);

    HabitDto toDto (Habits entity);

    List<Habits> toEntityList(List<HabitDto> dtos);

    List<HabitDto> toDtoList(List<Habits> entities);
}
