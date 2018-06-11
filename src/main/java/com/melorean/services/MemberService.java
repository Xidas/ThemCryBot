package com.melorean.services;


import com.melorean.DAO.DaoMember;
import com.melorean.entity.*;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.guild.member.GenericGuildMemberEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.core.managers.GuildController;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class MemberService {

    @Resource
    private DaoMember daoMember;

    @Transactional(value = "txManagerJpa",rollbackFor = Exception.class)
    public void saveOrUpdateAllMembers(List<Member> memberList) {
        memberList.forEach(i -> daoMember.merge(new MemberEntity(i.getUser().getDiscriminator(), i.getUser().getName())));
        memberList.forEach(i -> daoMember.merge(new MemberGuildEntity(i.getUser().getDiscriminator(), i.getGuild().getId())));
        memberList.forEach(i -> i.getRoles().forEach( k -> daoMember.merge(new UserRolesEntity(i.getUser().getDiscriminator(), k.getId(), i.getGuild().getId()))));
        memberList.forEach(i -> i.getRoles().forEach(k -> daoMember.merge(new RolesEntity(k.getId(), k.getName(), k.getGuild().getId()))));

    }

    @Transactional(value = "txManagerJpa", rollbackFor = Exception.class)
    public void saveOrUpdateJoinMember(Member member) {

    }

    @Transactional(value = "txManagerJpa", rollbackFor = Exception.class)
    public void saveOrUpdateGuilds(Guild guild) {
        daoMember.merge(new GuildEntity(guild.getId(), guild.getName(), guild.getOwner().getUser().getDiscriminator(), new Date(guild.getCreationTime().toInstant().toEpochMilli())));
    }

    @Transactional(value = "txManagerJpa", rollbackFor = Exception.class)
    public void saveUserJoinOrLeave(GenericGuildMemberEvent event) {
        Member member = event.getMember();
        if (event instanceof GuildMemberJoinEvent) {
            daoMember.merge(new MemberEntity(member.getUser().getDiscriminator(), member.getUser().getName(), new Date()));
            daoMember.persist(new UserJoinLogEntity(member.getUser().getDiscriminator(), member.getGuild().getId(), "JOIN", new Date(event.getMember().getJoinDate().toInstant().toEpochMilli())));
            daoMember.persist(new MemberGuildEntity(member.getUser().getDiscriminator(), member.getGuild().getId()));
        } else if (event instanceof GuildMemberLeaveEvent) {
            daoMember.persist(new UserJoinLogEntity(member.getUser().getDiscriminator(), member.getGuild().getId(), "LEAVE", new Date()));
            daoMember.remove(new MemberGuildEntity(member.getUser().getDiscriminator(), member.getGuild().getId()));
        }
    }


}
