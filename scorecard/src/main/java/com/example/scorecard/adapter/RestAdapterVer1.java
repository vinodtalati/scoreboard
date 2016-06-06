package com.example.scorecard.adapter;

import java.util.List;

import com.example.scorecard.rest.domain.GameResource;
import com.example.scorecard.rest.domain.PlayerResource;
import com.example.scorecard.rest.domain.PlayerStatResource;
import com.example.scorecard.rest.domain.TeamResource;


 

public interface RestAdapterVer1 {

	public List<TeamResource> getAllTeams() throws Exception;
	public TeamResource getTeam(int id) throws Exception;
	public TeamResource addTeam(TeamResource teamResource) throws Exception;
	public TeamResource updateTeam(TeamResource teamResource) throws Exception;
	public void removeTeam(int id) throws Exception;
	
	
	public List<PlayerResource> getAllPlayers() throws Exception;
	public PlayerResource getPlayer(int id) throws Exception;
	public PlayerResource addPlayer(PlayerResource playerResource) throws Exception;
	public PlayerResource updatePlayer(PlayerResource playerResource) throws Exception;
	public void removePlayer(int id) throws Exception;
	
	
	public List<GameResource> getAllGames() throws Exception;
	public GameResource getGame(int id) throws Exception;
	public GameResource addGame(GameResource gameResource) throws Exception;
	public GameResource updateGame(GameResource gameResource) throws Exception;
	public void removeGame(int id) throws Exception;
	
	public PlayerStatResource addPlayerStat(PlayerStatResource playerStatResource) throws Exception;
	public PlayerStatResource getPlayerStatsForGame(int playerId, int gameId) throws Exception;
}
