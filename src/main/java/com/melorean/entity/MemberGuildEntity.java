package com.melorean.entity;


import com.melorean.entity.Keys.KeyForMemberGuildsEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode

@Entity
@Table(name = "member_guilds", schema = "discord")
@IdClass(KeyForMemberGuildsEntity.class)
public class MemberGuildEntity implements Serializable {

    @Id
    @Column(name = "user_discord_id")
    private String userDiscordId;

    @Id
    @Column(name = "discord_channel_id")
    private String discordChannelId;

    public MemberGuildEntity(String userDiscordId, String discordChannelId) {
        this.userDiscordId = userDiscordId;
        this.discordChannelId = discordChannelId;
    }
}
