package com.pandatype.leaderboard.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LeaderboardRecordListEntity {
    private String testLanguage;
    private String testType;
    private String testOption;
    private List<LeaderboardRecordEntity> recordList;

    public LeaderboardRecordListEntity(String testLanguage, String testType, String testOption, List<LeaderboardRecordEntity> recordList) {
        this.testLanguage = testLanguage;
        this.testType = testType;
        this.testOption = testOption;
        this.recordList = recordList;
    }
}
