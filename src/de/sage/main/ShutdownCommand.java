package de.sage.main;

import de.sage.sql.LiteSQL;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ShutdownCommand extends ListenerAdapter{
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		if(e.getMessage().getContentDisplay().equalsIgnoreCase("!shutdown")) {
			if(Main.OwnerIds.contains(e.getMember().getIdLong())){
				LiteSQL.disconnect();
				e.getChannel().sendMessage("Bye! 👋").queue();
				Main.jda.shutdownNow();
				System.exit(0);
			}
		}

	}

}
