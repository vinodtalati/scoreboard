package com.example.scorecard.core.services;

import static com.example.scorecard.core.dao.ScJdbcDao.CURRENT_DB_SCHEMA_VERSION;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.scorecard.core.dao.ScJdbcDao;

public class DbServiceImpl implements DbService {
	
	private static Logger logger = LoggerFactory.getLogger(DbServiceImpl.class);
	private final ScJdbcDao scJdbcDao_;
	

	@Autowired
	public DbServiceImpl(final ScJdbcDao rmsJdbcDao) {
		this.scJdbcDao_ = rmsJdbcDao;
	}
	

	@Override
	public void initializeDatabase() throws Exception {
		if (!scJdbcDao_.dbTablesExist()) {
			scJdbcDao_.createTables("create.sql");
			logger.info("The database tables have been created.");
		}
		
		Integer currentDbVersion =  scJdbcDao_.getCurrentDatabaseVersion();
		if (currentDbVersion == null) {
			throw new RuntimeException("Invalid Database Version.");
		}
		
		if (currentDbVersion < CURRENT_DB_SCHEMA_VERSION) {
			scJdbcDao_.migrateDatabase(currentDbVersion, CURRENT_DB_SCHEMA_VERSION, "db/migrate/");
		} else if (currentDbVersion > CURRENT_DB_SCHEMA_VERSION) {
			throw new Exception("Error: Database version (" + currentDbVersion + ") is newer than software version (" + CURRENT_DB_SCHEMA_VERSION + ")");
		}
		
		logger.info("The database has been initialized.");
	}
	
}
