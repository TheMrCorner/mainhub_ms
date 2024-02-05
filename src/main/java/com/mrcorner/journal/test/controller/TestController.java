package com.mrcorner.journal.test.controller;

import com.mrcorner.journal.test.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @Autowired
    private ITestService testService;

    @PreAuthorize("hasAnyAuthority('TESTING', 'ADMIN', 'APP')")
    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint(){
        return ResponseEntity.ok("This is a test from the Raspberry");
    } // testEndpoint
} // TestController
