package com.mrcorner.journal.monthly.controller;

import com.mrcorner.journal.exceptions.DataNotFoundException;
import com.mrcorner.journal.exceptions.InvalidDataException;
import com.mrcorner.journal.monthly.dto.HabitDto;
import com.mrcorner.journal.monthly.service.HabitService;
import com.mrcorner.journal.utils.PathUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(PathUtils.API_BASE_PATH + PathUtils.API_VERSION + "/habit")
@AllArgsConstructor
public class HabitController {

    HabitService habitService;

    @PostMapping("/new")
    @PreAuthorize("hasAnyAuthority('APP', 'ADMIN')")
    public HabitDto newHabit(@RequestBody HabitDto habitDto) {
        return habitService.newHabit(habitDto);
    } // newHabit

    @GetMapping("/find/{dayDate}")
    @PreAuthorize("hasAnyAuthority('APP', 'ADMIN')")
    public List<HabitDto> findHabitsByDay(@Valid @PathVariable LocalDate dayDate) throws DataNotFoundException, InvalidDataException {
        return habitService.findHabitsByDay(dayDate);
    } // findHabitsByDay

} // HabitController
