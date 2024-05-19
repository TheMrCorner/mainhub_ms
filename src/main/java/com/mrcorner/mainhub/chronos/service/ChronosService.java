package com.mrcorner.mainhub.chronos.service;

import com.mrcorner.mainhub.exceptions.DataNotFoundException;
import com.mrcorner.mainhub.exceptions.InvalidDataException;
import com.mrcorner.mainhub.utils.Http;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChronosService {

    private final Http http;

    @Value("${app.urls.daily.dailySourcePath}")
    private String dailySourcePath;

    public ResponseEntity<String> saveChronodoro(String chronodoroDto) throws InvalidDataException {
        String url = dailySourcePath + "/chronos/chronodoro";
        return http.postRequest(url, chronodoroDto);
    }

    public ResponseEntity<String> savePomodoro(String pomodoroDto) throws InvalidDataException {
        String url = dailySourcePath + "/chronos/pomodoro";
        return http.postRequest(url, pomodoroDto);
    }

    public ResponseEntity<String> obtainHistoryBetweenDates(String request) throws InvalidDataException, DataNotFoundException {
        String url = dailySourcePath + "/chronos/history";
        return http.postRequest(url, request);
    }
}

