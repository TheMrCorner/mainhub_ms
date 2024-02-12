package com.mrcorner.journal.daily.model;

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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "daily_review")
public class DailyReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_daily_review")
    private int idDailyReview;

    @Column(name = "id_day")
    private int idDay;

    private String notes;

    @Column(name = "plan_reality")
    private short planToReality;

    @Column(name = "win_day")
    private short winDay;

    private short mood;

    @Column(name = "ft_complete")
    private boolean ftComplete;

    @Column(name = "st_complete")
    private boolean stComplete;

    @Column(name = "tt_complete")
    private boolean ttComplete;

    @Column(name = "db_del_date")
    private Timestamp dbDelDate;

    @Column(name = "db_ins_date")
    private Timestamp dbInsDate;

    @Column(name = "db_mod_date")
    private Timestamp dbModDate;
} // DailyReview
