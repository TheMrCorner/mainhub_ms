package com.mrcorner.journal.test.service.impl;

import com.mrcorner.journal.test.service.ITestService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
public class TestServiceImpl implements ITestService {
    @Override
    public String testingRaspberry() {
        return "Testing the microservice response from the raspi";
    } // testingRaspberry
} // ITestService
