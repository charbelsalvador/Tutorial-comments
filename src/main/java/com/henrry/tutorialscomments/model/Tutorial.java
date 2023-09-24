package com.henrry.tutorialscomments.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tutorials")
@Data
public class Tutorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private boolean published;
    private String title;
}
