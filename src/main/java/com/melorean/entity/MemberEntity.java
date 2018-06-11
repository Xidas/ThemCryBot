package com.melorean.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "members", schema = "discord")
public class MemberEntity {


    @Id
    @Column(name = "discord_id")
    private String  discordId;

    @Column(name = "name")
    private String name;

    @Column(name = "date_write", updatable = false)
    private Date dateWrite;

    @PrePersist
    public void onCreate(){
        this.dateWrite = new Date();
    }

    public MemberEntity(String discordId, String name) {
        this.discordId = discordId;
        this.name = name;
    }
}

