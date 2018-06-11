package com.melorean.listeners;


import com.melorean.services.MemberService;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReadyListener extends ListenerAdapter {


    @Autowired
    private MemberService service;

    @Override
    public void onReady(ReadyEvent event){
        event.getJDA().getGuilds().forEach(i -> service.saveOrUpdateAllMembers(i.getMembers()));
        event.getJDA().getGuilds().forEach(i -> service.saveOrUpdateGuilds(i) );
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event){
        service.saveUserJoinOrLeave(event);
        service.saveOrUpdateJoinMember(event.getMember());
    }

    @Override
    public void onGuildMemberLeave(GuildMemberLeaveEvent event){
        service.saveUserJoinOrLeave(event);
    }




}
