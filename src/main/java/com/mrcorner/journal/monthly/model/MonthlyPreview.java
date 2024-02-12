package com.mrcorner.journal.monthly.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "monthly_preview")
public class MonthlyPreview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_month")
    private int idMonth;

    private String expectation;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "db_del_date")
    private Timestamp dbDelDate;

    @Column(name = "db_ins_date")
    private Timestamp dbInsDate;

    @Column(name = "db_mod_date")
    private Timestamp dbModDate;

} // MonthlyPreview
