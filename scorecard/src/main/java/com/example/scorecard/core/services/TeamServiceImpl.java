package com.example.scorecard.core.services;

import java.util.Collection;

import com.example.scorecard.core.app.ScoreCardServiceImpl;
import com.example.scorecard.core.dao.TeamDao;
import com.example.scorecard.core.domain.Team;
import com.google.common.eventbus.EventBus;

public class TeamServiceImpl extends ScoreCardServiceImpl implements TeamService {

	private static final String SERVICE_NAME = "TeamService";
	private TeamDao teamDao;
	EventBus eventBus;

	public TeamServiceImpl(TeamDao teamDao, EventBus eventBus) {
		super(SERVICE_NAME);
		this.teamDao = teamDao;
		this.eventBus = eventBus;
	}

	@Override
	public void start() throws Exception {
		super.start();
	}

	@Override
	public void stop() {
		super.stop();
	}

	@Override
	public Team addTeam(Team team) throws Exception {
		teamDao.add(team);
		return team;
	}

	@Override
	public Team updateTeam(Team team) throws Exception {
		teamDao.saveOrUpdate(team);
		return team;
	}

	@Override
	public void removeTeam(int id) throws Exception {
		teamDao.delete(id);
	}

	@Override
	public Team getTeam(int id) throws Exception {
		return teamDao.findById(id);
	}

	@Override
	public Collection<Team> getAllTeams() throws Exception {
		return teamDao.listTeams();
	}

}
