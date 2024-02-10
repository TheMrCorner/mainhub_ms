package com.mrcorner.journal.monthly.repository;

import com.mrcorner.journal.monthly.model.Habits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHabitsRepository extends JpaRepository<Habits, Integer> {
}
