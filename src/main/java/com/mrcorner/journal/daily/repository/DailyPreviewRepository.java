package com.mrcorner.journal.daily.repository;

import com.mrcorner.journal.daily.model.DailyPreview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DailyPreviewRepository extends JpaRepository<DailyPreview, Integer> {

    Optional<DailyPreview> findDailyPreviewByCurrentDay(LocalDate currentDay);

}
