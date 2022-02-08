package com.pandatype.leaderboard.service;

import com.pandatype.leaderboard.entities.LeaderboardRecordEntity;
import com.pandatype.leaderboard.entities.LeaderboardResponseEntity;
import com.pandatype.leaderboard.entities.LeaderboardRecordListResponseEntity;
import com.pandatype.leaderboard.mapper.LeaderboardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LeaderboardService {
    @Resource
    private LeaderboardMapper leaderboardMapper;
    Logger LOGGER = LoggerFactory.getLogger(LeaderboardService.class);

    public ResponseEntity<LeaderboardRecordListResponseEntity> getLeaderboardRecords (String testLanguage, String testType, String testOption){
        LeaderboardRecordEntity testOptions = new LeaderboardRecordEntity(testLanguage, testType, testOption);
        LeaderboardRecordListResponseEntity list = new LeaderboardRecordListResponseEntity(leaderboardMapper.selectLeaderboardByTestOptions(testOptions));
        return ResponseEntity.ok(list);
    }

    public int addLeaderboardRecords (LeaderboardRecordEntity leaderboardRecordEntity) {
        return leaderboardMapper.insertLeaderboardRecord(leaderboardRecordEntity);
    }
}
