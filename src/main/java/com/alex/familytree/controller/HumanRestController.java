package com.alex.familytree.controller;

import com.alex.familytree.entity.Human;
import com.alex.familytree.service.HumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/humans")
public class HumanRestController {
    @Autowired
    private HumanService humanService;

    @GetMapping
    public ResponseEntity<List<Human>> showAllHumans() {
        List<Human> allHumans = humanService.getAllHumans();
        return ResponseEntity.ok(allHumans);

    }

    @PostMapping
    public ResponseEntity<String> addHuman(@Valid @RequestBody Human human) {
        Human newHuman = humanService.saveHuman(human);
        int id = newHuman.getId();
        return ResponseEntity.status(HttpStatus.CREATED).body("Human with id = " + id + " was created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Human> updateHuman(@PathVariable int id, @Valid @RequestBody Human updatedHuman) {
        Human existingHuman = humanService.getHuman(id);

        existingHuman.setName(updatedHuman.getName());
        existingHuman.setSurname(updatedHuman.getSurname());
        existingHuman.setGender(updatedHuman.getGender());
        existingHuman.setDateOfBirth(updatedHuman.getDateOfBirth());

        Human savedHuman = humanService.saveHuman(existingHuman);
        return ResponseEntity.ok(savedHuman);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Human> getHuman(@PathVariable int id) {
        Human human = humanService.getHuman(id);
        return ResponseEntity.ok(human);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHuman(@PathVariable int id) {
        humanService.deleteHuman(id);
        return ResponseEntity.ok("Human with id = " + id + " was deleted");
    }

    @PutMapping("/child/{childId}/addStepparent/{stepparentId}")
    public ResponseEntity<String> assignStepparentToChild(@PathVariable int stepparentId,
                                                          @PathVariable int childId) {
        humanService.assignStepparentToChild(stepparentId, childId);
        return ResponseEntity.ok("Stepparent with id = " + stepparentId
                + " was assign to child with id = " + childId);
    }

    @PostMapping("/child/{childId}/addStepparent")
    public ResponseEntity<String> creatAndAssignStepparentToChild(@Valid @RequestBody Human newStepparent,
                                                                  @PathVariable int childId) {
        Human stepparent = humanService.saveHuman(newStepparent);
        int stepparentId = stepparent.getId();

        humanService.assignStepparentToChild(stepparentId, childId);
        return ResponseEntity.ok("Stepparent with id = " + stepparentId
                + " was assign to child with id = " + childId);
    }
}
