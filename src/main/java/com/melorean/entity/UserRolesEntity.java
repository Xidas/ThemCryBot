package com.melorean.entity;


import com.melorean.entity.Keys.KeyForUserRolesEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

@Entity
@Table(name = "user_roles", schema = "discord")
@IdClass(KeyForUserRolesEntity.class)
public class UserRolesEntity {

    @Id @Column(name = "user_discord_id")
    private String userDiscordId;

    @Id @Column(name = "role_id")
    private String roleId;

    @Id @Column(name = "discord_channel_id")
    private String discordChannelId;

}
