package com.mrcorner.mainhub.journal.daily.controller;

import com.mrcorner.mainhub.exceptions.DataNotFoundException;
import com.mrcorner.mainhub.exceptions.InvalidDataException;
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
    public ResponseEntity<String> findDailyPreview(@PathVariable LocalDate dayDate) throws InvalidDataException, DataNotFoundException {
        return dailyService.findDailyPreview(dayDate);
    }

    @PreAuthorize("hasAuthority('APP')")
    @GetMapping("/find/review")
    public ResponseEntity<String> findDailyReview(@RequestParam Integer idDay) throws InvalidDataException, DataNotFoundException {
        return dailyService.findDailyReview(idDay);
    }

    @PreAuthorize("hasAuthority('APP')")
    @GetMapping("/find/events/{idDay}")
    public ResponseEntity<String> findDailyEvents(@PathVariable Integer idDay) throws InvalidDataException, DataNotFoundException {
        return dailyService.findDailyEvents(idDay);
    }

    @PreAuthorize("hasAuthority('APP')")
    @GetMapping("/find/events/betweendates")
    public ResponseEntity<String> findAllEventsBetweenDates(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate, @RequestParam(required = false) Boolean important) throws InvalidDataException {
        return dailyService.findAllDailyEventsBetweenDates(startDate, endDate, important);
    }
    

    @PreAuthorize("hasAuthority('APP')")
    @PostMapping("/save/preview")
    public ResponseEntity<String> saveDailyPreview(@RequestBody String dailyPreview) throws InvalidDataException{
        return dailyService.saveDailyPreview(dailyPreview);
    }

    @PreAuthorize("hasAuthority('APP')")
    @PostMapping("/save/event")
    public ResponseEntity<String> saveEvent(@RequestBody String dailyEvent) throws InvalidDataException{
        return dailyService.saveEvent(dailyEvent);
    }

    @PreAuthorize("hasAuthority('APP')")
    @PostMapping("/save/review")
    public ResponseEntity<String> saveDailyReview(@RequestBody String dailyReview) throws InvalidDataException{
        return dailyService.saveDailyReview(dailyReview);
    }

}
