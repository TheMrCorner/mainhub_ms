package com.mrcorner.journal.daily.repository;

import com.mrcorner.journal.daily.model.DailyHabit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyHabitRepository extends JpaRepository<DailyHabit, Integer> {

    List<DailyHabit> findAllByIdDay(int idDay);

} // DailyHabitRepository
