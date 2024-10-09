package com.mrcorner.mainhub.journal.habit.service;

import com.mrcorner.mainhub.utils.Http;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class HabitService {

    private final Http http;
    @Value("${app.urls.daily.dailySourcePath}")
    private String dailySourcePath;

    public ResponseEntity<String> findHabitsByDay(LocalDate dayDate){
        String url = dailySourcePath + "/habit/find/" + dayDate.toString();
        return http.getRequest(url);
    }

    public ResponseEntity<String> saveNewHabit(String body){
        String url = dailySourcePath + "/habit/new";
        return http.postRequest(url, body);
    }
}
