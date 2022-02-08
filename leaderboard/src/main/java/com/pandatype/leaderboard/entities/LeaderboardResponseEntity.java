package com.pandatype.leaderboard.entities;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LeaderboardResponseEntity {
    private String status;
    private String message;

    public LeaderboardResponseEntity(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
