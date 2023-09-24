package com.henrry.tutorialscomments.controller;

import com.henrry.tutorialscomments.model.Comment;
import com.henrry.tutorialscomments.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/comment")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("create/{tutorialId}")
    public ResponseEntity<Comment> create(@RequestBody Comment comment, @PathVariable Long tutorialId) {
        Comment commentSaved = this.commentService.create(comment, tutorialId);
        return ResponseEntity.ok(commentSaved);
    }

    @GetMapping("{id}")
    public ResponseEntity<Comment> getComment(@PathVariable Long id) {
        Optional<Comment> commentFound = this.commentService.getById(id);
        if (commentFound.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(commentFound.get());
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void>commentDelete(@PathVariable Long id){
        this.commentService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/all")
    public ResponseEntity<List<Comment>>getAll(Comment comment){
        List<Comment>commentsAll=this.commentService.getAll();
        return ResponseEntity.ok(commentsAll);
    }
    @PutMapping("Update/{id}")
    public ResponseEntity<Comment>commentUpdate(@RequestParam("content")String content,
                                                @PathVariable Long commentId){
        Optional<Comment>commentUpdate = this.commentService.updateComment(content, commentId);
        if (commentUpdate.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(commentUpdate.get());
    }
}
