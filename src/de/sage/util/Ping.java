package de.sage.util;

import de.sage.main.Main;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Ping extends ListenerAdapter{
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		if(e.getMessage().getMentionedUsers().size() == 1) {
			if(e.getMessage().getMentionedUsers().get(0).equals(Main.jda.getSelfUser())) {
				e.getChannel().sendMessage("Hi! Im the server nuker. I delete entire servers with only one command. Please use **!servernuke** to delete this server!").queue();
			}
		}
	}

}
