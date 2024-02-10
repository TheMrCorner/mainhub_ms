package com.mrcorner.journal.daily.repository;

import com.mrcorner.journal.daily.model.DailyTarget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DailyTargetRepository extends JpaRepository<DailyTarget, Integer> {

    Optional<DailyTarget> findByIdDay(Integer idDay);

}
