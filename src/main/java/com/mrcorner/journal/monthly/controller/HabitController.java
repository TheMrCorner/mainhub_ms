package com.mrcorner.journal.monthly.controller;

import com.mrcorner.journal.monthly.dto.HabitDto;
import com.mrcorner.journal.monthly.service.HabitService;
import com.mrcorner.journal.utils.PathUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

} // HabitController
