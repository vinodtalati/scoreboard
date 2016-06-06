package com.example.scorecard.core.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.scorecard.core.domain.PlayerStat;

@Repository
public class PlayerStatDaoImpl implements PlayerStatDao {
	
	private SessionFactory sessionFactory;
    

    @Autowired
    public PlayerStatDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
    
    @SuppressWarnings("unchecked")
	@Override
    @Transactional
	public PlayerStat listPlayerStats(int playerId, int gameId) {
    	Query query = sessionFactory.getCurrentSession().createQuery("from PlayerStat where playerId = :playerId and gameId = :gameId");
    	query.setParameter("playerId", playerId);
    	query.setParameter("gameId", gameId);
    	List<PlayerStat> list = query.list();
    	
    	if(list.size() >0 ){
    		return list.get(0);
    	}
    	return null;
	}
    
	@Override
    @Transactional
    public PlayerStat add(PlayerStat playerStat) {
		
		BigInteger result = (BigInteger) sessionFactory.getCurrentSession()
				.createSQLQuery("SELECT NEXTVAL('sc_playerstat_sequence')")
				.uniqueResult();
		playerStat.setId(result.intValue());
		
		sessionFactory.getCurrentSession().save(playerStat);
		return playerStat;
    }
	
}
