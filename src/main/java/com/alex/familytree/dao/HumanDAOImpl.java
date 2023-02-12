package com.alex.familytree.dao;

import com.alex.familytree.entity.Human;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
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
    public void saveHuman(Human human) {
        Human newHuman = entityManager.merge(human);
        human.setId(newHuman.getId());
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
    public Human assignChildToParent(int parentId, int childId) {
        Human parent = entityManager.find(Human.class, parentId);
        Human child = entityManager.find(Human.class, childId);
        Set<Human> children = parent.getChildren();
        children.add(child);
        Set<Human> parents = child.getParents();
        parents.add(parent);
        entityManager.merge(child);
        return entityManager.merge(parent);
    }
}
