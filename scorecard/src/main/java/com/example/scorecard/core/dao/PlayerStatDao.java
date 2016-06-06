package com.example.scorecard.core.dao;

import com.example.scorecard.core.domain.PlayerStat;

public interface PlayerStatDao {
	PlayerStat listPlayerStats(int player, int gameId);
	
    PlayerStat add(PlayerStat playerStat);
}
