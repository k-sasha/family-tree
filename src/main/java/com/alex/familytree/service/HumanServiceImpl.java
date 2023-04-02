package com.alex.familytree.service;

import com.alex.familytree.repository.HumanRepository;
import com.alex.familytree.entity.Human;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HumanServiceImpl implements HumanService{
    @Autowired
    private HumanRepository humanRepository;

    @Override
    public List<Human> getAllHumans() {
        return humanRepository.findAll();
    }

    @Override
    public Human saveHuman(Human human) {
        return humanRepository.save(human);
    }

    @Override
    public Optional <Human> getHuman(int id) {
        return humanRepository.findById(id);
    }

    @Override
    public void deleteHuman(int id) {
        humanRepository.deleteById(id);
    }

//    @Override
//    public void assignStepparentToChild(int stepparentId, int childId) {
//        humanRepository.assignStepparentToChild(stepparentId, childId);
//    }

//    @Override
//    public int getHumanId(Human human) {
//        return humanRepository.getHumanId(human);
//    }
//
//    @Override
//    public void deleteStepparentFromChild(int stepparentId, int childId) {
//        humanRepository.deleteStepparentFromChild(stepparentId, childId);
//    }
}
