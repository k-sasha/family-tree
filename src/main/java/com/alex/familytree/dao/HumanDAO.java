package com.alex.familytree.dao;

import com.alex.familytree.entity.Human;

import java.util.ArrayList;
import java.util.List;

public interface HumanDAO {
    List<Human> getAllHumans();

    void saveHuman(Human human);

    Human getHuman(int id);

    void deleteHuman(int id);



}
