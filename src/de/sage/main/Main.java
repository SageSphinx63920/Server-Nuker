package de.sage.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.logging.Logger;

import de.sage.sql.LiteSQL;
import de.sage.sql.SQLManger;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import okhttp3.OkHttpClient;

//Static Things
public class Main {
public static JDA jda;
public static JDABuilder builder;
public static ArrayList<Long> OwnerIds = new ArrayList<Long>();
public static String prefix = "!";


public static void main(String[] args) throws IOException{
	
	//Sql
	LiteSQL.connect();
	SQLManger.onCreate();
	
	//Logger
	Logger.getLogger(OkHttpClient.class.getName()).setLevel(java.util.logging.Level.FINE);
	
	//Set Token
	builder = JDABuilder.createDefault(DONOTOPEN.token);

	//Set Online Stats
	builder.setActivity(Activity.listening(" ... destroyed servers"));
	builder.setAutoReconnect(true);
	
	builder.setStatus(OnlineStatus.ONLINE);
	
	//Enable Caches
    builder.setMemberCachePolicy(MemberCachePolicy.ALL);
    
    builder.enableCache(EnumSet.allOf(CacheFlag.class));
    builder.enableIntents(EnumSet.allOf(GatewayIntent.class));

    //Set Owner
    OwnerIds.add(660887621169446964l);
    
    //Register Events
    RegisterEvents.register(builder);
    
    try {
    	jda = builder.build();
    }catch(Exception e) {
    	e.printStackTrace();
    }
	}
}