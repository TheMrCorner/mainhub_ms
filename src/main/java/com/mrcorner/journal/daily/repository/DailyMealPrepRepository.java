package com.mrcorner.journal.daily.repository;

import com.mrcorner.journal.daily.model.DailyMealPrep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DailyMealPrepRepository extends JpaRepository<DailyMealPrep, Integer> {

    Optional<DailyMealPrep> findByIdDay(Integer idDay);

}
