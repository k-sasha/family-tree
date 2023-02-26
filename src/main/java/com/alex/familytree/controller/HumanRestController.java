package com.alex.familytree.controller;

import com.alex.familytree.entity.Human;
import com.alex.familytree.service.HumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/humans")
public class HumanRestController {
    @Autowired
    private HumanService humanService;

    @GetMapping
    public List<Human> showAllHumans() {
        List<Human> allHumans = humanService.getAllHumans();
        return allHumans;
    }

    @PostMapping
    public Human addHuman(@RequestBody Human human) {
        humanService.saveHuman(human);
        return human;
    }

    @PutMapping
    public Human updateHuman(@RequestBody Human human) {
        humanService.saveHuman(human);
        return human;
    }

    @GetMapping("/{id}")
    public Human getHuman(@PathVariable int id) {
        Human human = humanService.getHuman(id);
        return human;
    }

    @DeleteMapping("/{id}")
    public String deleteHuman(@PathVariable int id) {
        humanService.deleteHuman(id);
        return "Human with id = " + id + " was deleted";
    }

    @PutMapping("/{childId}/stepparent/{stepparentId}")
    public Human assignStepparentToChild(@PathVariable int stepparentId, @PathVariable int childId) {
        return humanService.assignStepparentToChild(stepparentId, childId);
    }

    @PostMapping("/{childId}/stepparent")
    public Human creatAndAssignStepparentToChild(@RequestBody Human stepparent, @PathVariable int childId) {
        humanService.saveHuman(stepparent);
        int stepparentId = humanService.getHumanId(stepparent);
        humanService.assignStepparentToChild(stepparentId, childId);
        return humanService.assignStepparentToChild(stepparentId, childId);
    }

    @DeleteMapping("/{childId}/stepparent/{stepparentId}")
    public String deleteStepparentFromChild(@PathVariable int stepparentId, @PathVariable int childId) {
        humanService.deleteStepparentFromChild(stepparentId, childId);
        return "Stepparent with id = " + stepparentId + " was deleted from child with id = " + childId;
    }
}
