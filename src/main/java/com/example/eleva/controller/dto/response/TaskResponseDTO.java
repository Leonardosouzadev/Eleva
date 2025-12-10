package com.example.eleva.controller.dto.response;

import com.example.eleva.entity.Task;

public record TaskResponseDTO(String taskName, String taskDescription, Boolean taskStatus) {

    public TaskResponseDTO(Task task){
        this(
                task.getTaskName(),
                task.getTaskDescription(),
                task.getTaskStatus()
        );
    }
}
