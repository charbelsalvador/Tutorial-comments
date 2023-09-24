package com.henrry.tutorialscomments.service;

import com.henrry.tutorialscomments.model.Comment;
import com.henrry.tutorialscomments.model.Tutorial;

import java.util.List;
import java.util.Optional;

public interface TutorialService {
    Tutorial create(Tutorial tutorial);
    Optional<Tutorial> getById(Long id);
    List<Comment> getComments(Long tutorialId);
    void delete(Long id);
    List<Tutorial>getAll();

}
