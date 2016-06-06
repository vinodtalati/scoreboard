package nbaclient.resources;

import java.io.Serializable;

public class PlayerStatResource implements Serializable {

	private static final long serialVersionUID = -863234543962397807L;
	private Integer gameId;
	private Integer teamId;
	private Integer playerId;
	private Integer opponentId;
	private String period;
	private String season;
	private String min;
	private Double offRating;
	private Double defRating;
	private Double astPct;
	private Double astTov;
	private Double astRatio;
	private Double orebPct;
	private Double drebPct;
	private Double trebPct;
	private Double tmTovPct;
	private Double efgPct;
	private Double tsPct;
	private Double usgPct;
	private Double pace;
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

}
