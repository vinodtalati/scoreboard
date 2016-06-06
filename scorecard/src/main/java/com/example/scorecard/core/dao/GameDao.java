package com.example.scorecard.core.dao;

import java.util.List;

import com.example.scorecard.core.domain.Game;

public interface GameDao {
	List<Game> listGames();
	
	Game add(Game Game);
    
    Game findById(int GameId);
    
    void saveOrUpdate(Game Game);
    
    void delete(int GameId);
   	
}
