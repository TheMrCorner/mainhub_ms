package com.mrcorner.journal.anual.repository;

import com.mrcorner.journal.anual.model.AnualPreview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnualPreviewRepository extends JpaRepository<AnualPreview, Integer> {
}
