package com.example.users.service;
import com.example.users.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final List<Task> taskList;

    public TaskService() {
        this.taskList = new ArrayList<>();
    }

    // Recupera uma tarefa pelo ID do usuário e ID da tarefa
    public Optional<Task> getTaskByUserIdAndTaskId(Integer userId, Integer taskId) {
        return taskList.stream()
                .filter(task -> task.getUserId().equals(userId) && task.getId().equals(taskId))
                .findFirst();
    }

    // Cria uma nova tarefa para um usuário
    public Optional<Task> createTaskForUser(Integer userId, Task task) {
        // Define o ID do usuário para a tarefa
        task.setUserId(userId);

        // Adiciona a tarefa à lista
        taskList.add(task);
        return Optional.of(task);
    }

    // Retorna todas as tarefas associadas a um usuário
    public List<Task> getAllTasksByUserId(Integer userId) {
        List<Task> userTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getUserId().equals(userId)) {
                userTasks.add(task);
            }
        }
        return userTasks;
    }

    // Atualiza uma tarefa associada a um usuário
    public boolean updateTaskForUser(Integer userId, Integer taskId, Task updatedTask) {
        for (Task task : taskList) {
            if (task.getUserId().equals(userId) && task.getId().equals(taskId)) {
                task.setTitle(updatedTask.getTitle());
                task.setDescription(updatedTask.getDescription());
                task.setCompleted(updatedTask.isCompleted());
                return true;
            }
        }
        return false;
    }

    // Remove uma tarefa associada a um usuário
    public boolean removeTaskByUserIdAndTaskId(Integer userId, Integer taskId) {
        Optional<Task> taskToRemove = getTaskByUserIdAndTaskId(userId, taskId);
        if (taskToRemove.isPresent()) {
            taskList.remove(taskToRemove.get());
            return true;
        }
        return false;
    }
}
