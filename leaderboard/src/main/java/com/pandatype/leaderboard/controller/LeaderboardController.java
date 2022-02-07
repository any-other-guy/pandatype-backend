package com.pandatype.leaderboard.controller;

import com.pandatype.leaderboard.entities.LeaderboardResponseEntity;
import com.pandatype.leaderboard.service.LeaderboardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/api/leaderboard")
public class LeaderboardController {
    Logger logger = LoggerFactory.getLogger(LeaderboardController.class);

    @Resource
    private LeaderboardService leaderboardService;

    @GetMapping(value = "/getLeaderboard")
    public LeaderboardResponseEntity getLeaderboard(@RequestParam(required = true) String testLanguage,
                                                    @RequestParam(required = true) String testType,
                                                    @RequestParam(required = true) String testOption){
        return leaderboardService.getLeaderboardRecords(testLanguage, testType, testOption);
    }

    @PostMapping(value = "/saveTestResult")
    public LeaderboardResponseEntity saveTestResult(@RequestParam(required = true) long userId,
                                                    @RequestParam(required = true) String testLanguage,
                                                    @RequestParam(required = true) String testType,
                                                    @RequestParam(required = true) String testOption,
                                                    @RequestParam(required = true) Double wpm,
                                                    @RequestParam(required = true) Double rawWpm,
                                                    @RequestParam(required = true) Double accuracy,
                                                    @RequestParam(required = true) Double consistency,
                                                    @RequestParam(required = true) String testDate,
                                                    @RequestParam(required = true) Double elapsedTime){
        return leaderboardService.addLeaderboardRecords(userId, testLanguage, testType, testOption, wpm, rawWpm, accuracy, consistency, testDate, elapsedTime);
    }
}
