package com.example.scorecard.core.services;

import java.util.Collection;

import com.example.scorecard.core.app.ScorecardService;
import com.example.scorecard.core.domain.Team;

public interface TeamService extends ScorecardService {

	Team addTeam(Team team) throws Exception;

	Team updateTeam(Team team) throws Exception;

	void removeTeam(int id) throws Exception;

	Team getTeam(int id) throws Exception;

	Collection<Team> getAllTeams() throws Exception;

}
