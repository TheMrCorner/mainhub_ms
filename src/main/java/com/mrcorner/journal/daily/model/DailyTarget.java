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
@Table(name = "daily_targets")
public class DailyTarget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_target")
    private int idTarget;

    @Column(name = "id_day")
    private int idDay;

    @Column(name = "first_target")
    private String firstTarget;
    @Column(name = "ft_duration")
    private String ftDuration;

    @Column(name = "second_target")
    private String secondTarget;
    @Column(name = "st_duration")
    private String stDuration;

    @Column(name = "third_target")
    private String thirdTarget;
    @Column(name = "tt_duration")
    private String ttDuration;

    @Column(name = "db_del_date")
    private Timestamp dbDelDate;

    @Column(name = "db_ins_date")
    private Timestamp dbInsDate;

    @Column(name = "db_mod_date")
    private Timestamp dbModDate;
} // DailyTarget
