package com.mrcorner.mainhub.journal.daily.controller;

import com.mrcorner.mainhub.journal.daily.service.DailyService;
import com.mrcorner.mainhub.utils.PathUtils;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping(PathUtils.API_BASE_PATH + PathUtils.API_VERSION + "/daily")
@RequiredArgsConstructor
public class DailyController {

    private final DailyService dailyService;


    @PreAuthorize("hasAuthority('APP')")
    @GetMapping("/find/{dayDate}")
    public ResponseEntity<String> findDailyPreview(@PathVariable LocalDate dayDate){
        return dailyService.findDailyPreview(dayDate);
    }

    @PreAuthorize("hasAuthority('APP')")
    @GetMapping("/find/review")
    public ResponseEntity<String> findDailyReview(@RequestParam Integer idDay){
        return dailyService.findDailyReview(idDay);
    }

    @PreAuthorize("hasAuthority('APP')")
    @GetMapping("/find/events/{idDay}")
    public ResponseEntity<String> findDailyEvents(@PathVariable Integer idDay){
        return dailyService.findDailyEvents(idDay);
    }

    @PreAuthorize("hasAuthority('APP')")
    @PostMapping("/save/preview")
    public ResponseEntity<String> saveDailyPreview(@RequestBody String dailyPreview){
        return dailyService.saveDailyPreview(dailyPreview);
    }

    @PreAuthorize("hasAuthority('APP')")
    @PostMapping("/save/event")
    public ResponseEntity<String> saveEvent(@RequestBody String dailyEvent){
        return dailyService.saveEvent(dailyEvent);
    }

    @PreAuthorize("hasAuthority('APP')")
    @PostMapping("/save/review")
    public ResponseEntity<String> saveDailyReview(@RequestBody String dailyReview){
        return dailyService.saveDailyReview(dailyReview);
    }

}
