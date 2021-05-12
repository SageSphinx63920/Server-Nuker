package de.sage.main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

import de.sage.sql.LiteSQL;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReadyListener extends ListenerAdapter{
	
	public void onReady(ReadyEvent e){
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				ResultSet nbRS = LiteSQL.onQuery("SELECT nb FROM number");
				try {
					int nb = nbRS.getInt("nb");
					Main.jda.getPresence().setActivity(Activity.watching(nb + " destroyed servers"));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		}, 10000, 30*1000);
		
	}

}
