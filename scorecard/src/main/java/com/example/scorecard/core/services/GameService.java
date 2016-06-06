package com.example.scorecard.core.services;

import java.util.Collection;

import com.example.scorecard.core.app.ScorecardService;
import com.example.scorecard.core.domain.Game;

public interface GameService extends ScorecardService {

	Game addGame(Game game) throws Exception;

	Game updateGame(Game game) throws Exception;

	void removeGame(int id) throws Exception;

	Game getGame(int id) throws Exception;

	Collection<Game> getAllGames() throws Exception;

}
