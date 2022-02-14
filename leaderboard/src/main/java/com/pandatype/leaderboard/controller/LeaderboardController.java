package com.pandatype.leaderboard.controller;

import com.pandatype.leaderboard.entities.LeaderboardRecordEntity;
import com.pandatype.leaderboard.entities.LeaderboardResponseEntity;
import com.pandatype.leaderboard.security.TokenUtils;
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

    @Resource
    TokenUtils tokenUtils;

    @GetMapping(value = "/getLeaderboard")
    public ResponseEntity<LeaderboardResponseEntity> getLeaderboard(@RequestParam(required = false) String testLanguage,
                                                                      @RequestParam(required = false) String testType,
                                                                      @RequestParam(required = false) String testOption){
        if(testLanguage != null && testType != null && testOption !=  null) {
            return leaderboardService.getLeaderboardRecordsByOptions(testLanguage, testType, testOption);
        }
        return leaderboardService.getAllLeaderboards();
    }

    @PostMapping(value = "/saveTestResult")
    public ResponseEntity<LeaderboardResponseEntity> saveTestResult(@RequestBody LeaderboardRecordEntity leaderboardRecordEntity){
        leaderboardRecordEntity.setIdentifierStr(tokenUtils.getEmailFromToken(leaderboardRecordEntity.getIdentifierStr()));
        int id = leaderboardService.addLeaderboardRecords(leaderboardRecordEntity);
        if (id == 0) {
            return ResponseEntity.notFound().build();
        } else{
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("")
                    .buildAndExpand(id)
                    .toUri();
            return ResponseEntity.created(uri)
                    .body(new LeaderboardResponseEntity("SUCCESS", "record saved", null));
        }
    }
}
