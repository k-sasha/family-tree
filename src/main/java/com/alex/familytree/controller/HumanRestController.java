package com.alex.familytree.controller;

import com.alex.familytree.entity.Human;
import com.alex.familytree.service.HumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HumanRestController {
    @Autowired
    private HumanService humanService;

    @GetMapping("/humans")
    public List<Human> showAllHumans(){
        List<Human> allHumans = humanService.getAllHumans();
        return allHumans;
    }
}
