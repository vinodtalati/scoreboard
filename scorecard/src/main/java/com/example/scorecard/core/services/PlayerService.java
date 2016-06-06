package com.example.scorecard.core.services;

import java.util.Collection;

import com.example.scorecard.core.app.ScorecardService;
import com.example.scorecard.core.domain.Player;
import com.example.scorecard.core.domain.PlayerStat;

public interface PlayerService extends ScorecardService {

	Player addPlayer(Player player) throws Exception;

	Player updatePlayer(Player player) throws Exception;

	void removePlayer(int id) throws Exception;

	Player getPlayer(int id) throws Exception;

	Collection<Player> getAllPlayers() throws Exception;

	PlayerStat addPlayerStats(PlayerStat playerStat);

	PlayerStat getPlayerStatForGame(int playerId, int gameId);

}
