package com.mrcorner.mainhub.chronos.controller;

import com.mrcorner.mainhub.chronos.service.ChronosService;
import com.mrcorner.mainhub.utils.PathUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PathUtils.API_BASE_PATH + PathUtils.API_VERSION + "/chronos")
@RequiredArgsConstructor
public class ChronosController {

    private final ChronosService chronosService;

    @PreAuthorize("hasAuthority('APP')")
    @PostMapping("/pomodoro")
    public ResponseEntity<String> registerPomodoro(@RequestBody String pomodoroDto){
        return chronosService.savePomodoro(pomodoroDto);
    }

    @PreAuthorize("hasAuthority('APP')")
    @PostMapping("/chronodoro")
    public ResponseEntity<String> registerChronodoro(@RequestBody String chronodoroDto){
        return chronosService.saveChronodoro(chronodoroDto);
    }

    @PreAuthorize("hasAuthority('APP')")
    @PostMapping("/history")
    public ResponseEntity<String> obtainHistory(@RequestBody String requestDto){
        return chronosService.obtainHistoryBetweenDates(requestDto);
    }

    @GetMapping("/test-jenkins")
    public ResponseEntity<String> testJenkins() {
        return ResponseEntity.ok("This is a build made automatically by Jenkins");
    }
}
