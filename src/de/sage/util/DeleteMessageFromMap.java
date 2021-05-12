package de.sage.util;

import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DeleteMessageFromMap extends ListenerAdapter{
	
	public void onMessageDelete(MessageDeleteEvent e) {
		if(Nuke.serverMessages.containsKey(e.getGuild().getIdLong())) {
			Nuke.serverMessages.remove(e.getGuild().getIdLong(), e.getMessageIdLong());
		}
	}

}
