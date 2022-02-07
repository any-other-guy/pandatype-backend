package com.pandatype.leaderboard.mapper;

import com.pandatype.leaderboard.entities.LeaderboardRecordEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LeaderboardMapper {
    List<LeaderboardRecordEntity> selectLeaderboardByTestOptions(LeaderboardRecordEntity leaderboardRecordEntity);

    void insertLeaderboardRecord(LeaderboardRecordEntity leaderboardRecordEntity);
}
