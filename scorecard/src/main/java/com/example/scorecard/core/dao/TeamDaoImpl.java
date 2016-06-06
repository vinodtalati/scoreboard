package com.example.scorecard.core.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.scorecard.core.domain.Team;

@Repository
public class TeamDaoImpl implements TeamDao {
	
	private SessionFactory sessionFactory;
    

    @Autowired
    public TeamDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
    
    @SuppressWarnings("unchecked")
	@Override
    @Transactional
	public List<Team> listTeams() {
		return sessionFactory.getCurrentSession().createQuery("from Team").list();
	}
    
   
    @SuppressWarnings("unchecked")
    @Override
    @Transactional
	public List<Team> listTeamsWithPlayers() {
		List<Team> teamList =  sessionFactory.getCurrentSession().createQuery("from Team").list();
		for(Team team : teamList) {
			Hibernate.initialize(team.getPlayers());
		}
        return teamList;
	}
    
	
	@Override
    @Transactional
    public Team add(Team team) {
		sessionFactory.getCurrentSession().save(team);
		return team;
    }
    
	
	@Override
    @Transactional
    public Team findById(int teamId) {
		return (Team) sessionFactory.getCurrentSession().get(Team.class, teamId);
    }
    
	
	@Override
    @Transactional
    public void saveOrUpdate(Team team) {
		sessionFactory.getCurrentSession().saveOrUpdate(team);
    }
        
	
	@Override
    @Transactional
    public void delete(int teamId) {		
		Team team = findById(teamId);
        if (team != null) {
            sessionFactory.getCurrentSession().delete(team);
        }
    }
	
	
	@Override
    @Transactional
    public void delete(List<Integer> ids) {
		Query q = sessionFactory.getCurrentSession().createQuery("DELETE FROM Team t WHERE t.id IN (:list)");
		q.setParameterList("list", ids);
		q.executeUpdate();
    }

}
