package com.alex.familytree.repository;

import com.alex.familytree.entity.Human;
import com.alex.familytree.exeption_handling.NoSuchHumanException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;


public interface HumanRepository extends JpaRepository<Human, Integer> {
//    int getHumanId(Human human);
//
//    void deleteStepparentFromChild(int stepparentId, int childId);

//    default void assignStepparentToChild(int stepparentId, int childId) {
//        Human stepparent = findById(stepparentId).orElseThrow(() -> new NoSuchHumanException("There is no human with id = " + stepparentId));
//        Human child = findById(childId).orElseThrow(() -> new NoSuchHumanException("There is no human with id = " + childId));
//
////        // check that stepparent's birthday is after child's birthday, and the age difference is at least 13 years
////        LocalDate stepparentDateOfBirthday = stepparent.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
////        LocalDate childDateOfBirthday = child.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
////        Period ageDifference = Period.between(childDateOfBirthday, stepparentDateOfBirthday);
////        if (ageDifference.getYears() < 13) {
////            throw new IllegalArgumentException("age difference between stepparent and child must be at least 13 years child");
////        } else if(stepparentDateOfBirthday.isAfter(childDateOfBirthday)){
////            throw new IllegalArgumentException("stepparent can't be younger than child");
////        }
////
////        stepparent.getChildren().add(child);
////        child.getStepparents().add(stepparent);
////
////        save(stepparent);
////        save(child);
////    }

}
