package com.mrcorner.journal.monthly.repository;

import com.mrcorner.journal.monthly.model.MonthlyPreview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthlyPreviewRepository extends JpaRepository<MonthlyPreview, Integer> {
}
