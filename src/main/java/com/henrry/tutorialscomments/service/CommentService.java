package com.henrry.tutorialscomments.service;

import com.henrry.tutorialscomments.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Comment create(Comment comment, Long tutorialId);
    Optional<Comment> getById(Long commentId);
    void delete(Long commentId);
    List<Comment>getAll();
    Optional<Comment>updateComment(String content, Long commentId);

}
