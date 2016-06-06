package com.example.scorecard.core.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ScoreCardServiceImpl implements ScorecardService {
	
	private static Logger logger = LoggerFactory.getLogger(ScoreCardServiceImpl.class);
    protected final String serviceName;
    
	public ScoreCardServiceImpl(String serviceName) {
        this.serviceName = serviceName;
    }
    
    @Override
    public void start() throws Exception {
    	logger.info("Service.Started: " + serviceName);
    }
    
   
    @Override
    public void stop() {
    	logger.info("Service.Stopped: " + serviceName);
    }

}
