package com.pandatype.leaderboard.controller;

import com.pandatype.leaderboard.entities.LeaderboardRecordEntity;
import com.pandatype.leaderboard.entities.LeaderboardRecordListResponseEntity;
import com.pandatype.leaderboard.entities.LeaderboardResponseEntity;
import com.pandatype.leaderboard.service.LeaderboardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.Resource;
import java.net.URI;

@RestController
@RequestMapping(value = "/api/leaderboard")
public class LeaderboardController {
    Logger LOGGER = LoggerFactory.getLogger(LeaderboardController.class);

    @Resource
    private LeaderboardService leaderboardService;

    @GetMapping(value = "/getLeaderboard")
    public ResponseEntity<LeaderboardRecordListResponseEntity> getLeaderboard(@RequestParam(required = true) String testLanguage,
                                                                              @RequestParam(required = true) String testType,
                                                                              @RequestParam(required = true) String testOption){
        return leaderboardService.getLeaderboardRecords(testLanguage, testType, testOption);
    }

    @PostMapping(value = "/saveTestResult")
    public ResponseEntity<Object> saveTestResult(@RequestParam(required = true) String email,
                                                    @RequestParam(required = true) String testLanguage,
                                                    @RequestParam(required = true) String testType,
                                                    @RequestParam(required = true) String testOption,
                                                    @RequestParam(required = true) Double wpm,
                                                    @RequestParam(required = true) Double rawWpm,
                                                    @RequestParam(required = true) Double accuracy,
                                                    @RequestParam(required = true) Double consistency,
                                                    @RequestParam(required = true) String testDate,
                                                    @RequestParam(required = true) Double elapsedTime){
        LeaderboardRecordEntity leaderboardRecordEntity = new LeaderboardRecordEntity(email, testLanguage, testType, testOption, wpm, rawWpm, accuracy, consistency, testDate, elapsedTime);
        int id = leaderboardService.addLeaderboardRecords(leaderboardRecordEntity);
        if (id == 0) {
            return ResponseEntity.notFound().build();
        } else{
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("")
                    .buildAndExpand(id)
                    .toUri();
            return ResponseEntity.created(uri)
                    .body(new LeaderboardResponseEntity("SUCCESS", "record saved"));
        }
    }
}
