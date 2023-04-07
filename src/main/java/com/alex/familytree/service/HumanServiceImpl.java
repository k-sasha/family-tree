package com.alex.familytree.service;

import com.alex.familytree.exeption_handling.NoSuchHumanException;
import com.alex.familytree.repository.HumanRepository;
import com.alex.familytree.entity.Human;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

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
    public Human getHuman(int id) {
        Human existingHuman = humanRepository.findById(id)
                .orElseThrow(()-> new NoSuchHumanException("There is no human with id = " + id));
        return existingHuman;
    }

    @Override
    public void deleteHuman(int id) {
        humanRepository.findById(id)
                .orElseThrow(()-> new NoSuchHumanException("There is no human with id = " + id));
        humanRepository.deleteById(id);
    }

    @Override
    public void assignStepparentToChild(int stepparentId, int childId) {
        Human stepparent = humanRepository.findById(stepparentId).orElseThrow(() -> new NoSuchHumanException("There is no human with id = " + stepparentId));
        Human child = humanRepository.findById(childId).orElseThrow(() -> new NoSuchHumanException("There is no human with id = " + childId));

        // check that stepparent's birthday is after child's birthday, and the age difference is at least 13 years
        LocalDate stepparentDateOfBirthday = stepparent.getDateOfBirth();
        LocalDate childDateOfBirthday = child.getDateOfBirth();
        Period ageDifference = Period.between(stepparentDateOfBirthday, childDateOfBirthday);

        if (stepparentDateOfBirthday.isAfter(childDateOfBirthday)) {
            throw new IllegalArgumentException("stepparent can't be younger than child. "
                    + "Stepparent's date of birth is " + stepparentDateOfBirthday
                    + ". Child's date of birth is " + childDateOfBirthday);
        } else if (ageDifference.getYears() < 13) {
            throw new IllegalArgumentException("age difference between stepparent and child is " + ageDifference.getYears() + " year(s), but it must be at least 13 years");
        }

        stepparent.getChildren().add(child);
        child.getStepparents().add(stepparent);

        humanRepository.save(stepparent);
        humanRepository.save(child);
    }

}
