package com.example.users.model;

public class User {
    public int id;
    public String name;
    public int idade;
    public String email;

    public User(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.idade = age;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public int getIdade() {
        return idade;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
