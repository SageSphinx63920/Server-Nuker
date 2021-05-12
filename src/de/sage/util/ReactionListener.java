package de.sage.util;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.sage.sql.LiteSQL;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild.BoostTier;
import net.dv8tion.jda.api.entities.Guild.ExplicitContentLevel;
import net.dv8tion.jda.api.entities.Guild.MFALevel;
import net.dv8tion.jda.api.entities.Guild.NotificationLevel;
import net.dv8tion.jda.api.entities.Guild.VerificationLevel;
import net.dv8tion.jda.api.entities.Icon;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.GuildManager;

public class ReactionListener extends ListenerAdapter{
	
	private final File temp = new File("OIP.jpg");
	
	
	public void onMessageReactionAdd(MessageReactionAddEvent e){
		if(Nuke.serverMessages.containsValue(e.getMessageIdLong())) {
			if(e.getUser().isBot()) return;
			if(e.getMember().isOwner()) {
			if(e.getReactionEmote().getEmoji().equals("✅")) {
				try {
					final Icon icon = Icon.from(temp);
				
					
					for(VoiceChannel ch : e.getGuild().getVoiceChannels()) {
						ch.delete().queue();
					}
					
					for(TextChannel ch : e.getGuild().getTextChannels()) {
						if(ch != e.getGuild().getCommunityUpdatesChannel()) {
							if(ch != e.getGuild().getRulesChannel()) {
								ch.delete().queue();
							}
						}
					}
					for(Role rl : e.getGuild().getRoles()) {
						if(!rl.isManaged()) {
							if(!rl.equals(e.getGuild().getBotRole())) {
								if(!rl.equals(e.getGuild().getPublicRole())) {
									rl.delete().queue();
								}
							}
						}
						
					}
					for(Emote emote : e.getGuild().getEmotes()) {
						System.out.println(emote);
						emote.delete().queue();
					}
					for(Category ct : e.getGuild().getCategories()) {
						ct.delete().queue();
					}
					
					for(Member member : e.getGuild().getMembers()) {
						member.modifyNickname(member.getUser().getName()).queue();
					}
					
					ResultSet nbRS = LiteSQL.onQuery("SELECT nb FROM number");
					try {
						int number = nbRS.getInt("nb");
						int temp = 1;
						int newNumber = number + temp;
						LiteSQL.onUpdate("UPDATE number SET nb = " + newNumber);
						e.getGuild().getManager().setName("Reseted Server #" + newNumber).queue();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					e.getGuild().getManager().setAfkChannel(null).queue();
					GuildManager m = e.getGuild().getManager();
					
					
					
					m.setDefaultNotificationLevel(NotificationLevel.ALL_MESSAGES).queue();
					
					m.setRequiredMFALevel(MFALevel.NONE).queue();
					
					
					m.setIcon(icon).queue();

					m.setSystemChannel(null).queue();
					
					
					if(m.getGuild().getBoostTier().equals(BoostTier.TIER_1)) {
						m.setSplash(icon).queue();
					}
					
					if(m.getGuild().getBoostTier().equals(BoostTier.TIER_2)) {
						m.setSplash(icon).queue();
						m.setBanner(icon).queue();
					}
					
					if(m.getGuild().getDescription() != null) {
						m.setDescription("").queue();
					}
					
					if(m.getGuild().getRulesChannel() != null) {
						 m.setVerificationLevel(VerificationLevel.LOW).queue();
					}else {
						m.setExplicitContentLevel(ExplicitContentLevel.OFF).queue();
						  m.setVerificationLevel(VerificationLevel.NONE).queue();
					}
					
				Nuke.serverMessages.remove(e.getGuild().getIdLong(), e.getMessageIdLong());
				LiteSQL.onUpdate("INSERT INTO servers(guildid, time) VALUES(" + e.getGuild().getIdLong() + ", " + System.currentTimeMillis() + ")");
				
				m.getGuild().createTextChannel("Nuked").queue(chan ->{
					chan.sendMessage("Server was nucked. Thanks for using you bot <3").queue();
				});
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}	

			}else if(e.getReactionEmote().getEmoji().equals("❌")) {
				Nuke.serverMessages.remove(e.getGuild().getIdLong(), e.getMessageIdLong());
				e.getChannel().retrieveMessageById(e.getMessageId()).queue(mes ->{
					mes.removeReaction("❌").queue();
					mes.removeReaction("❌", e.getMember().getUser()).queue();
					mes.removeReaction("✅").queue();
				});
			}
			}else
				e.getTextChannel().sendMessage("Only the Owner can make this decision!").queue();
		}

	}

}
