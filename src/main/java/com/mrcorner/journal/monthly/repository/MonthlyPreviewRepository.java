package com.mrcorner.journal.monthly.repository;

import com.mrcorner.journal.monthly.model.MonthlyPreview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface MonthlyPreviewRepository extends JpaRepository<MonthlyPreview, Integer> {

    @Query(value = "SELECT id_month FROM monthly_preview WHERE start_date <= :dayDate AND end_date >= :dayDate", nativeQuery = true)
    Integer findIdMonthByDayDate(@Param("dayDate") LocalDate dayDate);

}
