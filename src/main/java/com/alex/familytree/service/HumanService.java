package com.alex.familytree.service;

import com.alex.familytree.entity.Human;

import java.util.List;

public interface HumanService {
    List<Human> getAllHumans();
    void saveHuman(Human human);
    Human getHuman(int id);
    void deleteHuman(int id);
    Human assignStepparentToChild(int stepparentId, int childId);
    int getHumanId(Human human);

    void deleteStepparentFromChild(int stepparentId, int childId);
}
