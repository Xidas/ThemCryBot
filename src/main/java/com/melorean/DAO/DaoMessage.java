package com.melorean.DAO;


import com.melorean.entity.GuildEntity;
import com.melorean.entity.MemberEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class DaoMessage {
    @PersistenceContext(name = "entityManagerFactory")
    EntityManager em;

    public List<GuildEntity> getGuilds() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<GuildEntity> cq = cb.createQuery(GuildEntity.class);
        Root<GuildEntity> root = cq.from(GuildEntity.class);
        cq.select(root);
        return em.createQuery(cq).getResultList();
    }

    public List<MemberEntity> getMembers(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<MemberEntity> cq = cb.createQuery(MemberEntity.class);
        Root<MemberEntity> root = cq.from(MemberEntity.class);
        cq.select(root);
        return em.createQuery(cq).getResultList();
    }
}
