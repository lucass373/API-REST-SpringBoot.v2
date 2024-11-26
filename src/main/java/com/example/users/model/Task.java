package com.example.users.model;


public class Task {
    private Integer id; // Identificador único da tarefa
    private String title; // Título da tarefa
    private String description; // Descrição da tarefa
    private boolean completed; // Status de conclusão da tarefa
    private Integer userId; // ID do usuário associado à tarefa

    // Construtor vazio (necessário para frameworks como Spring)
    public Task() {
    }

    // Construtor com parâmetros
    public Task(Integer id, String title, String description, boolean completed, Integer userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.userId = userId;
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
