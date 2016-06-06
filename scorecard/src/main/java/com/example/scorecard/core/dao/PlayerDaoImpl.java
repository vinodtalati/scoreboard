package com.example.scorecard.core.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.scorecard.core.domain.Player;

@Repository
public class PlayerDaoImpl implements PlayerDao {
	
	private SessionFactory sessionFactory;
    

    @Autowired
    public PlayerDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
    
    @SuppressWarnings("unchecked")
	@Override
    @Transactional
	public List<Player> listPlayers() {
		return sessionFactory.getCurrentSession().createQuery("from Player").list();
	}
    
   
    @SuppressWarnings("unchecked")
    @Override
    @Transactional
	public List<Player> listPlayersWithTeam() {
		List<Player> playerList =  sessionFactory.getCurrentSession().createQuery("from Player").list();
		for(Player player : playerList) {
			Hibernate.initialize(player.getTeam());
		}
        return playerList;
	}
    
	
	@Override
    @Transactional
    public Player add(Player player) {
		sessionFactory.getCurrentSession().save(player);
		return player;
    }
    
	
	@Override
    @Transactional
    public Player findById(int playerId) {
		return (Player) sessionFactory.getCurrentSession().get(Player.class, playerId);
    }
    
	
	@Override
    @Transactional
    public void saveOrUpdate(Player player) {
		sessionFactory.getCurrentSession().saveOrUpdate(player);
    }
        
	
	@Override
    @Transactional
    public void delete(int playerId) {		
		Player player = findById(playerId);
        if (player != null) {
            sessionFactory.getCurrentSession().delete(player);
        }
    }
	
	
	@Override
    @Transactional
    public void delete(List<Integer> ids) {
		Query q = sessionFactory.getCurrentSession().createQuery("DELETE FROM Player t WHERE t.id IN (:list)");
		q.setParameterList("list", ids);
		q.executeUpdate();
    }

}
