package com.pandatype.leaderboard.service;

import com.pandatype.leaderboard.entities.LeaderboardRecordEntity;
import com.pandatype.leaderboard.entities.LeaderboardResponseEntity;
import com.pandatype.leaderboard.mapper.LeaderboardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LeaderboardService {
    @Resource
    private LeaderboardMapper leaderboardMapper;
    Logger LOGGER = LoggerFactory.getLogger(LeaderboardService.class);

    public LeaderboardResponseEntity getLeaderboardRecords (String testLanguage, String testType, String testOption){
        LeaderboardRecordEntity testOptions = new LeaderboardRecordEntity(testLanguage, testType, testOption);
        List<LeaderboardRecordEntity> list = leaderboardMapper.selectLeaderboardByTestOptions(testOptions);
        return new LeaderboardResponseEntity(list);
    }

    public LeaderboardResponseEntity addLeaderboardRecords (long userId, String testLanguage, String testType, String testOption, Double wpm, Double rawWpm, Double accuracy, Double consistency, String testDate, Double elapsedTime) {
        LeaderboardRecordEntity leaderboardRecordEntity = new LeaderboardRecordEntity(userId, testLanguage, testType, testOption, wpm, accuracy, consistency, rawWpm, testDate, elapsedTime);
        leaderboardMapper.insertLeaderboardRecord(leaderboardRecordEntity);
        return new LeaderboardResponseEntity("SUCCESS", "test record saved!");
    }
}
