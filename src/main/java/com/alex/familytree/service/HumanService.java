package com.alex.familytree.service;

import com.alex.familytree.entity.Human;

import java.util.List;
import java.util.Optional;

public interface HumanService {
    List<Human> getAllHumans();
    Human saveHuman(Human human);
    Optional<Human> getHuman(int id);
    void deleteHuman(int id);
//    void assignStepparentToChild(int stepparentId, int childId);
//    int getHumanId(Human human);
//
//    void deleteStepparentFromChild(int stepparentId, int childId);
}
