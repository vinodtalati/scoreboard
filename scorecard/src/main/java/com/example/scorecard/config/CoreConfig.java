package com.example.scorecard.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.example.scorecard.adapter.RestAdapterVer1;
import com.example.scorecard.adapter.RestAdapterVer1Impl;
import com.example.scorecard.core.app.ApplicationService;
import com.example.scorecard.core.app.ApplicationServiceImpl;
import com.example.scorecard.core.dao.GameDao;
import com.example.scorecard.core.dao.PlayerDao;
import com.example.scorecard.core.dao.PlayerStatDao;
import com.example.scorecard.core.dao.ScJdbcDao;
import com.example.scorecard.core.dao.TeamDao;
import com.example.scorecard.core.services.DbService;
import com.example.scorecard.core.services.DbServiceImpl;
import com.example.scorecard.core.services.GameService;
import com.example.scorecard.core.services.GameServiceImpl;
import com.example.scorecard.core.services.PlayerService;
import com.example.scorecard.core.services.PlayerServiceImpl;
import com.example.scorecard.core.services.TeamService;
import com.example.scorecard.core.services.TeamServiceImpl;
import com.example.scorecard.core.services.UIEventService;
import com.example.scorecard.core.services.UIEventServiceImpl;
import com.google.common.eventbus.EventBus;


@Configuration
@Import({ DaoConfig.class })
@PropertySources({
		@PropertySource("classpath:application.properties"),
		@PropertySource(value = "classpath:override.properties", ignoreResourceNotFound = true), })
public class CoreConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public DbService dbService(ScJdbcDao scJdbcDao) {
		return new DbServiceImpl(scJdbcDao);
	}

	
	@Bean
	public EventBus eventBus() {
		return new EventBus("eventbus.scorecard");
	}
	
	@Autowired
	@Bean
	public UIEventService uiEventService(SimpMessagingTemplate template) {
		return new UIEventServiceImpl(template);
	}
	
	@Bean
	public TeamService teamService(TeamDao teamDao, EventBus eventBus) {
		return new TeamServiceImpl(teamDao, eventBus);
	}

	@Bean
	public PlayerService playerService(PlayerDao playerDao,
			PlayerStatDao playerStatDao) {
		return new PlayerServiceImpl(playerDao, playerStatDao);
	}

	@Bean
	public GameService gameService(GameDao gameDao) {
		return new GameServiceImpl(gameDao);
	}

	@Bean
	public RestAdapterVer1 restAdapterVer1(TeamService teamService,
			PlayerService playerService, GameService gameService) {
		return new RestAdapterVer1Impl(teamService, playerService, gameService);
	}

	@Bean
	public ApplicationService applicationService(TeamService teamService,
			PlayerService playerService, GameService gameService,
			DbService dbService, UIEventService uiEventService, EventBus eventBus) {
		return new ApplicationServiceImpl(teamService, playerService,
				gameService, dbService, uiEventService, eventBus);
	}

}
