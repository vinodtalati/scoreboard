package nbaclient.resources;

import java.io.Serializable;

public class GameResource implements Serializable {

	private static final long serialVersionUID = -6083477186361678252L;

	private Integer id;
	private Integer homeId;
	private Integer awayId;
	private String season;
	private java.util.Calendar gameDate;
	private String result;
	private String score;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getHomeId() {
		return homeId;
	}

	public void setHomeId(Integer homeId) {
		this.homeId = homeId;
	}

	public Integer getAwayId() {
		return awayId;
	}

	public void setAwayId(Integer awayId) {
		this.awayId = awayId;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public java.util.Calendar getGameDate() {
		return gameDate;
	}

	public void setGameDate(java.util.Calendar gameDate) {
		this.gameDate = gameDate;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}