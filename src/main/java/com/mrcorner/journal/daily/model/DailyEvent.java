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
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "daily_events")
public class DailyEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_event")
    private int idEvent;

    @Column(name = "id_day")
    private int idDay;

    @Column(name = "ini_hour")
    private LocalTime iniHour;

    @Column(name = "end_hour")
    private LocalTime endHour;


    private boolean important;
    private String title;
    private String description;

    @Column(name = "db_del_date")
    private Timestamp dbDelDate;

    @Column(name = "db_ins_date")
    private Timestamp dbInsDate;

    @Column(name = "db_mod_date")
    private Timestamp dbModDate;
} // DailyEvent
