package com.pandatype.leaderboard.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class LeaderboardResponseEntity {
    private String status;
    private String message;
    private List<LeaderboardRecordListEntity> leaderboardList;


    public LeaderboardResponseEntity(String status, String message, List<LeaderboardRecordListEntity> leaderboardList) {
        this.status = status;
        this.message = message;
        this.leaderboardList = leaderboardList;
    }
}
