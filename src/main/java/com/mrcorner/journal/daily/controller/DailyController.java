package com.mrcorner.journal.daily.controller;

import com.mrcorner.journal.daily.dto.DailyPreviewDto;
import com.mrcorner.journal.daily.service.DailyService;
import com.mrcorner.journal.exceptions.DataNotFoundException;
import com.mrcorner.journal.exceptions.InvalidDataException;
import com.mrcorner.journal.utils.PathUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
@RequestMapping(PathUtils.API_BASE_PATH + PathUtils.API_VERSION + "/daily")
@AllArgsConstructor
public class DailyController {

    DailyService dailyService;

    @PostMapping("/new/preview")
    @PreAuthorize("hasAnyAuthority('APP', 'ADMIN')")
    public ResponseEntity<DailyPreviewDto> createNewDailyPreview(@RequestBody DailyPreviewDto previewDto) throws InvalidDataException {
        return ResponseEntity.ok(dailyService.newDailyPreview(previewDto));
    } // createNewDailyPreview

    @GetMapping("/find/{dayDate}")
    @PreAuthorize("hasAnyAuthority('APP', 'ADMIN')")
    public ResponseEntity<DailyPreviewDto> findDailyPreview(@Valid @PathVariable LocalDate dayDate) throws InvalidDataException, DataNotFoundException{
        return ResponseEntity.ok(dailyService.findDailyPreview(dayDate));
    } // findDailyPreview
} // DailyController
