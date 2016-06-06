package nbaclient.json;

import java.io.Serializable;

public class PlayerStatJson implements Serializable {

	private static final long serialVersionUID = -863234543962397807L;
	private Integer game_id;
	private Integer team_id;
	private Integer player_id;
	private Integer opponent_id;
	private String period;
	private String season;
	private String min;
	private Double off_rating;
	private Double def_rating;
	private Double ast_pct;
	private Double ast_tov;
	private Double ast_ratio;
	private Double oreb_pct;
	private Double dreb_pct;
	private Double treb_pct;
	private Double tm_tov_pct;
	private Double efg_pct;
	private Double ts_pct;
	private Double usg_pct;
	private Double pace;
	private Double pie;
	public Integer getGame_id() {
		return game_id;
	}
	public void setGame_id(Integer game_id) {
		this.game_id = game_id;
	}
	public Integer getTeam_id() {
		return team_id;
	}
	public void setTeam_id(Integer team_id) {
		this.team_id = team_id;
	}
	public Integer getPlayer_id() {
		return player_id;
	}
	public void setPlayer_id(Integer player_id) {
		this.player_id = player_id;
	}
	public Integer getOpponent_id() {
		return opponent_id;
	}
	public void setOpponent_id(Integer opponent_id) {
		this.opponent_id = opponent_id;
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
	public Double getOff_rating() {
		return off_rating;
	}
	public void setOff_rating(Double off_rating) {
		this.off_rating = off_rating;
	}
	public Double getDef_rating() {
		return def_rating;
	}
	public void setDef_rating(Double def_rating) {
		this.def_rating = def_rating;
	}
	public Double getAst_pct() {
		return ast_pct;
	}
	public void setAst_pct(Double ast_pct) {
		this.ast_pct = ast_pct;
	}
	public Double getAst_tov() {
		return ast_tov;
	}
	public void setAst_tov(Double ast_tov) {
		this.ast_tov = ast_tov;
	}
	public Double getAst_ratio() {
		return ast_ratio;
	}
	public void setAst_ratio(Double ast_ratio) {
		this.ast_ratio = ast_ratio;
	}
	public Double getOreb_pct() {
		return oreb_pct;
	}
	public void setOreb_pct(Double oreb_pct) {
		this.oreb_pct = oreb_pct;
	}
	public Double getDreb_pct() {
		return dreb_pct;
	}
	public void setDreb_pct(Double dreb_pct) {
		this.dreb_pct = dreb_pct;
	}
	public Double getTreb_pct() {
		return treb_pct;
	}
	public void setTreb_pct(Double treb_pct) {
		this.treb_pct = treb_pct;
	}
	public Double getTm_tov_pct() {
		return tm_tov_pct;
	}
	public void setTm_tov_pct(Double tm_tov_pct) {
		this.tm_tov_pct = tm_tov_pct;
	}
	public Double getEfg_pct() {
		return efg_pct;
	}
	public void setEfg_pct(Double efg_pct) {
		this.efg_pct = efg_pct;
	}
	public Double getTs_pct() {
		return ts_pct;
	}
	public void setTs_pct(Double ts_pct) {
		this.ts_pct = ts_pct;
	}
	public Double getUsg_pct() {
		return usg_pct;
	}
	public void setUsg_pct(Double usg_pct) {
		this.usg_pct = usg_pct;
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
