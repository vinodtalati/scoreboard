package com.example.scorecard.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.scorecard.core.dao.GameDao;
import com.example.scorecard.core.dao.GameDaoImpl;
import com.example.scorecard.core.dao.PlayerDao;
import com.example.scorecard.core.dao.PlayerDaoImpl;
import com.example.scorecard.core.dao.PlayerStatDao;
import com.example.scorecard.core.dao.PlayerStatDaoImpl;
import com.example.scorecard.core.dao.ScJdbcDao;
import com.example.scorecard.core.dao.ScJdbcDaoImpl;
import com.example.scorecard.core.dao.TeamDao;
import com.example.scorecard.core.dao.TeamDaoImpl;
import com.example.scorecard.core.domain.Game;
import com.example.scorecard.core.domain.Player;
import com.example.scorecard.core.domain.PlayerStat;
import com.example.scorecard.core.domain.Team;


@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class DaoConfig {

	@Value("${jdbc.driverClassName}")
	private String jdbcDriverClassName;

	@Value("${jdbc.databaseurl}")
	private String jdbcDatabaseUrl;

	@Value("${jdbc.username}")
	private String jdbcUsername;

	@Value("${jdbc.password}")
	private String jdbcPassword;

	@Value("${hibernate.dialect}")
	private String hibernateDialect;

	@Value("${hibernate.show_sql}")
	private String hibernateShowSql;

	@Value("${hibernate.format_sql}")
	private String hibernateFormatSql;
	
	@Value("${dbcp.initialSize}")
	private int dbcpInitialSize;
	
	@Value("${dbcp.maxTotal}")
	private int dbcpMaxTotal;
	
	@Value("${dbcp.maxIdle}")
	private int dbcpMaxIdle;
	
	@Value("${dbcp.minIdle}")
	private int dbcpMinIdle;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
		return new PersistenceExceptionTranslationPostProcessor();
	}


	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(jdbcDriverClassName);
		dataSource.setUrl(jdbcDatabaseUrl);
		dataSource.setUsername(jdbcUsername);
		dataSource.setPassword(jdbcPassword);
		dataSource.setInitialSize(dbcpInitialSize);
		dataSource.setMaxTotal(dbcpMaxTotal);
		dataSource.setMaxIdle(dbcpMaxIdle);
		dataSource.setMinIdle(dbcpMinIdle);	
	
		return dataSource;
	}

	
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		
		sessionBuilder.addAnnotatedClasses(Team.class);
		sessionBuilder.addAnnotatedClasses(Player.class);
		sessionBuilder.addAnnotatedClasses(Game.class);
		sessionBuilder.addAnnotatedClasses(PlayerStat.class);
		
		sessionBuilder.addProperties(getHibernateProperties());
	    return sessionBuilder.buildSessionFactory();
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", hibernateDialect);
		properties.put("hibernate.format_sql", hibernateFormatSql);
		properties.put("hibernate.show_sql", hibernateShowSql);

		return properties;
	}

	
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		return new HibernateTransactionManager(sessionFactory);

	}
	
	@Bean
	public ScJdbcDao rmsJdbcDao(DataSource dataSource) {
		return new ScJdbcDaoImpl(dataSource);
	}

	
	@Bean(name = "teamDao")
	public TeamDao getTeamDao(SessionFactory sessionFactory) {
		return new TeamDaoImpl(sessionFactory);
	}

	@Bean(name = "playerDao")
	public PlayerDao getPlayerDao(SessionFactory sessionFactory) {
		return new PlayerDaoImpl(sessionFactory);
	}
	
	@Bean(name = "gameDao")
	public GameDao getGameDao(SessionFactory sessionFactory) {
		return new GameDaoImpl(sessionFactory);
	}
	
	@Bean(name = "playerStatDao")
	public PlayerStatDao getPlayerStatDao(SessionFactory sessionFactory) {
		return new PlayerStatDaoImpl(sessionFactory);
	}
}
