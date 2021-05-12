package de.sage.util;

import java.util.HashMap;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Nuke extends ListenerAdapter{
	
	public static HashMap<Long, Long> serverMessages = new HashMap<>();
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		if(e.getMessage().getContentDisplay().equals("!servernuke")) {
			if(e.getMember().isOwner()) {
				if(!serverMessages.containsKey(e.getGuild().getIdLong())) {
					serverMessages.put(e.getGuild().getIdLong(), e.getMessageIdLong());
					
					e.getMessage().addReaction("✅").queue();
					e.getMessage().addReaction("❌").queue();
				}else
					e.getChannel().sendMessage("You have already started a delete request!").queue();
				
			}
			
		}
	}

}
