package com.mrcorner.journal.quarter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDo implements Serializable {
    private String task;
    private Boolean done;
}
