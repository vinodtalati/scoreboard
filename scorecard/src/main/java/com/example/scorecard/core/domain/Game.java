package com.example.scorecard.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sc_games")
public class Game implements Serializable {

	private static final long serialVersionUID = -413770640529590141L;

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "homeId")
	private Integer homeId;

	@Column(name = "awayId")
	private Integer awayId;

	@Column(name = "season")
	private String season;

	@Column(name = "gameDate")
	private java.util.Calendar gameDate;

	@Column(name = "result")
	private String result;

	@Column(name = "score")
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