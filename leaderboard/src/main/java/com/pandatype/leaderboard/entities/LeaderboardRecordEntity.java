package com.pandatype.leaderboard.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LeaderboardRecordEntity {
    private long userId;
    private String identifierStr;
    private String testLanguage;
    private String testType;
    private String testOption;
    private Double wpm;
    private Double rawWpm;
    private Double accuracy;
    private Double consistency;
    private String testDate;
    private Double elapsedTime;
    private String ipAddress;

    public LeaderboardRecordEntity(String testLanguage, String testType, String testOption) {
        this.testLanguage = testLanguage;
        this.testType = testType;
        this.testOption = testOption;
    }


    public LeaderboardRecordEntity(long userId, String testLanguage, String testType, String testOption, Double wpm, Double rawWpm, Double accuracy, Double consistency, String testDate, Double elapsedTime) {
        this.userId = userId;
        this.testLanguage = testLanguage;
        this.testType = testType;
        this.testOption = testOption;
        this.wpm = wpm;
        this.rawWpm = rawWpm;
        this.accuracy = accuracy;
        this.consistency = consistency;
        this.testDate = testDate;
        this.elapsedTime = elapsedTime;
    }

    public LeaderboardRecordEntity(String identifierStr, String testLanguage, String testType, String testOption, Double wpm, Double rawWpm, Double accuracy, Double consistency, String testDate, Double elapsedTime, String ipAddress) {
        this.identifierStr = identifierStr;
        this.testLanguage = testLanguage;
        this.testType = testType;
        this.testOption = testOption;
        this.wpm = wpm;
        this.rawWpm = rawWpm;
        this.accuracy = accuracy;
        this.consistency = consistency;
        this.testDate = testDate;
        this.elapsedTime = elapsedTime;
        this.ipAddress = ipAddress;
    }
}
