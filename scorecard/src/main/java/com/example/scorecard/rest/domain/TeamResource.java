package com.example.scorecard.rest.domain;

import java.io.Serializable;

public class TeamResource implements Serializable {

	private static final long serialVersionUID = 5683658617178122476L;

	private Integer id;

	private String name;

	private String city;

	private String abbreviation;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
