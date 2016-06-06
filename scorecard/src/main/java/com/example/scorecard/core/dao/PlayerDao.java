package com.example.scorecard.core.dao;

import java.util.List;

import com.example.scorecard.core.domain.Player;

public interface PlayerDao {
	List<Player> listPlayers();
	
    Player add(Player player);
    
    Player findById(int playerId);
    
    void saveOrUpdate(Player player);
    
    void delete(int playerId);
    
    void delete(List<Integer> ids);
    
	List<Player> listPlayersWithTeam();
}
