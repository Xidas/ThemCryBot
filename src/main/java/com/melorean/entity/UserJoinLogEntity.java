package com.melorean.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode

@Entity
@Table(name = "user_join_log", schema = "discord")
public class UserJoinLogEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id @Column(name = "id")
    private int id;

    @Column(name = "user_discord_id")
    private String userDiscordId;

    @Column(name = "discord_channel_id")
    private String discordChannelId;

    @Column(name = "action")
    private String action;

    @Column(name = "action_date")
    private Date actionDate;


    public UserJoinLogEntity(String userDiscordId, String discordChannelId, String action, Date actionDate) {
        this.userDiscordId = userDiscordId;
        this.discordChannelId = discordChannelId;
        this.action = action;
        this.actionDate = actionDate;
    }
}
