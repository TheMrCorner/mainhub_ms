package com.mrcorner.journal.daily.repository;

import com.mrcorner.journal.daily.model.DailyReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyReviewRepository extends JpaRepository<DailyReview, Integer> {
}
