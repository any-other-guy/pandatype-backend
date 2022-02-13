package com.pandatype.leaderboard.service;

import com.pandatype.leaderboard.entities.LeaderboardResponseEntity;
import com.pandatype.leaderboard.entities.UserEntity;
import com.pandatype.leaderboard.entities.LeaderboardRecordEntity;
import com.pandatype.leaderboard.entities.LeaderboardRecordListEntity;
import com.pandatype.leaderboard.mapper.LeaderboardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class LeaderboardService {
    @Resource
    private LeaderboardMapper leaderboardMapper;
    Logger LOGGER = LoggerFactory.getLogger(LeaderboardService.class);

    public ResponseEntity<LeaderboardResponseEntity> getLeaderboardRecordsByOptions (String testLanguage, String testType, String testOption){
        LeaderboardRecordEntity testOptions = new LeaderboardRecordEntity(testLanguage, testType, testOption);
        List<LeaderboardRecordEntity> recordList =  leaderboardMapper.selectLeaderboardByTestOptions(testOptions);
        LeaderboardRecordListEntity leaderboardRecordListEntity = new LeaderboardRecordListEntity(testLanguage, testType, testOption, recordList);
        List<LeaderboardRecordListEntity> list = new ArrayList<>();
        list.add(leaderboardRecordListEntity);
        String message = String.format("%s %s %s", testLanguage, testType, testOption);
        LeaderboardResponseEntity res = new LeaderboardResponseEntity("SUCCESS", message, list);
        return ResponseEntity.ok(res);
    }

    public ResponseEntity<LeaderboardResponseEntity> getAllLeaderboards () {
        List<LeaderboardRecordListEntity> list = new ArrayList<>();
        list.add(new LeaderboardRecordListEntity("en", "time", "15",
                new ArrayList<>(leaderboardMapper.selectLeaderboardByTestOptions(
                        new LeaderboardRecordEntity("en", "time", "15")))));
        list.add(new LeaderboardRecordListEntity("en", "time", "60",
                new ArrayList<>(leaderboardMapper.selectLeaderboardByTestOptions(
                        new LeaderboardRecordEntity("en", "time", "60")))));
        list.add(new LeaderboardRecordListEntity("zh", "time", "15",
                new ArrayList<>(leaderboardMapper.selectLeaderboardByTestOptions(
                        new LeaderboardRecordEntity("zh", "time", "15")))));
        list.add(new LeaderboardRecordListEntity("zh", "time", "60",
                new ArrayList<>(leaderboardMapper.selectLeaderboardByTestOptions(
                        new LeaderboardRecordEntity("zh", "time", "60")))));
        return ResponseEntity.ok(new LeaderboardResponseEntity("SUCCESS", "all leaderboards", list));
    }

    public int addLeaderboardRecords (LeaderboardRecordEntity leaderboardRecordEntity) {
        UserEntity userEntity = leaderboardMapper.selectUserEntityByEmail(leaderboardRecordEntity.getUsername());
        leaderboardRecordEntity.setUserId(userEntity.getUserId());
        return leaderboardMapper.insertLeaderboardRecord(leaderboardRecordEntity);
    }
}
