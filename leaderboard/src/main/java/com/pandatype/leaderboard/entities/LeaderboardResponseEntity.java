package com.pandatype.leaderboard.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LeaderboardResponseEntity {
    private String status;
    private String message;
    private List<LeaderboardRecordEntity> list;

    public LeaderboardResponseEntity(List<LeaderboardRecordEntity> list) {
        this.list = list;
    }

    public LeaderboardResponseEntity(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
