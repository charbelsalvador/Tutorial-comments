package com.henrry.tutorialscomments.service.impl;

import com.henrry.tutorialscomments.model.Comment;
import com.henrry.tutorialscomments.model.Tutorial;
import com.henrry.tutorialscomments.repository.CommentRepository;
import com.henrry.tutorialscomments.repository.TutorialRepository;
import com.henrry.tutorialscomments.service.TutorialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class TutorialServiceImplTest {
    private CommentRepository commentRepository;
    private TutorialRepository tutorialRepository;
    private TutorialService tutorialService;

    Tutorial tutorial;
    Comment comment;

    @BeforeEach
    void setUp(){
        tutorialRepository = mock(TutorialRepository.class);
        commentRepository = mock(CommentRepository.class);
        tutorialService = new TutorialServiceImpl(commentRepository, tutorialRepository);

       tutorial = new Tutorial();
       tutorial.setId(123L);
       tutorial.setDescription("any-description");
       tutorial.setTitle("any-title");
       tutorial.setPublished(false);

       comment = new Comment();
       comment.setId(123L);
       comment.setContent("any-content");
       comment.setTutorial(tutorial);
    }

    @Test
    @DisplayName("create tutorial and will return ok")
    void created(){
        //given
        when(tutorialRepository.save(any(Tutorial.class))).thenReturn(tutorial);

        //when
        Tutorial response = tutorialService.create(tutorial);

        //then
        assertEquals(response.getId(), 123L);
        assertEquals(response.getDescription(), "any-description");
        assertEquals(response.getTitle(), "any-title");
        assertFalse(response.isPublished());
    }

    @Test
    @DisplayName("get by Id")
    void get_by_Id(){
        //given
        when(tutorialRepository.findById(anyLong())).thenReturn(Optional.of(tutorial));

        //when
        Optional<Tutorial>response = tutorialService.getById(123L);

        //then
        assertNotNull(response);
    }

    @Test
    @DisplayName("gets all comments by tutorial Id")
    void get_all_comments(){
        //given
        when(commentRepository.findAllByTutorialId(anyLong())).thenReturn(List.of(comment));

        //when
        List<Comment>response = tutorialService.getComments(123L);

        //then
        assertNotNull(response);
    }

    @Test
    @DisplayName("Delete")
    void delete(){
        //given
        when(tutorialRepository.findById(anyLong())).thenReturn(Optional.of(tutorial));

        //when
        tutorialService.delete(123L);

        //then
        verify(tutorialRepository, times(1)).deleteById(anyLong());
    }

    @Test
    @DisplayName("get all")
    void get_all(){
        //given
        when(tutorialRepository.findAll()).thenReturn(List.of(tutorial));

        //when
        List<Tutorial>response = tutorialService.getAll();

        //then
        assertNotNull(response);
    }
}