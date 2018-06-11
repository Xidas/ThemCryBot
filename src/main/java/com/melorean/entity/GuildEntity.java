package com.melorean.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;


@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "guilds", schema = "discord")
public class GuildEntity {

    @Id
    @Column(name = "discord_id")
    private String discordId;

    @Column(name = "name")
    private String name;

    @Column(name = "owner_id")
    private String  ownerId;

    @Column(name = "creation_date")
    private Date creationDate;


    @CreationTimestamp
    @Column(name = "date_write", updatable = false)
    private Date dateWrite;


    @PrePersist
    protected void onCreate() {
        dateWrite =  new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        dateWrite = new Date();
    }




    public GuildEntity(String discordId, String name, String  ownerId, Date creationDate) {
        this.discordId = discordId;
        this.name = name;
        this.ownerId = ownerId;
        this.creationDate = creationDate;
    }
}
