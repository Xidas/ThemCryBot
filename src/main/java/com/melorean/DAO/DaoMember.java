package com.melorean.DAO;

import com.melorean.entity.MemberGuildEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

@Repository
public class DaoMember {


    @PersistenceContext(name = "entityManagerFactory")
    EntityManager em;

    public void merge(Object entity) {
        em.merge(entity);
        em.flush();
    }

    public void persist(Object entity) {
        em.persist(entity);
    }

    public void remove(Object entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }

    public void deleteUserGuild(String userId, String channelId){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<MemberGuildEntity> cd = cb.createCriteriaDelete(MemberGuildEntity.class);
        Root<MemberGuildEntity> root = cd.from(MemberGuildEntity.class);
        cd.where(cb.equal(root.get("userDiscordId"), userId));
        cb.and(cb.equal(root.get("discordChannelId"), channelId));
        em.createQuery(cd).executeUpdate();
    }

}
