package com.silicolife.anote2daemon.utils;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

import com.silicolife.textmining.core.datastructures.dataaccess.database.DatabaseFactory;
import com.silicolife.textmining.core.datastructures.init.exception.InvalidDatabaseAccess;
import com.silicolife.textmining.core.interfaces.core.dataaccess.database.DataBaseTypeEnum;
import com.silicolife.textmining.core.interfaces.core.dataaccess.database.IDatabase;

public class GenerateIntegratedDB {
	
	public static void main(String[] args) throws Exception {
		if(args.length != 4) {
			String message = "The GenerateIntegratedDB command must have <H2databasePath> <schema> <username> <password>, given:";
	        for (String s: args) 
	        	message = message + " " + s;
	        
	        throw new Exception(message);
		}
		
		String databasePath = args[0];
		String schema = args[1];
		String username = args[2];
		String password = args[3];
		
		DataBaseTypeEnum databaseType = DataBaseTypeEnum.H2Embedded;
		if (databasePath.trim().equals("") || schema.trim().equals("")) {
			if(databasePath.isEmpty())
			{
				throw new InvalidDatabaseAccess("Host can not be empty ('./' by default)");
			}
			if(schema.isEmpty())
			{
				throw new InvalidDatabaseAccess("Schema can not be empty");
			}

		} else {
			System.out.println("Connecting H2 database at " + databasePath + " with DB schema " + schema + " and credentials " + username + " " + password);
			IDatabase database = DatabaseFactory.createDatabase(databaseType, databasePath, null, schema, username, password);
			database.createDataBase();
			if(!database.isfill())
			{
				System.out.println("The database is empty, filling with tables!");
				URL res = GenerateIntegratedDB.class.getClassLoader().getResource("database/h2_anote2databasescript.sql");
				File file = Paths.get(res.toURI()).toFile();
				database.fillDataBaseTables(file.getAbsolutePath());
			}
			System.out.println("Embeded database generation complete!");
		}
	}
}
