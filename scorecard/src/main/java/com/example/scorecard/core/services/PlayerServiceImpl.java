package com.example.scorecard.core.services;

import java.util.Collection;

import com.example.scorecard.core.app.ScoreCardServiceImpl;
import com.example.scorecard.core.dao.PlayerDao;
import com.example.scorecard.core.dao.PlayerStatDao;
import com.example.scorecard.core.domain.Player;
import com.example.scorecard.core.domain.PlayerStat;

public class PlayerServiceImpl extends ScoreCardServiceImpl implements
		PlayerService {

	private static final String SERVICE_NAME = "PlayerService";
	private PlayerDao playerDao;
	private PlayerStatDao playerStatDao;

	public PlayerServiceImpl(PlayerDao playerDao, PlayerStatDao playerStatDao) {
		super(SERVICE_NAME);
		this.playerDao = playerDao;
		this.playerStatDao = playerStatDao;
	}

	@Override
	public void start() throws Exception {
		super.start();
	}

	@Override
	public void stop() {
		super.stop();
	}

	@Override
	public Player addPlayer(Player player) throws Exception {
		playerDao.add(player);
		return player;
	}

	@Override
	public Player updatePlayer(Player player) throws Exception {
		playerDao.saveOrUpdate(player);
		return player;
	}

	@Override
	public void removePlayer(int id) throws Exception {
		playerDao.delete(id);
	}

	@Override
	public Player getPlayer(int id) throws Exception {
		return playerDao.findById(id);
	}

	@Override
	public Collection<Player> getAllPlayers() throws Exception {
		return playerDao.listPlayers();
	}

	@Override
	public PlayerStat addPlayerStats(PlayerStat playerStat) {
		return playerStatDao.add(playerStat);
	}

	@Override
	public PlayerStat getPlayerStatForGame(int playerId, int gameId) {
		return playerStatDao.listPlayerStats(playerId, gameId);
	}

}
