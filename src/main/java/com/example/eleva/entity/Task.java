package com.example.eleva.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "eleva", name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String taskId;

    private String taskName;

    private String taskDescription;

    private Boolean taskStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
