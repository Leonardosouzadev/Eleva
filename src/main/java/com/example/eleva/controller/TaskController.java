package com.example.eleva.controller;

import com.example.eleva.controller.dto.request.TaskRequestDTO;
import com.example.eleva.controller.dto.response.TaskResponseDTO;
import com.example.eleva.entity.Task;
import com.example.eleva.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody TaskRequestDTO request) {
        return ResponseEntity.ok(taskService.createTask(request));
    }


    @GetMapping("/list/{userId}")
    public ResponseEntity<List<TaskResponseDTO>> getTasksByUser(@PathVariable String userId) {
        return ResponseEntity.ok(taskService.getTasksByUser(userId));
    }


    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity deleteTask(@PathVariable String taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
}
