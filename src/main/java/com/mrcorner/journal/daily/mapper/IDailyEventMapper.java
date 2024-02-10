package com.mrcorner.journal.daily.mapper;


import com.mrcorner.journal.daily.dto.DailyEventDto;
import com.mrcorner.journal.daily.model.DailyEvent;
import com.mrcorner.journal.utils.IEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IDailyEventMapper extends IEntityMapper<DailyEventDto, DailyEvent> {

    @Mapping(target = "dbInsDate", ignore = true)
    @Mapping(target = "dbDelDate", ignore = true)
    @Mapping(target = "dbModDate", ignore = true)
    DailyEvent toEntity(DailyEventDto dto);

    DailyEventDto toDto (DailyEvent entity);

    List<DailyEvent> toEntityList(List<DailyEventDto> dtos);

    List<DailyEventDto> toDtoList(List<DailyEvent> entities);

}
