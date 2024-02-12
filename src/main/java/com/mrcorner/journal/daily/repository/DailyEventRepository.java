package com.mrcorner.journal.daily.repository;

import com.mrcorner.journal.daily.model.DailyEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyEventRepository extends JpaRepository<DailyEvent, Integer> {

    List<DailyEvent> findAllByIdDay(Integer idDay);

}
