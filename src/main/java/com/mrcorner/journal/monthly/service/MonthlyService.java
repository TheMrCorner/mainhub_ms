package com.mrcorner.journal.monthly.service;

import com.mrcorner.journal.monthly.repository.MonthlyPreviewRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
@Slf4j
public class MonthlyService {

    // Monthly
    MonthlyPreviewRepository monthlyPreviewRepository;

    public Integer findMonthByDay(LocalDate idDay){
        return monthlyPreviewRepository.findIdMonthByDayDate(idDay);
    } // findMonthByDay

} // MonthlyService
