package nbaclient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.plaf.SliderUI;

import nbaclient.json.GameJson;
import nbaclient.json.PlayerJson;
import nbaclient.json.PlayerStatJson;
import nbaclient.json.TeamJson;
import nbaclient.resources.GameResource;
import nbaclient.resources.PlayerResource;
import nbaclient.resources.PlayerStatResource;
import nbaclient.resources.TeamResource;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Driver {
	
	

	public static void main(String[] args) throws Exception {
		//sendPost();
		Driver driver = new Driver();
		
		List<TeamJson> teamList = driver.getTeamJsonData();
		driver.uploadTeamData(teamList);
		
		List<PlayerJson> playerList = driver.getPlayerJsonData();
		driver.uploadPlayerData(playerList);
	
		List<GameJson> gameList = driver.getGameJsonData();
		driver.uploadGameData(gameList);
		
	
		/*List<PlayerStatJson> playerStatList = driver.getPlayerStatJsonData();
		driver.uploadPlayerStatData(playerStatList);
		*/
		
	}
	
	private void uploadTeamData(List<TeamJson> teamList) throws Exception {
		
		for(TeamJson json : teamList){
			TeamResource team = new TeamResource();
			team.setAbbreviation(json.getAbbreviation());
			team.setCity(json.getCity());
			team.setId(json.getId());
			team.setName(json.getTeam_name());
			uploadToScoreCard("team", team);
			Thread.sleep(200);
		}
	}
	
	private void uploadPlayerData(List<PlayerJson> playerList) throws Exception {
		
		for(PlayerJson json : playerList){
			PlayerResource p = new PlayerResource();
			
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			cal.setTime(sdf.parse(json.getBirth_date()));
			
			p.setBirthDate(cal);
			p.setFirstName(json.getFirst_name());
			p.setId(json.getId());
			p.setLastName(json.getLast_name());
			p.setPlayerName(json.getPlayer_name());
			p.setPosition(json.getPosition());
			p.setTeamId(json.getTeam_id());
			uploadToScoreCard("player", p);
		}
	}
	
	
	private void uploadGameData(List<GameJson> gameList) throws Exception {
		
		for(GameJson json : gameList){
			GameResource g = new GameResource();
			
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			cal.setTime(sdf.parse(json.getDate()));
			
			g.setAwayId(json.getAway_id());
			g.setGameDate(cal);
			g.setHomeId(json.getHome_id());
			g.setId(json.getId());
			g.setSeason(json.getSeason());
		
			uploadToScoreCard("game", g);
		}
	}
	
	private void uploadPlayerStatData(List<PlayerStatJson> statlist) throws Exception {
		
		for(PlayerStatJson stat : statlist){
			PlayerStatResource res = new PlayerStatResource();
			
			res.setAstPct(stat.getAst_pct());
			res.setAstRatio(stat.getAst_ratio());
			res.setAstTov(stat.getAst_tov());
			res.setDefRating(stat.getDef_rating());
			res.setDrebPct(stat.getDreb_pct());
			res.setEfgPct(stat.getEfg_pct());
			res.setGameId(stat.getGame_id());
			res.setMin(stat.getMin());
			res.setOffRating(stat.getOff_rating());
			res.setOpponentId(stat.getOpponent_id());
			res.setOrebPct(stat.getOreb_pct());
			res.setPace(stat.getPace());
			res.setPeriod(stat.getPeriod());
			res.setPie(stat.getPie());
			res.setPlayerId(stat.getPlayer_id());
			res.setSeason(stat.getSeason());
			res.setTeamId(stat.getTeam_id());
			res.setTmTovPct(stat.getTm_tov_pct());
			res.setTrebPct(stat.getTreb_pct());
			res.setTsPct(stat.getTs_pct());
			res.setUsgPct(stat.getUsg_pct());
			
			uploadToScoreCard("player/playerstats", res);
		}
	}
	
	
	private  List<TeamJson> getTeamJsonData(){
		ObjectMapper mapper = new ObjectMapper();
		List<TeamJson> teamList = null;
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("teams.txt").getFile());
		
			teamList =    mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, TeamJson.class));
			System.out.println("Teams parsed: " +  teamList.size());
			
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return teamList;
	}
	
	
	private  List<PlayerJson> getPlayerJsonData(){
		ObjectMapper mapper = new ObjectMapper();
		List<PlayerJson> playerList = null;
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("players.txt").getFile());
		
			playerList =    mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, PlayerJson.class));
			System.out.println("Players parsed: " +  playerList.size());
			
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return playerList;
	}
	
	private  List<GameJson> getGameJsonData(){
		ObjectMapper mapper = new ObjectMapper();
		List<GameJson> gameList = null;
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("games.txt").getFile());
		
			gameList =    mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, GameJson.class));
			System.out.println("Games parsed: " +  gameList.size());
			
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return gameList;
	}
	
	
	private  List<PlayerStatJson> getPlayerStatJsonData(){
		ObjectMapper mapper = new ObjectMapper();
		List<PlayerStatJson> playerStatList = null;
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("playerStats.txt").getFile());
		
			playerStatList =    mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, PlayerStatJson.class));
			System.out.println("PlayerStats parsed: " +  playerStatList.size());
			
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return playerStatList;
	}
	
	private static void sendPost() throws Exception {

		String url = "http://api.probasketballapi.com/advanced/player";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		
		con.setRequestMethod("POST");
		
		String urlParameters = "api_key=2t3aSeL5u7DG6xiQoJdnZTjEq8XCWpg1&team_id=1610612737&season=2015";
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		System.out.println(response.toString());

	}
	
	private static void uploadToScoreCard(String resource, Object resobj) throws Exception {

		String url = "http://localhost:8080/scorecard/api/v1/" + resource;
		URL urlObj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestMethod("POST");
		
		ObjectMapper mapper = new ObjectMapper();	
		String urlParameters = mapper.writeValueAsString(resobj);
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		System.out.println(response.toString());

	}
	
	/*private static void uploadToScoreCardPlayerStat(String resource, Object resobj, int playerid) throws Exception {

		String url = "http://localhost:8080/scorecard/api/v1/" + resource ;
		URL urlObj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestMethod("POST");
		
		ObjectMapper mapper = new ObjectMapper();	
		String urlParameters = mapper.writeValueAsString(resobj);
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		System.out.println(response.toString());

	}*/
	

}
