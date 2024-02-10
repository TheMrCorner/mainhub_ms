package com.mrcorner.journal.daily.mapper;

import com.mrcorner.journal.daily.dto.DailyReviewDto;
import com.mrcorner.journal.daily.dto.DailyTargetDto;
import com.mrcorner.journal.daily.model.DailyReview;
import com.mrcorner.journal.daily.model.DailyTarget;
import com.mrcorner.journal.utils.IEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IDailyTargetMapper extends IEntityMapper<DailyTargetDto, DailyTarget> {

    @Mapping(target = "dbInsDate", ignore = true)
    @Mapping(target = "dbDelDate", ignore = true)
    @Mapping(target = "dbModDate", ignore = true)
    DailyTarget toEntity(DailyTargetDto dto);

    DailyTargetDto toDto (DailyTarget entity);

    List<DailyTarget> toEntityList(List<DailyTargetDto> dtos);

    List<DailyTargetDto> toDtoList(List<DailyTarget> entities);
}
