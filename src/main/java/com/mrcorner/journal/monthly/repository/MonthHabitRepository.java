package com.mrcorner.journal.monthly.repository;

import com.mrcorner.journal.monthly.model.MonthHabit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthHabitRepository extends JpaRepository<MonthHabit, Integer> {

    @Query(value = "SELECT id_habits FROM month_habit WHERE id_month = :idMonth", nativeQuery = true)
    List<Integer> findAllIdHabitsByIdMonth(Integer idMonth);

}
