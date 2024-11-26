package com.example.users.api;

import com.example.users.model.Task;
import com.example.users.service.TaskService;
import com.example.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users/{userId}/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    @Autowired
    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    // POST /users/{userId}/tasks - Criar uma nova tarefa para um usuário
    @PostMapping
    public ResponseEntity<Task> createTask(@PathVariable Integer userId, @RequestBody Task task) {
        if (!userService.userExists(userId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // Usuário não encontrado
        }

        Optional<Task> createdTask = taskService.createTaskForUser(userId, task);
        return createdTask.map(t -> ResponseEntity.status(HttpStatus.CREATED).body(t))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    // GET /users/{userId}/tasks/{taskId} - Obter uma tarefa específica de um usuário
    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable Integer userId, @PathVariable Integer taskId) {
        if (!userService.userExists(userId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // Usuário não encontrado
        }

        Optional<Task> task = taskService.getTaskByUserIdAndTaskId(userId, taskId);
        return task.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // Tarefa não encontrada
    }

    // GET /users/{userId}/tasks - Listar todas as tarefas de um usuário
    @GetMapping
    public ResponseEntity<List<Task>> listTasks(@PathVariable Integer userId) {
        if (!userService.userExists(userId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // Usuário não encontrado
        }

        List<Task> tasks = taskService.getAllTasksByUserId(userId);
        return ResponseEntity.ok(tasks); // Retorna lista de tarefas
    }

    // PUT /users/{userId}/tasks/{taskId} - Atualizar uma tarefa de um usuário
    @PutMapping("/{taskId}")
    public ResponseEntity<String> updateTask(@PathVariable Integer userId, @PathVariable Integer taskId, @RequestBody Task updatedTask) {
        if (!userService.userExists(userId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuário com ID " + userId + " não encontrado.");
        }

        boolean isUpdated = taskService.updateTaskForUser(userId, taskId, updatedTask);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Tarefa com ID " + taskId + " atualizada com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Tarefa com ID " + taskId + " não encontrada.");
        }
    }

    // DELETE /users/{userId}/tasks/{taskId} - Remover uma tarefa de um usuário
    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> removeTask(@PathVariable Integer userId, @PathVariable Integer taskId) {
        if (!userService.userExists(userId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuário com ID " + userId + " não encontrado.");
        }

        boolean isRemoved = taskService.removeTaskByUserIdAndTaskId(userId, taskId);
        if (isRemoved) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Tarefa com ID " + taskId + " removida com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Tarefa com ID " + taskId + " não encontrada.");
        }
    }
}
