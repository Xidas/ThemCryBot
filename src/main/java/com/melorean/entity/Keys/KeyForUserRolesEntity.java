package com.melorean.entity.Keys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class KeyForUserRolesEntity implements Serializable {

    private String userDiscordId;

    private String roleId;

    private String discordChannelId;
}
