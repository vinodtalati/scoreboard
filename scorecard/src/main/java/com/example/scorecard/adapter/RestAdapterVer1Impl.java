package com.example.scorecard.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import com.example.scorecard.common.util.GameResult;
import com.example.scorecard.core.domain.Game;
import com.example.scorecard.core.domain.Player;
import com.example.scorecard.core.domain.PlayerStat;
import com.example.scorecard.core.domain.Team;
import com.example.scorecard.core.services.GameService;
import com.example.scorecard.core.services.PlayerService;
import com.example.scorecard.core.services.TeamService;
import com.example.scorecard.rest.domain.GameResource;
import com.example.scorecard.rest.domain.PlayerResource;
import com.example.scorecard.rest.domain.PlayerStatResource;
import com.example.scorecard.rest.domain.TeamResource;


public class RestAdapterVer1Impl implements RestAdapterVer1 {
	
	
	private final TeamService teamService;
	private final PlayerService playerService;
	private final GameService gameService;
	
	public RestAdapterVer1Impl(TeamService teamService, PlayerService playerService, GameService gameService) {
		this.teamService = teamService;
		this.playerService = playerService;		
		this.gameService = gameService;
	}


	@Override
	public List<TeamResource> getAllTeams() throws Exception {
		Collection<Team> list = teamService.getAllTeams();
		List<TeamResource> reslist = new ArrayList<TeamResource>();
		for(Team team : list){
			reslist.add(convertToTeamResource(team));
		}
		return reslist;
	}


	@Override
	public TeamResource getTeam(int id) throws Exception {
		return convertToTeamResource(teamService.getTeam(id));
	}


	@Override
	public TeamResource addTeam(TeamResource teamResource) throws Exception {
		return convertToTeamResource(teamService.addTeam(convertToTeam(teamResource)));
	}


	@Override
	public TeamResource updateTeam(TeamResource teamResource) throws Exception {
		return convertToTeamResource(teamService.updateTeam(convertToTeam(teamResource)));
	}


	@Override
	public void removeTeam(int id) throws Exception {
		teamService.removeTeam(id);
	}

	public TeamResource convertToTeamResource(Team team) {
		TeamResource res = new TeamResource();
		
		res.setAbbreviation(team.getAbbreviation());
		res.setCity(team.getCity());
		res.setId(team.getId());
		res.setName(team.getName());
		
		return res;		
	}
	public Team convertToTeam(TeamResource res) {
		Team team = new Team();
		
		team.setAbbreviation(res.getAbbreviation());
		team.setCity(res.getCity());
		team.setId(res.getId());
		team.setName(res.getName());
		
		return team;		
	}
	
	
	@Override
	public List<PlayerResource> getAllPlayers() throws Exception {
		Collection<Player> list = playerService.getAllPlayers();
		List<PlayerResource> reslist = new ArrayList<PlayerResource>();
		for(Player player : list){
			reslist.add(convertToPlayerResource(player));
		}
		return reslist;
	}


	@Override
	public PlayerResource getPlayer(int id) throws Exception {
		return convertToPlayerResource(playerService.getPlayer(id));
	}


	@Override
	public PlayerResource addPlayer(PlayerResource playerResource) throws Exception {
		Player player = convertToPlayer(playerResource);
		int teamId = playerResource.getTeamId();
		
		if(teamId >=0 ){
			player.setTeam(teamService.getTeam(teamId));
		}
		
		return convertToPlayerResource(playerService.addPlayer(player));
	}


	@Override
	public PlayerResource updatePlayer(PlayerResource playerResource) throws Exception {
		return convertToPlayerResource(playerService.updatePlayer(convertToPlayer(playerResource)));
	}


	@Override
	public void removePlayer(int id) throws Exception {
		playerService.removePlayer(id);
	}
	
	public PlayerResource convertToPlayerResource(Player player) {
		PlayerResource res = new PlayerResource();
		
		res.setBirthDate(player.getBirthDate());
		res.setFirstName(player.getFirstName());
		res.setId(player.getId());
		res.setLastName(player.getLastName());
		res.setPlayerName(player.getPlayerName());
		res.setPosition(player.getPosition());
		
		return res;		
	}
	public Player convertToPlayer(PlayerResource res) {
		Player player = new Player();
		
		player.setBirthDate(res.getBirthDate());
		player.setFirstName(res.getFirstName());
		player.setId(res.getId());
		player.setLastName(res.getLastName());
		player.setPlayerName(res.getPlayerName());
		player.setPosition(res.getPosition());
		
		return player;		
	}
	
	
	@Override
	public List<GameResource> getAllGames() throws Exception {
		Collection<Game> list = gameService.getAllGames();
		List<GameResource> reslist = new ArrayList<GameResource>();
		for(Game game : list){
			reslist.add(convertToGameResource(game));
		}
		return reslist;
	}


