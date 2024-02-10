package com.mrcorner.journal.quarter.model;

import com.vladmihalcea.hibernate.type.json.JsonType;
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
import org.hibernate.annotations.Type;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "quarter_preview")
public class QuarterPreview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_quarter")
    private int idQuarter;

    private String expectation;

    @Type(JsonType.class)
    @Column(name = "to_do", columnDefinition = "jsonb")
    private List<ToDo> toDoList;

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
}
