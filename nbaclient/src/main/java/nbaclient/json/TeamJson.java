package nbaclient.json;

import java.io.Serializable;

public class TeamJson implements Serializable {

	private static final long serialVersionUID = -8642054537470004284L;

	private Integer id;

	private String team_name;

	private String city;

	private String abbreviation;
	
	private int dk_id;
	private String created_at;
	private String updated_at;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTeam_name() {
		return team_name;
	}
	public void setTeam_name(String team_name) {
		this.team_name = team_name;
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
	public int getDk_id() {
		return dk_id;
	}
	public void setDk_id(int dk_id) {
		this.dk_id = dk_id;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
