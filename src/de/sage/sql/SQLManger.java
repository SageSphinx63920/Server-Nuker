package de.sage.sql;

public class SQLManger {

	public static void onCreate() {
		
		LiteSQL.onUpdate("CREATE TABLE IF NOT EXISTS servers(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, guildid INTEGER, time LONG)");
		LiteSQL.onUpdate("CREATE TABLE IF NOT EXISTS number(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, nb INTEGER)");
	}
	
}

