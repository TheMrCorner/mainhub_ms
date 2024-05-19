package com.mrcorner.mainhub.journal.daily.service;

import com.mrcorner.mainhub.exceptions.DataNotFoundException;
import com.mrcorner.mainhub.exceptions.InvalidDataException;
import com.mrcorner.mainhub.utils.Http;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DailyService {

    private final Http http;

    @Value("${app.urls.daily.dailySourcePath}")
    private String dailySourcePath;

    public ResponseEntity<String> findDailyPreview(LocalDate dayDate) throws InvalidDataException, DataNotFoundException {
        String url = dailySourcePath + "/daily/find/" + dayDate.toString();
        return http.getRequest(url);
    }

    public ResponseEntity<String> findDailyReview(Integer idDay) throws InvalidDataException, DataNotFoundException{
        String url = dailySourcePath + "/daily/find/review?idDay=" + idDay.toString();
        return http.getRequest(url);
    }

    public ResponseEntity<String> findDailyEvents(Integer idDay) throws InvalidDataException, DataNotFoundException{
        String url = dailySourcePath + "/daily/find/events/" + idDay.toString();
        return http.getRequest(url);
    }

    public ResponseEntity<String> findAllDailyEventsBetweenDates(LocalDate startDate, LocalDate endDate, Boolean important) throws InvalidDataException {
        String url = dailySourcePath + "/daily/find/events/betweendates?startDate=" + startDate.toString()
                     + "&endDate=" + endDate.toString();
        if(important != null){
            url += "&important=" + important;
        }
        return http.getRequest(url); 
    }

    public ResponseEntity<String> saveDailyPreview(String body) throws InvalidDataException{
        String url = dailySourcePath + "/daily/save/preview";
        return http.postRequest(url, body);
    }

    public ResponseEntity<String> saveEvent(String body) throws InvalidDataException{
        String url = dailySourcePath + "/daily/save/event";
        return http.postRequest(url, body);
    }

    public ResponseEntity<String> saveDailyReview(String body) throws InvalidDataException{
        String url = dailySourcePath + "/daily/save/review";
        return http.postRequest(url, body);
    }

}
