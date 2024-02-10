package com.mrcorner.journal.weekly.repository;

import com.mrcorner.journal.weekly.model.WeeklyPreview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeeklyPreviewRepository extends JpaRepository<WeeklyPreview, Integer> {
}