	@Override
	public GameResource getGame(int id) throws Exception {
		return convertToGameResource(gameService.getGame(id));
	}


	@Override
	public GameResource addGame(GameResource gameResource) throws Exception {
		Game game = convertToGame(gameResource);
		
		Random rand = new Random();
		int homeScore = rand.nextInt(200);
		int awayScore = rand.nextInt(200);
		if(homeScore > awayScore){
			game.setResult(GameResult.WIN.name());
		} else if(homeScore < awayScore) {
			game.setResult(GameResult.LOSS.name());
		} else {
			game.setResult(GameResult.TIE.name());
		}
		game.setScore(Integer.toString(homeScore) + "/" + Integer.toString(awayScore));
		
		return convertToGameResource(gameService.addGame(game));
	}


	@Override
	public GameResource updateGame(GameResource gameResource) throws Exception {
		return convertToGameResource(gameService.updateGame(convertToGame(gameResource)));
	}


	@Override
	public void removeGame(int id) throws Exception {
		gameService.removeGame(id);
	}
	
	public GameResource convertToGameResource(Game game) {
		GameResource res = new GameResource();
		
		res.setAwayId(game.getAwayId());
		res.setGameDate(game.getGameDate());
		res.setHomeId(game.getHomeId());
		res.setId(game.getId());
		res.setResult(game.getResult());
		res.setScore(game.getScore());
		res.setSeason(game.getSeason());
		
		
		return res;		
	}
	public Game convertToGame(GameResource res) {
		Game game = new Game();
		
		game.setAwayId(res.getAwayId());
		game.setGameDate(res.getGameDate());
		game.setHomeId(res.getHomeId());
		game.setId(res.getId());
		game.setResult(res.getResult());
		game.setScore(res.getScore());
		game.setSeason(res.getSeason());
		
		return game;		
	}
	
	@Override
	public PlayerStatResource addPlayerStat(
			PlayerStatResource playerStatResource) throws Exception {
		return convertToPlayerStatResource(playerService.addPlayerStats(convertToPlayerStat(playerStatResource)));
	}

	@Override
	public PlayerStatResource getPlayerStatsForGame(int playerId, int gameId) throws Exception {
		return convertToPlayerStatResource(playerService.getPlayerStatForGame(playerId, gameId));
	}
	
	public PlayerStatResource convertToPlayerStatResource(PlayerStat stat) {
		PlayerStatResource res = new PlayerStatResource();
		
		res.setAstPct(stat.getAstPct());
		res.setAstRatio(stat.getAstRatio());
		res.setAstTov(stat.getAstTov());
		res.setDefRating(stat.getDefRating());
		res.setDrebPct(stat.getDrebPct());
		res.setEfgPct(stat.getEfgPct());
		res.setGameId(stat.getGameId());
		res.setMin(stat.getMin());
		res.setOffRating(stat.getOffRating());
		res.setOpponentId(stat.getOpponentId());
		res.setOrebPct(stat.getOrebPct());
		res.setPace(stat.getPace());
		res.setPeriod(stat.getPeriod());
		res.setPie(stat.getPie());
		res.setPlayerId(stat.getPlayerId());
		res.setSeason(stat.getSeason());
		res.setTeamId(stat.getTeamId());
		res.setTmTovPct(stat.getTmTovPct());
		res.setTrebPct(stat.getTrebPct());
		res.setTsPct(stat.getTsPct());
		res.setUsgPct(stat.getUsgPct());
		
		return res;		
	}
	public PlayerStat convertToPlayerStat(PlayerStatResource res) {
		PlayerStat stat = new PlayerStat();

		stat.setAstPct(res.getAstPct());
		stat.setAstRatio(res.getAstRatio());
		stat.setAstTov(res.getAstTov());
		stat.setDefRating(res.getDefRating());
		stat.setDrebPct(res.getDrebPct());
		stat.setEfgPct(res.getEfgPct());
		stat.setGameId(res.getGameId());
		stat.setMin(res.getMin());
		stat.setOffRating(res.getOffRating());
		stat.setOpponentId(res.getOpponentId());
		stat.setOrebPct(res.getOrebPct());
		stat.setPace(res.getPace());
		stat.setPeriod(res.getPeriod());
		stat.setPie(res.getPie());
		stat.setPlayerId(res.getPlayerId());
		stat.setSeason(res.getSeason());
		stat.setTeamId(res.getTeamId());
		stat.setTmTovPct(res.getTmTovPct());
		stat.setTrebPct(res.getTrebPct());
		stat.setTsPct(res.getTsPct());
		stat.setUsgPct(res.getUsgPct());
		
		return stat;		
	}
	
	
	
}
