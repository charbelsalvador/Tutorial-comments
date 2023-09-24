package com.henrry.tutorialscomments.controller;

import com.henrry.tutorialscomments.model.Comment;
import com.henrry.tutorialscomments.model.Tutorial;
import com.henrry.tutorialscomments.service.TutorialService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/tutorials")
@AllArgsConstructor
public class TutorialController {
    private final TutorialService tutorialService;

    @GetMapping("{id}/comments")
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long id) {
        List<Comment> comments = this.tutorialService.getComments(id);
        if(comments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(comments);
    }

    @PostMapping("/create")
    public ResponseEntity<Tutorial> create(@RequestBody Tutorial tutorial) {
        Tutorial tutorialCreated = this.tutorialService.create(tutorial);
        return ResponseEntity.ok(tutorialCreated);
    }

    @GetMapping("{id}")
    public ResponseEntity<Tutorial>getById(@PathVariable Long id){

        Optional<Tutorial> tutorialGetById=this.tutorialService.getById(id);
        if (tutorialGetById.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tutorialGetById.get());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Tutorial>>getAll(Tutorial tutorial){
        List<Tutorial>getAll = this.tutorialService.getAll();
        return ResponseEntity.ok(getAll);
    }
}
