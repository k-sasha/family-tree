package com.alex.familytree.service;

import com.alex.familytree.dao.HumanDAO;
import com.alex.familytree.entity.Human;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class HumanServiceImpl implements HumanService{
    @Autowired
    private HumanDAO humanDAO;

    @Override
    @Transactional
    public List<Human> getAllHumans() {
        return humanDAO.getAllHumans();
    }

    @Override
    @Transactional
    public void saveHuman(Human human) {
        humanDAO.saveHuman(human);
    }

    @Override
    @Transactional
    public Human getHuman(int id) {
        return humanDAO.getHuman(id);
    }

    @Override
    @Transactional
    public void deleteHuman(int id) {
        humanDAO.deleteHuman(id);
    }

    @Override
    @Transactional
    public Human assignChildToParent(int parentId, int childId) {
        return humanDAO.assignChildToParent(parentId, childId);
    }
}
