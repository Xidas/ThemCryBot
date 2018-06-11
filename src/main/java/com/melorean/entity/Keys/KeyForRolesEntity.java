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
public class KeyForRolesEntity implements Serializable {

    private String roleId;
    private String roleName;
    private String discordChannelId;

}
