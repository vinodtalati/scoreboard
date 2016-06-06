package com.example.scorecard.core.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.scorecard.core.domain.Game;

@Repository
public class GameDaoImpl implements GameDao {
	
	private SessionFactory sessionFactory;
    

    @Autowired
    public GameDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
    
    @SuppressWarnings("unchecked")
	@Override
    @Transactional
	public List<Game> listGames() {
		return sessionFactory.getCurrentSession().createQuery("from Game").list();
	}
    
	@Override
    @Transactional
    public Game add(Game game) {
		sessionFactory.getCurrentSession().save(game);
		return game;
    }
    
	
	@Override
    @Transactional
    public Game findById(int gameId) {
		return (Game) sessionFactory.getCurrentSession().get(Game.class, gameId);
    }
    
	
	@Override
    @Transactional
    public void saveOrUpdate(Game game) {
		sessionFactory.getCurrentSession().saveOrUpdate(game);
    }
        
	
	@Override
    @Transactional
    public void delete(int gameId) {		
		Game game = findById(gameId);
        if (game != null) {
            sessionFactory.getCurrentSession().delete(game);
        }
    }
	

}
