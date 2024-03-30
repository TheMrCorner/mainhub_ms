package com.mrcorner.mainhub.journal.habit.controller;

import com.mrcorner.mainhub.journal.habit.service.HabitService;
import com.mrcorner.mainhub.utils.PathUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping(PathUtils.API_BASE_PATH + PathUtils.API_VERSION + "/habit")
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    @PreAuthorize("hasAuthority('APP')")
    @GetMapping("/find/{dayDate}")
    public ResponseEntity<String> findHabitsByDay(@PathVariable LocalDate dayDate){
        return habitService.findHabitsByDay(dayDate);
    }

    @PreAuthorize("hasAuthority('APP')")
    @PostMapping("/new")
    public ResponseEntity<String> saveNewHabit(@RequestBody String newHabit){
        return habitService.saveNewHabit(newHabit);
    }
}
