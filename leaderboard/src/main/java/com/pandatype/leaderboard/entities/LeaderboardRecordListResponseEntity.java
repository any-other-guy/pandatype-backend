package com.pandatype.leaderboard.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LeaderboardRecordListResponseEntity {
    private List<LeaderboardRecordEntity> recordList;

    public LeaderboardRecordListResponseEntity(List<LeaderboardRecordEntity> recordList) {
        this.recordList = recordList;
    }
}
