package com.example.eleva.service;

import com.example.eleva.controller.dto.request.TaskRequestDTO;
import com.example.eleva.controller.dto.response.TaskResponseDTO;
import com.example.eleva.entity.Task;
import com.example.eleva.entity.User;
import com.example.eleva.repository.TaskRepository;
import com.example.eleva.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public TaskResponseDTO createTask(TaskRequestDTO request) {

        User newUser = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Task task = new Task();
        task.setTaskName(request.taskName());
        task.setTaskDescription(request.taskDescription());
        task.setTaskStatus(Boolean.FALSE);
        task.setUser(newUser);

        newUser.getTasks().add(task);

        Task savedTask = taskRepository.save(task);

        return new TaskResponseDTO(savedTask);
    }


    public List<TaskResponseDTO> getTasksByUser(String userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return user.getTasks()
                .stream()
                .map(TaskResponseDTO::new)
                .toList();
    }


    public void deleteTask(String taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task não encontrada"));
        taskRepository.delete(task);
    }
}
