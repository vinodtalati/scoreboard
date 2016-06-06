package com.example.scorecard.core.dao;

import java.util.List;
import java.util.Map;




//Note: This DAO class should only be used when it is needed to run DDL statements
// using Spring jdbc support like during Creation or migration of database.

public interface ScJdbcDao {
	
	static final int CURRENT_DB_SCHEMA_VERSION = 1;
	
	/**
	 * Db tables exist.
	 *
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	boolean dbTablesExist() throws Exception;
	
	/**
	 * Creates the tables.
	 *
	 * @param scriptPath the script path
	 */
	void createTables(String scriptPath);
	
	/**
	 * Migrate database.
	 *
	 * @param sourceDbVersion the source db version
	 * @param targetDbVersion the target db version
	 * @param migrateScriptsLocation the migrate scripts location
	 * @throws Exception the exception
	 */
	void migrateDatabase(int sourceDbVersion, int targetDbVersion, String migrateScriptsLocation) throws Exception;
	
	Integer getCurrentDatabaseVersion();
	
	List<String> getAllRmsSequences() throws Exception;
	
	Map<String, List<String>> getAllRmsTables() throws Exception;
}
