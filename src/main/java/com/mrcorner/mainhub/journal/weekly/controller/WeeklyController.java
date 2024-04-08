package com.mrcorner.mainhub.journal.weekly.controller;

import com.mrcorner.mainhub.exceptions.DataNotFoundException;
import com.mrcorner.mainhub.exceptions.InvalidDataException;
import com.mrcorner.mainhub.journal.weekly.service.WeeklyService;
import com.mrcorner.mainhub.utils.PathUtils;
import jakarta.validation.Valid;
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
@RequestMapping(PathUtils.API_BASE_PATH + PathUtils.API_VERSION + "/weekly")
@RequiredArgsConstructor
public class WeeklyController {

    private final WeeklyService weeklyService;


    @PreAuthorize("hasAuthority('APP')")
    @GetMapping("/find/preview/{weekDate}")
    public ResponseEntity<String> findWeeklyPreview(@Valid @PathVariable LocalDate weekDate) throws DataNotFoundException, InvalidDataException{
        return weeklyService.findWeeklyPreview(weekDate);
    }

    @PreAuthorize("hasAuthority('APP')")
    @GetMapping("/find/review/{idWeek}")
    public ResponseEntity<String> findWeeklyReview(@PathVariable Integer idWeek) throws DataNotFoundException, InvalidDataException{
        return weeklyService.findWeeklyReview(idWeek);
    }

    @PreAuthorize("hasAuthority('APP')")
    @GetMapping("/find/buyinglist/{idWeek}")
    public ResponseEntity<String> findWeeklyBuyingList(@PathVariable Integer idWeek) throws DataNotFoundException, InvalidDataException{
        return weeklyService.findWeeklyBuyingList(idWeek);
    }

    @PreAuthorize("hasAuthority('APP')")
    @PostMapping("/save/preview")
    public ResponseEntity<String> saveWeeklyPreview(@RequestBody String weeklyPreview) throws InvalidDataException{
        return weeklyService.saveWeeklyPreview(weeklyPreview);
    }
    
    @PreAuthorize("hasAuthority('APP')")
    @PostMapping("/save/review")
    public ResponseEntity<String> saveWeeklyReview(@RequestBody String weeklyReview) {
        return weeklyService.saveWeeklyReview(weeklyReview);
    }

    @PreAuthorize("hasAuthority('APP')")
    @PostMapping("/save/buyinglist")
    public ResponseEntity<String> saveWeeklyBuyingList(@RequestBody String weeklyBuyingList) {
        return weeklyService.saveWeeklyBuyingList(weeklyBuyingList);
    }
}
