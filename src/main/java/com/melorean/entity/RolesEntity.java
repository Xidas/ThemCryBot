package com.melorean.entity;


import com.melorean.entity.Keys.KeyForRolesEntity;
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
@Table(name = "roles", schema = "discord")
@IdClass(KeyForRolesEntity.class)
public class RolesEntity {

    @Id @Column(name = "role_id")
    private String roleId;
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "discord_channel_id")
    private String discordChannelId;

}
