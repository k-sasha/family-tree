package com.alex.familytree.controller;

import com.alex.familytree.entity.Human;
import com.alex.familytree.service.HumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HumanRestController {
    @Autowired
    private HumanService humanService;

    @GetMapping("/humans")
    public List<Human> showAllHumans() {
        List<Human> allHumans = humanService.getAllHumans();
        return allHumans;
    }

    @PostMapping("/humans")
    public Human addHuman(@RequestBody Human human) {
        humanService.saveHuman(human);
        return human;
    }

    @PutMapping("/humans")
    public Human updateHuman(@RequestBody Human human) {
        humanService.saveHuman(human);
        return human;
    }

    @GetMapping("/{id}")
    public Human getHuman(@PathVariable int id) {
        Human human = humanService.getHuman(id);
        return human;
    }

    @DeleteMapping("/humans/{id}")
    public String deleteHuman(@PathVariable int id) {
        humanService.deleteHuman(id);
        return "Human with id = " + id + " was deleted";
    }

    @PutMapping("/{parentId}/child/{childId}")
    public Human assignChildToParent(@PathVariable int parentId, @PathVariable int childId) {
        return humanService.assignChildToParent(parentId, childId);
    }
}
