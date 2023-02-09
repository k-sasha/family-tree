package com.alex.familytree.service;

import com.alex.familytree.dao.HumanDAO;
import com.alex.familytree.entity.Human;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HumanServiceImpl implements HumanService{
    @Autowired
    private HumanDAO humanDAO;

    @Override
    public List<Human> getAllHumans() {
        return humanDAO.getAllHumans();
    }
}
