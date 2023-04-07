package com.alex.familytree.repository;

import com.alex.familytree.entity.Human;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HumanRepository extends JpaRepository<Human, Integer> {

     default void assignStepparentToChild(int stepparentId, int childId){}

}
