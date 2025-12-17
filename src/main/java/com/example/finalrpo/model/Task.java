package com.example.finalrpo.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "task_project",
            joinColumns = @JoinColumn(name  = "task_id"),
            inverseJoinColumns = @JoinColumn( name = "project_id")
    )
    private List<Project> projects;
}
