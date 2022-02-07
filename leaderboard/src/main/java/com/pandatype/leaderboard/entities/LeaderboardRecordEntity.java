package com.pandatype.leaderboard.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeaderboardRecordEntity {
    private long userId;
    private String username;
    private String testLanguage;
    private String testType;
    private String testOption;
    private Double wpm;
    private Double rawWpm;
    private Double accuracy;
    private Double consistency;
    private String testDate;
    private Double elapsedTime;

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

    public LeaderboardRecordEntity(String username, String testLanguage, String testType, String testOption, Double wpm, Double rawWpm, Double accuracy, Double consistency, String testDate, Double elapsedTime) {
        this.username = username;
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
}
