package de.sage.main;

import de.sage.util.DeleteMessageFromMap;
import de.sage.util.Nuke;
import de.sage.util.Ping;
import de.sage.util.ReactionListener;
import net.dv8tion.jda.api.JDABuilder;

public class RegisterEvents {
	
	public static void register(JDABuilder builder) {
		builder.addEventListeners(new ReactionListener());
		builder.addEventListeners(new Nuke());
		builder.addEventListeners(new ReadyListener());
		builder.addEventListeners(new DeleteMessageFromMap());
		builder.addEventListeners(new Ping());
		builder.addEventListeners(new ShutdownCommand());
	}

}
