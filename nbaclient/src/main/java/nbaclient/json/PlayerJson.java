package nbaclient.json;

import java.io.Serializable;

public class PlayerJson implements Serializable{

	private static final long serialVersionUID = -413770640529590141L;

    private int id;
    
    private int team_id;
    
    private String player_name;
	
    private String first_name;
	
	private String last_name;
	
    private String position;
    private String birth_date;
    private int dk_id;
    private String dk_position;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTeam_id() {
		return team_id;
	}
	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}
	public String getPlayer_name() {
		return player_name;
	}
	public void setPlayer_name(String player_name) {
		this.player_name = player_name;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}
	public int getDk_id() {
		return dk_id;
	}
	public void setDk_id(int dk_id) {
		this.dk_id = dk_id;
	}
	public String getDk_position() {
		return dk_position;
	}
	public void setDk_position(String dk_position) {
		this.dk_position = dk_position;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
