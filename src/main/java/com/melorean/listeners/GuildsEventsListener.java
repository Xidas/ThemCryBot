package com.melorean.listeners;

import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.springframework.stereotype.Component;

@Component
public class GuildsEventsListener extends ListenerAdapter {

   private static  int countChannels = 1;

    @Override
    public void onGuildVoiceJoin(GuildVoiceJoinEvent event)
    {
        Integer guildVoiceChannels = event.getGuild().getVoiceChannels().size();
        if (event.getChannelJoined().getMembers().size()==1){
            event.getGuild().getController().createVoiceChannel("Канальчик для игр " + countChannels).queue();
            countChannels++;
        }
        event.get
        event.getGuild().getVoiceChannels().forEach(i -> {
            if (i.getMembers().size()==0){
                event.getGuild().getDefaultChannel().sendMessage("Сука, есть свободые румы").queue();
            }
        });
    }

    @Override
    public void onGuildVoiceLeave(GuildVoiceLeaveEvent event){
        if (event.getChannelLeft().getMembers().size()==0){
            event.getGuild().getVoiceChannels().get(event.getGuild().getVoiceChannels().size()-1).delete().queue();
            countChannels--;
        }
    }
}
