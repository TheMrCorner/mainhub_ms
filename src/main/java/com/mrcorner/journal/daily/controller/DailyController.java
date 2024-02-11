package com.mrcorner.journal.daily.controller;

import com.mrcorner.journal.daily.dto.DailyEventDto;
import com.mrcorner.journal.daily.dto.DailyPreviewDto;
import com.mrcorner.journal.daily.dto.DailyReviewDto;
import com.mrcorner.journal.daily.service.DailyService;
import com.mrcorner.journal.exceptions.DataNotFoundException;
import com.mrcorner.journal.exceptions.InvalidDataException;
import com.mrcorner.journal.utils.PathUtils;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class DailyController {

    DailyService dailyService;

    @GetMapping("/find/{dayDate}")
    @PreAuthorize("hasAnyAuthority('APP', 'ADMIN')")
    public ResponseEntity<DailyPreviewDto> findDailyPreview(@Valid @PathVariable LocalDate dayDate) throws InvalidDataException, DataNotFoundException{
        return ResponseEntity.ok(dailyService.findDailyPreview(dayDate));
    } // findDailyPreview

    @GetMapping("/find/review")
    @PreAuthorize("hasAnyAuthority('APP', 'ADMIN')")
    public ResponseEntity<DailyReviewDto> findDailyReview(@RequestParam Integer idDay) throws InvalidDataException, DataNotFoundException{
        return ResponseEntity.ok(dailyService.findDailyReview(idDay));
    } // findDailyPreview

    @PostMapping("/new/preview")
    @PreAuthorize("hasAnyAuthority('APP', 'ADMIN')")
    public ResponseEntity<DailyPreviewDto> createNewDailyPreview(@RequestBody DailyPreviewDto previewDto) throws InvalidDataException {
        return ResponseEntity.ok(dailyService.newDailyPreview(previewDto));
    } // createNewDailyPreview

    @PostMapping("/new/event")
    @PreAuthorize("hasAnyAuthority('APP', 'ADMIN')")
    public ResponseEntity<DailyEventDto> createNewEvent(@RequestBody DailyEventDto dailyEventDto) throws InvalidDataException {
        return ResponseEntity.ok(dailyService.newDailyEvent(dailyEventDto));
    } // createNewEvent

    @PostMapping("/new/review")
    @PreAuthorize("hasAnyAuthority('APP', 'ADMIN')")
    public ResponseEntity<DailyReviewDto> createNewDailyReview(@RequestBody DailyReviewDto dailyReviewDto) throws InvalidDataException{
        return ResponseEntity.ok(dailyService.newDailyReview(dailyReviewDto));
    } // createNewDailyReview

} // DailyController
