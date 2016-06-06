package com.example.scorecard.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sc_player_stats")
public class PlayerStat implements Serializable {

	private static final long serialVersionUID = 6953394256506970850L;

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "gameId")
	private Integer gameId;

	@Column(name = "teamId")
	private Integer teamId;

	@Column(name = "playerId")
	private Integer playerId;

	@Column(name = "opponentId")
	private Integer opponentId;

	@Column(name = "period")
	private String period;

	@Column(name = "season")
	private String season;

	@Column(name = "minutes")
	private String min;

	@Column(name = "off_rating")
	private Double offRating;

	@Column(name = "def_rating")
	private Double defRating;

	@Column(name = "ast_pct")
	private Double astPct;

	@Column(name = "ast_tov")
	private Double astTov;

	@Column(name = "ast_ratio")
	private Double astRatio;

	@Column(name = "oreb_pct")
	private Double orebPct;

	@Column(name = "dreb_pct")
	private Double drebPct;

	@Column(name = "treb_pct")
	private Double trebPct;

	@Column(name = "tm_tov_pct")
	private Double tmTovPct;

	@Column(name = "efg_pct")
	private Double efgPct;

	@Column(name = "ts_pct")
	private Double tsPct;

	@Column(name = "usg_pct")
	private Double usgPct;

	@Column(name = "pace")
	private Double pace;

	@Column(name = "pie")
	private Double pie;

	public Integer getGameId() {
		return gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Integer getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}

	public Integer getOpponentId() {
		return opponentId;
	}

	public void setOpponentId(Integer opponentId) {
		this.opponentId = opponentId;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public Double getOffRating() {
		return offRating;
	}

	public void setOffRating(Double offRating) {
		this.offRating = offRating;
	}

	public Double getDefRating() {
		return defRating;
	}

	public void setDefRating(Double defRating) {
		this.defRating = defRating;
	}

	public Double getAstPct() {
		return astPct;
	}

	public void setAstPct(Double astPct) {
		this.astPct = astPct;
	}

	public Double getAstTov() {
		return astTov;
	}

	public void setAstTov(Double astTov) {
		this.astTov = astTov;
	}

	public Double getAstRatio() {
		return astRatio;
	}

	public void setAstRatio(Double astRatio) {
		this.astRatio = astRatio;
	}

	public Double getOrebPct() {
		return orebPct;
	}

	public void setOrebPct(Double orebPct) {
		this.orebPct = orebPct;
	}

	public Double getDrebPct() {
		return drebPct;
	}

	public void setDrebPct(Double drebPct) {
		this.drebPct = drebPct;
	}

	public Double getTrebPct() {
		return trebPct;
	}

	public void setTrebPct(Double trebPct) {
		this.trebPct = trebPct;
	}

	public Double getTmTovPct() {
		return tmTovPct;
	}

	public void setTmTovPct(Double tmTovPct) {
		this.tmTovPct = tmTovPct;
	}

	public Double getEfgPct() {
		return efgPct;
	}

	public void setEfgPct(Double efgPct) {
		this.efgPct = efgPct;
	}

	public Double getTsPct() {
		return tsPct;
	}

	public void setTsPct(Double tsPct) {
		this.tsPct = tsPct;
	}

	public Double getUsgPct() {
		return usgPct;
	}

	public void setUsgPct(Double usgPct) {
		this.usgPct = usgPct;
	}

	public Double getPace() {
		return pace;
	}

	public void setPace(Double pace) {
		this.pace = pace;
	}

	public Double getPie() {
		return pie;
	}

	public void setPie(Double pie) {
		this.pie = pie;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
