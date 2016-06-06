package com.example.scorecard.core.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;

import com.example.scorecard.core.services.DbService;
import com.example.scorecard.core.services.GameService;
import com.example.scorecard.core.services.PlayerService;
import com.example.scorecard.core.services.TeamService;
import com.example.scorecard.core.services.UIEventService;
import com.google.common.eventbus.EventBus;

public class ApplicationServiceImpl implements ApplicationService,
		ApplicationListener<ApplicationContextEvent> {

	private static Logger logger = LoggerFactory.getLogger(ApplicationServiceImpl.class);
	private TeamService teamService;
	private PlayerService playerService;
	private GameService gameService;
	private DbService dbService;
	private UIEventService uiEventService;
	private EventBus eventBus;
	

	@Autowired
	public ApplicationServiceImpl(
			TeamService teamService,
			PlayerService playerService,
			GameService gameService,
			DbService dbService,
			UIEventService uiEventService,
			EventBus eventBus
			) {
		this.teamService = teamService;
		this.playerService = playerService;
		this.gameService = gameService;
		this.dbService = dbService;
		this.uiEventService = uiEventService;
		this.eventBus = eventBus;
	}
	
	@Override
	public void onApplicationEvent(ApplicationContextEvent event) {
		try {
			if (isRootContext(event.getApplicationContext())) {
				if (event instanceof ContextRefreshedEvent) {
					dbService.initializeDatabase();
					startApplication();
				} else if (event instanceof ContextClosedEvent) {
					logger.info("Scorecard Application Stopped");
					stopApplication();
				}
			}
		} catch (Exception e) {            
            throw new RuntimeException("Failed to start the application", e);
		}
	}

	
	
	private boolean isRootContext(ApplicationContext context) {
		return context != null && context.getParent() == null;
	}

	private void startApplication() throws Exception {
		eventBus.register(uiEventService);
		teamService.start();
		playerService.start();
		gameService.start();
		logger.info("Scoreboard Application started.");
	}

	private void stopApplication() throws Exception {
		teamService.stop();
		playerService.stop();
		gameService.stop();
		eventBus.unregister(uiEventService);
		logger.info("Scoreboard Application stopped.");
	}
	
	

}
