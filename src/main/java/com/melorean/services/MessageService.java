package com.melorean.services;


import com.melorean.DAO.DaoMessage;
import net.dv8tion.jda.core.entities.Guild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicInteger;


@Service
public class MessageService {

    @Autowired
    DaoMessage daoMessage;


    @Transactional(value = "txManagerJpa", readOnly = true)
    public String getServerStat(Guild guild) {
        AtomicInteger online = new AtomicInteger();
        AtomicInteger offline = new AtomicInteger();
        guild.getMembers().forEach(i -> {
            if (!i.getOnlineStatus().toString().equals("OFFLINE")) {
                online.getAndIncrement();
            } else {
                offline.getAndIncrement();
            }
        });

        StringBuilder sb = new StringBuilder();
        sb.append("```\n---------------------------------\nПриветствую тебя, путник! \n");
        sb.append("Всего под управлением бота серверов: ").append(daoMessage.getGuilds().size());
        sb.append("\n");
        sb.append("Всего пользователей под управлением бота: ").append(daoMessage.getMembers().size());
        sb.append("\n");
        sb.append("Всего пользователей на данном сервере: ").append(guild.getMembers().size()-1);
        sb.append("\n");
        sb.append("Из них: ").append(online.get()).append(" пользователей онлайн").append(" и ").append(offline.get()).append(" пользователей оффлайн");
        sb.append("\n");
        sb.append("---------------------------------```");
        System.out.println(sb);
        return sb.toString();

    }
}
