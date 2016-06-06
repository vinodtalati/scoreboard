package com.example.scorecard.core.services;

import java.util.Collection;

import com.example.scorecard.core.app.ScoreCardServiceImpl;
import com.example.scorecard.core.dao.GameDao;
import com.example.scorecard.core.domain.Game;

public class GameServiceImpl extends ScoreCardServiceImpl implements GameService {

	private static final String SERVICE_NAME = "GameService";
	private GameDao GameDao;

	public GameServiceImpl(final GameDao GameDao) {
		super(SERVICE_NAME);
		this.GameDao = GameDao;
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
	public Game addGame(Game Game) throws Exception {
		GameDao.add(Game);
		return Game;
	}

	@Override
	public Game updateGame(Game Game) throws Exception {
		GameDao.saveOrUpdate(Game);
		return Game;
	}

	@Override
	public void removeGame(int id) throws Exception {
		GameDao.delete(id);
	}

	@Override
	public Game getGame(int id) throws Exception {
		return GameDao.findById(id);
	}

	@Override
	public Collection<Game> getAllGames() throws Exception {
		return GameDao.listGames();
	}

}
