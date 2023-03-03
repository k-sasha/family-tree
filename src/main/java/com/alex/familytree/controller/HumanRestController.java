package com.alex.familytree.controller;

import com.alex.familytree.entity.Human;
import com.alex.familytree.service.HumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/humans")
public class HumanRestController {
    @Autowired
    private HumanService humanService;

    @GetMapping
    public ResponseEntity<List<Human>> showAllHumans() {
        List<Human> allHumans = humanService.getAllHumans();
        return allHumans.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.ok(allHumans);

    }

    @PostMapping
    public ResponseEntity<String> addHuman(@RequestBody Human human) {
        if (human.getName() == null || human.getName().isEmpty()
                || human.getSurname() == null || human.getSurname().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Human newHuman = humanService.saveHuman(human);
        int id = humanService.getHumanId(newHuman);
        return ResponseEntity.status(HttpStatus.CREATED).body("Human with id = " + id + " was created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Human> updateHuman(@PathVariable int id, @RequestBody Human updatedHuman) {
        Human existingHuman = humanService.getHuman(id);
        if (existingHuman == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (updatedHuman.getName() == null || updatedHuman.getName().isEmpty()
                || updatedHuman.getSurname() == null || updatedHuman.getSurname().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

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
        if (human == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(human);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHuman(@PathVariable int id) {
        Human human = humanService.getHuman(id);
        if (human == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        humanService.deleteHuman(id);
        return ResponseEntity.ok("Human with id = " + id + " was deleted");
    }

    @PutMapping("/child/{childId}/addStepparent/{stepparentId}")
    public ResponseEntity<Human> assignStepparentToChild(@PathVariable int stepparentId, @PathVariable int childId) {
        Human child = humanService.getHuman(childId);
        Human stepparent = humanService.getHuman(stepparentId);
        if (child == null || stepparent == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        humanService.assignStepparentToChild(stepparentId, childId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{childId}/stepparent")
    public ResponseEntity<Human> creatAndAssignStepparentToChild(@RequestBody Human stepparent,
                                                                 @PathVariable int childId) {
        if (stepparent.getName() == null || stepparent.getName().isEmpty()
                || stepparent.getSurname() == null || stepparent.getSurname().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        humanService.saveHuman(stepparent);
        int stepparentId = humanService.getHumanId(stepparent);

        Human child = humanService.getHuman(childId);
        if (child == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        humanService.assignStepparentToChild(stepparentId, childId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{childId}/stepparent/{stepparentId}")
    public ResponseEntity <String> deleteStepparentFromChild(@PathVariable int stepparentId,
                                                             @PathVariable int childId) {
        Human child = humanService.getHuman(childId);
        Human stepparent = humanService.getHuman(stepparentId);
        if (child == null || stepparent == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        humanService.deleteStepparentFromChild(stepparentId, childId);
        return ResponseEntity.ok("Stepparent with id = " + stepparentId + " was deleted from child with id = " + childId);
    }
}
