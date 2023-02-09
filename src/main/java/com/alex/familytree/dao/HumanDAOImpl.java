package com.alex.familytree.dao;

import com.alex.familytree.entity.Human;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class HumanDAOImpl implements HumanDAO{
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Human> getAllHumans() {
        Query query = entityManager.createQuery("from Human");
        List<Human> allHumans = query.getResultList();
        return allHumans;
    }
}
