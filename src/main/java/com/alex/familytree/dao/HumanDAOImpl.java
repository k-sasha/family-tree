package com.alex.familytree.dao;

import com.alex.familytree.entity.Human;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

@Repository
public class HumanDAOImpl implements HumanDAO {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Human> getAllHumans() {
        Query query = entityManager.createQuery("from Human");
        List<Human> allHumans = query.getResultList();
        return allHumans;
    }

    @Override
    public Human saveHuman(Human human) {
        Human newHuman = entityManager.merge(human);
        human.setId(newHuman.getId());
        return newHuman;
    }

    @Override
    public Human getHuman(int id) {
        Human human = entityManager.find(Human.class, id);
        return human;
    }

    @Override
    public void deleteHuman(int id) {
        Query query = entityManager.createQuery("delete from Human " +
                "where id=:humanId");
        query.setParameter("humanId", id);
        query.executeUpdate();
    }

    @Override
    public Human assignStepparentToChild(int stepparentId, int childId) {
        Human stepparent = entityManager.find(Human.class, stepparentId);
        Human child = entityManager.find(Human.class, childId);
        Set<Human> children = stepparent.getChildren();
        children.add(child);
        Set<Human> stepparents = child.getStepparents();
        stepparents.add(stepparent);
        entityManager.merge(stepparent);
        return entityManager.merge(child);
    }

    @Override
    public int getHumanId(Human human) {
        try {
            Query query = entityManager.createQuery("select h.id from Human h " +
                    " where h.name=:humanName and h.surname=:humanSurname and h.gender=:humanGender " +
                    " and h.dateOfBirth=:humanDataOfBirth");
            query.setParameter("humanName", human.getName());
            query.setParameter("humanSurname", human.getSurname());
            query.setParameter("humanGender", human.getGender());
            query.setParameter("humanDataOfBirth", human.getDateOfBirth());

            Integer humanId = (Integer) query.getSingleResult();
            return humanId;
        } catch (NoResultException e) {
            //TODO exception handling
            return -1;
        }
    }

    @Override
    public void deleteStepparentFromChild(int stepparentId, int childId) {
        Human stepparent = entityManager.find(Human.class, stepparentId);
        Human child = entityManager.find(Human.class, childId);
        if (stepparent == null || child == null) {
            //TODO exception handling
            return;
        }
        boolean removedChild = stepparent.getChildren().remove(child);
        boolean removedParent = child.getStepparents().remove(stepparent);
        if (removedChild || removedParent) {
            entityManager.merge(stepparent);
            entityManager.merge(child);
        }
    }
}
