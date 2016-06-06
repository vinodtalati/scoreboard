package nbaclient.resources;

import java.io.Serializable;

public class TeamResource implements Serializable {

	private static final long serialVersionUID = 5683658617178122476L;

	private Integer id;

	private String name;

	private String city;

	private String abbreviation;
	
	private int wins;

	private int losses;
	
	private int ties;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public int getTies() {
		return ties;
	}

	public void setTies(int ties) {
		this.ties = ties;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
