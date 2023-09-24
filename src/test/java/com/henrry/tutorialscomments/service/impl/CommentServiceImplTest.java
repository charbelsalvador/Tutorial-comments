package com.henrry.tutorialscomments.service.impl;

import com.henrry.tutorialscomments.model.Comment;
import com.henrry.tutorialscomments.model.Tutorial;
import com.henrry.tutorialscomments.repository.CommentRepository;
import com.henrry.tutorialscomments.repository.TutorialRepository;
import com.henrry.tutorialscomments.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommentServiceImplTest {
    private CommentRepository commentRepository;
    private TutorialRepository tutorialRepository;
    private CommentService commentService;

    Comment comment;
    Tutorial tutorial;

    @BeforeEach
    void setUp(){
        commentRepository = mock(CommentRepository.class);
        tutorialRepository = mock(TutorialRepository.class);
        commentService = new CommentServiceImpl(commentRepository, tutorialRepository);

        tutorial = new Tutorial();
        tutorial.setId(123L);
        tutorial.setDescription("any-description");
        tutorial.setTitle("any-title");
        tutorial.setPublished(false);

        comment = new Comment();
        comment.setContent("any-content");
        comment.setId(123L);
        comment.setTutorial(tutorial);
    }

    @Test
    @DisplayName("given a valid comment and id tutorial wil, return ok")
    void created(){
        //given
        when(tutorialRepository.findById(anyLong())).thenReturn(Optional.of(tutorial));
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        //when
        Comment response = commentService.create(comment, 123L);

        //then
        assertNotNull(response);
        assertEquals(response.getId(), 123L);
        assertEquals(response.getContent(), "any-content");
        assertNotNull(response.getTutorial());
        assertEquals(response.getTutorial().getId(), 123L);
        assertEquals(response.getTutorial().getDescription(), "any-description");
        assertEquals(response.getTutorial().getTitle(), "any-title");
        assertFalse(response.getTutorial().isPublished());
    }

    @Test
    @DisplayName("given a valid comment but invalid tutorial will retur null")
    void created_tutorial_not_found(){
        //given
        when(tutorialRepository.findById(anyLong())).thenReturn(Optional.empty());

        //when
        Comment response = commentService.create(comment, 123L);

        //then
        assertNull(response);
        verify(tutorialRepository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Get by id")
    void getById(){
        //given
        when(commentRepository.findById(anyLong())).thenReturn(Optional.of(comment));

        //when
        Optional<Comment>response = commentService.getById(1L);

        //then
        assertNotNull(response);
    }

    @Test
    @DisplayName("Delete")
    void delete(){
        //given
        when(commentRepository.findById(anyLong())).thenReturn(Optional.of(comment));

        //when
        commentService.delete(1L);

        //then
        verify(commentRepository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Update comment")
    void update(){
        //given
        when(commentRepository.findById(anyLong())).thenReturn(Optional.of(comment));
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        //when
        Optional<Comment>response = commentService.updateComment("content123", 123L);

        //then
        assertNotNull(response);
        assertTrue(response.isPresent());
        assertEquals(response.get().getContent(),"content123");
        assertEquals(response.get().getId(), 123L);
        assertNotNull(response.get().getTutorial());
    }

    @Test
    @DisplayName("Update when is empty")
    void update_empty(){
        //given
        when(commentRepository.findById(anyLong())).thenReturn(Optional.empty());

        //when
        Optional<Comment>response = commentService.updateComment("content", 123L);

        //then
        assertNotNull(response);
        assertFalse(response.isPresent());
        verify(commentRepository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Get all")
    void getAll(){
        //given
        when(commentRepository.findAll()).thenReturn(List.of(comment));

        //when
        List<Comment>response = commentService.getAll();

        //then
        assertNotNull(response);
    }

}