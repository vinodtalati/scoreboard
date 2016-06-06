package com.example.scorecard.core.dao;

import java.util.List;

import com.example.scorecard.core.domain.Team;

public interface TeamDao {
	List<Team> listTeams();
	
    Team add(Team team);
    
    Team findById(int teamId);
    
    void saveOrUpdate(Team team);
    
    void delete(int teamId);
    
    void delete(List<Integer> ids);
    
	List<Team> listTeamsWithPlayers();
}
