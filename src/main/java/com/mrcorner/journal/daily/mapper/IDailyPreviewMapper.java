package com.mrcorner.journal.daily.mapper;


import com.mrcorner.journal.daily.dto.DailyMealPrepDto;
import com.mrcorner.journal.daily.dto.DailyPreviewDto;
import com.mrcorner.journal.daily.model.DailyMealPrep;
import com.mrcorner.journal.daily.model.DailyPreview;
import com.mrcorner.journal.utils.IEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IDailyPreviewMapper extends IEntityMapper<DailyPreviewDto, DailyPreview> {

    @Mapping(target = "dbInsDate", ignore = true)
    @Mapping(target = "dbDelDate", ignore = true)
    @Mapping(target = "dbModDate", ignore = true)
    DailyPreview toEntity(DailyPreviewDto dto);

    @Mapping(target = "dailyTargets", ignore = true)
    @Mapping(target = "dailyEvents", ignore = true)
    @Mapping(target = "mealPrep", ignore = true)
    DailyPreviewDto toDto (DailyPreview entity);

    List<DailyPreview> toEntityList(List<DailyPreviewDto> dtos);

    List<DailyPreviewDto> toDtoList(List<DailyPreview> entities);
}
