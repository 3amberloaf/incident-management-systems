package com.AmberSautner.incidentmanagementsystem.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role;

    @OneToMany(mappedBy = "assignedUser")
    private List<Incident> assignedIncidents;

    // Getters and Setters
}
