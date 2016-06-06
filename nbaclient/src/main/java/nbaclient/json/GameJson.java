package nbaclient.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GameJson implements Serializable {

	private static final long serialVersionUID = 8027661486081123105L;
	private int id;
	private int home_id;
	private int away_id;
	private String season;
	private String date;
	
	@JsonProperty(value="final")
	private int finalvalue;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getHome_id() {
		return home_id;
	}
	public void setHome_id(int home_id) {
		this.home_id = home_id;
	}
	public int getAway_id() {
		return away_id;
	}
	public void setAway_id(int away_id) {
		this.away_id = away_id;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getFinalvalue() {
		return finalvalue;
	}
	public void setFinalvalue(int finalvalue) {
		this.finalvalue = finalvalue;
	}
	
	

}