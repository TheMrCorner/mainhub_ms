package com.mrcorner.journal.weekly.model;

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
@Table(name = "quarter_preview")
public class WeeklyPreview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_week")
    private int idWeek;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "first_goal")
    private String firstGoal;
    @Column(name = "fg_duration")
    private String fgDuration;
    @Column(name = "fg_comment")
    private String fgComment;

    @Column(name = "second_goal")
    private String secondGoal;
    @Column(name = "sg_duration")
    private String sgDuration;
    @Column(name = "sg_comment")
    private String sgComment;

    @Column(name = "third_goal")
    private String thirdGoal;
    @Column(name = "tg_duration")
    private String tgDuration;
    @Column(name = "tg_comment")
    private String tgComment;

    @Column(name = "db_del_date")
    private Timestamp dbDelDate;

    @Column(name = "db_ins_date")
    private Timestamp dbInsDate;

    @Column(name = "db_mod_date")
    private Timestamp dbModDate;
} // WeeklyPreview
