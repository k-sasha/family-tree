package com.alex.familytree.controller;

import com.alex.familytree.entity.Human;
import com.alex.familytree.exeption_handling.NoSuchHumanException;
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
        if(allHumans.isEmpty()){
            throw new NoSuchHumanException("There is no human in a list");
        }
        return ResponseEntity.ok(allHumans);

    }

    @PostMapping
    public ResponseEntity<String> addHuman(@Valid @RequestBody Human human){
        Human newHuman = humanService.saveHuman(human);
        int id = newHuman.getId();
        return ResponseEntity.status(HttpStatus.CREATED).body("Human with id = " + id + " was created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Human> updateHuman(@PathVariable int id, @Valid @RequestBody Human updatedHuman) {
        Human existingHuman = humanService.getHuman(id)
                .orElseThrow(()-> new NoSuchHumanException("There is no human with id = " + id));

        existingHuman.setName(updatedHuman.getName());
        existingHuman.setSurname(updatedHuman.getSurname());
        existingHuman.setGender(updatedHuman.getGender());
        existingHuman.setDateOfBirth(updatedHuman.getDateOfBirth());

        Human savedHuman = humanService.saveHuman(existingHuman);
        return ResponseEntity.ok(savedHuman);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Human> getHuman(@PathVariable int id) {
        Human human = humanService.getHuman(id)
                .orElseThrow(()-> new NoSuchHumanException("There is no human with id = " + id));
        return ResponseEntity.ok(human);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHuman(@PathVariable int id) {
        humanService.getHuman(id)
                .orElseThrow(()-> new NoSuchHumanException("There is no human with id = " + id));
        humanService.deleteHuman(id);
        return ResponseEntity.ok("Human with id = " + id + " was deleted");
    }

//    @PutMapping("/child/{childId}/addStepparent/{stepparentId}")
//    public ResponseEntity<String> assignStepparentToChild(@PathVariable int stepparentId, @PathVariable int childId) {
//        humanService.assignStepparentToChild(stepparentId, childId);
//        return ResponseEntity.ok("Stepparent with id = " + stepparentId + " was assign to child with id = " + childId);
//    }
//
//    @PostMapping("/{childId}/stepparent")
//    public ResponseEntity<Human> creatAndAssignStepparentToChild(@RequestBody Human stepparent,
//                                                                 @PathVariable int childId) {
//        if (stepparent.getName() == null || stepparent.getName().isEmpty()
//                || stepparent.getSurname() == null || stepparent.getSurname().isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//        humanService.saveHuman(stepparent);
//        int stepparentId = humanService.getHumanId(stepparent);
//
//        Human child = humanService.getHuman(childId);
//        if (child == null ) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        humanService.assignStepparentToChild(stepparentId, childId);
//        return ResponseEntity.ok().build();
//    }
//
//    @DeleteMapping("/{childId}/stepparent/{stepparentId}")
//    public ResponseEntity <String> deleteStepparentFromChild(@PathVariable int stepparentId,
//                                                             @PathVariable int childId) {
//        Human child = humanService.getHuman(childId);
//        Human stepparent = humanService.getHuman(stepparentId);
//        if (child == null || stepparent == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//
//        humanService.deleteStepparentFromChild(stepparentId, childId);
//        return ResponseEntity.ok("Stepparent with id = " + stepparentId + " was deleted from child with id = " + childId);
//    }
}
