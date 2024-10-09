package com.mrcorner.mainhub.journal.weekly.service;

import com.mrcorner.mainhub.utils.Http;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class WeeklyService {

    private final Http http;
    @Value("${app.urls.daily.weeklySourcePath}")
    private String weeklySourcePath;


    public ResponseEntity<String> findWeeklyPreview(LocalDate wDate){
        String url = weeklySourcePath + "/weekly/find/preview/" + wDate.toString();
        return http.getRequest(url);
    }

    public ResponseEntity<String> findWeeklyReview(Integer idWeek){
        String url = weeklySourcePath + "/weekly/find/review/" + idWeek.toString();
        return http.getRequest(url);
    }

    public ResponseEntity<String> findWeeklyBuyingList(Integer idWeek){
        String url = weeklySourcePath + "/weekly/find/buyinglist/" + idWeek.toString();
        return http.getRequest(url);
    }

    public ResponseEntity<String> saveWeeklyPreview(String weeklyPreview){
        String url = weeklySourcePath + "/weekly/save/preview";
        return http.postRequest(url, weeklyPreview);
    }

    public ResponseEntity<String> saveWeeklyReview(String weeklyReview){
        String url = weeklySourcePath + "/weekly/save/review";
        return http.postRequest(url, weeklyReview);
    }

    public ResponseEntity<String> saveWeeklyBuyingList(String weeklyBuyingList){
        String url = weeklyBuyingList + "/weekly/save/buyinglist";
        return http.postRequest(url, weeklyBuyingList);
    }
}
