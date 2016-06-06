package com.example.scorecard.core.dao;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.jdbc.support.DatabaseMetaDataCallback;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.MetaDataAccessException;

public class ScJdbcDaoImpl extends JdbcDaoSupport implements ScJdbcDao {
	
	private static final String TABLE = "TABLE";
	private static final String SC_STORE = "sc_store";
	private static final int _3 = 3;
	private static final int _4 = 4;
	
	
	@Autowired
	public ScJdbcDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Override
	public boolean dbTablesExist() throws Exception {
		Boolean tablesExist = (Boolean) JdbcUtils.extractDatabaseMetaData(getDataSource(),
				new DatabaseMetaDataCallback() {
					@Override
					public Object processMetaData(DatabaseMetaData dbmd) throws SQLException, MetaDataAccessException {
						ResultSet resultSet = null;
			            try{ 
						resultSet = dbmd.getTables(null, null, ScJdbcDaoImpl.SC_STORE, new String[] { TABLE });
						boolean exists = resultSet.next();
						resultSet.close();
						if (!exists) {
							resultSet = dbmd.getTables(null, null, "SC_store", new String[] { TABLE });
							exists = resultSet.next();
							resultSet.close();
						}
						
						return exists;
			            }
		            	finally{
		            	 if (resultSet != null) {
							resultSet.close();
						}
		            	}
					}
				});
		
		return tablesExist;
	}


	@Override
	public void createTables(String scriptPath) {
		Resource createSqlResource = new ClassPathResource(scriptPath);
		ScriptUtils.executeSqlScript(getConnection(), createSqlResource);
	}


	@Override
	public void migrateDatabase(int sourceDbVersion, int targetDbVersion, String migrateScriptsLocation) throws Exception {
       
            while (sourceDbVersion != targetDbVersion) {     
            	System.out.println("The database version (" + sourceDbVersion + ") does not match the current version (" + targetDbVersion + ").");
                Resource migrateResource = null;
                int target = targetDbVersion;
                
                while (target > sourceDbVersion) {
                    migrateResource = new ClassPathResource(migrateScriptsLocation + "migrate" + sourceDbVersion + "to" + target + ".sql");
                    if (migrateResource.exists()) {
                        break;
                    }
                    
                    --target;
                }
                if (migrateResource != null && migrateResource.exists()) {
                	System.out.println("The database is migrating from version " + sourceDbVersion + " to " + target);
                    try {
                    	ScriptUtils.executeSqlScript(getConnection(), migrateResource);
                    } catch (Exception e) {
                    	System.out.println("The database migration failed.");
                        throw e;
                    }
                } else {
                	throw new RuntimeException("The database can not find a migration path from version " + sourceDbVersion + " to " + targetDbVersion);
                }
                sourceDbVersion = target;
            }

            sourceDbVersion = targetDbVersion;
            System.out.println("The database finished migration to version " + targetDbVersion);
       
	}

	@Override
	public Integer getCurrentDatabaseVersion() {
		String maxVersionSql = "SELECT MAX(version) FROM " + SC_STORE;
		return getJdbcTemplate().queryForObject(maxVersionSql, Integer.class);
	}
	
    @Override
	@SuppressWarnings("unchecked")
	public Map<String, List<String>> getAllRmsTables() throws Exception {
    	Map<String, List<String>> rmsTablesMap = (Map<String, List<String>>) JdbcUtils.extractDatabaseMetaData(getDataSource(),
					new DatabaseMetaDataCallback() {
						@Override
						public Object processMetaData(DatabaseMetaData dbmd) throws SQLException, MetaDataAccessException {
							
					        Map<String, List<String>> tables = new HashMap<String, List<String>>();
					        ResultSet resultSet = null;
				            try{ 
				           resultSet = dbmd.getTables(null, null, "%", new String[] {TABLE});
				            while (resultSet.next()) {
				                String table = resultSet.getString(ScJdbcDaoImpl._3);
				                if (table.startsWith("rms_")) {
				                    tables.put(table, new LinkedList<String>());
				                }
				            }
							}
			            	finally{
			            	 if (resultSet != null) {
								resultSet.close();
							}
			            	}
					        
					        for (Map.Entry<String, List<String>> entry : tables.entrySet()) {
					            String table = entry.getKey();
					            List<String> columns = entry.getValue();
					            ResultSet rs = null;
					            try{ 
					            rs = dbmd.getColumns(null, null, table, null);
					            while (rs.next()) {
					                columns.add(rs.getString(ScJdbcDaoImpl._4));
					            }
					            }
					            finally{
					            	 if (rs != null) {
										rs.close();
									}
					            }
					            Collections.sort(columns);
					        }
					        return tables;
						}
					});
    	
    	return rmsTablesMap;
    }
    
    @Override
    @SuppressWarnings("unchecked")
	public List<String> getAllRmsSequences() throws Exception {
    	List<String> rmsSequences = (List<String>) JdbcUtils.extractDatabaseMetaData(getDataSource(),
					new DatabaseMetaDataCallback() {
						@Override
						public Object processMetaData(DatabaseMetaData dbmd) throws SQLException, MetaDataAccessException {
							List<String> sequences = new ArrayList<String>();
				            ResultSet resultSet = null;
				            try{
				            resultSet = dbmd.getTables(null, null, "%", new String[] {"SEQUENCE"});
				            if (resultSet != null) {
					            while (resultSet.next()) {
					                String sequence = resultSet.getString(ScJdbcDaoImpl._3);
					                if (sequence.startsWith("rms_")) {
					                	sequences.add(sequence);
					                }
					            }
					           
				            }
				            }
				            finally{
				            	 if (resultSet != null) {
									resultSet.close();
								}
				            }
					        
					        return sequences;
						}
					});
    	
    	return rmsSequences;
    }
    
    /**
     * Append column names.
     *
     * @param builder the builder
     * @param columns the columns
     */
    public void appendColumnNames(StringBuilder builder, List<String> columns) {
        boolean first = true;
        for (String column : columns) {
            if (first) {
                first = false;
            } else {
                builder.append(", ");
            }
            builder.append(column);
        }
    }



}
