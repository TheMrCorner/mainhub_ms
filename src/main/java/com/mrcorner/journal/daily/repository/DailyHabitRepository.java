package com.mrcorner.journal.daily.repository;

import com.mrcorner.journal.daily.model.DailyHabit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyHabitRepository extends JpaRepository<DailyHabit, Integer> {
}
