package com.example.users.api;
import com.example.users.model.User;
import com.example.users.service.UserService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "https://api-rest-springbootv2-production.up.railway.app")
    @GetMapping(value = "/user")
    public User getUser(@RequestParam Integer id) {
        Optional<User> user = userService.getUserById(id);
        return (User) user.orElse(null);
    }


    @CrossOrigin(origins = "https://api-rest-springbootv2-production.up.railway.app")
    @PostMapping(value = "/createuser")
    public User createUser(@RequestBody User user) {
        Optional<User> createdUser = userService.createUser(user);
        return createdUser.orElse(null);
    }

    @CrossOrigin(origins = "https://api-rest-springbootv2-production.up.railway.app")
    @GetMapping(value = "/listusers")
    public List<User> listUsers() {
        return userService.getAllUsers();
    }

    @CrossOrigin(origins = "https://api-rest-springbootv2-production.up.railway.app")
    @DeleteMapping (value = "/removeuser")
    public String removeUser(@RequestParam Integer id) {
        boolean isRemoved = userService.removeUserById(id);

        if (isRemoved) {
            return "Usuário com ID " + id + " removido com sucesso.";
        } else {
            return "Usuário com ID " + id + " não encontrado.";
        }
    }

    @CrossOrigin(origins = "https://api-rest-springbootv2-production.up.railway.app")
    @PutMapping (value = "/updateuser")
    public String updateUser(@RequestParam Integer id, @RequestBody User user) {
        boolean isUpdated = userService.updateUser(id, user);

        if (isUpdated) {
            return "Usuário com ID " + id + " Atualizado com sucesso.";
        } else {
            return "Usuário com ID " + id + " não encontrado.";
        }
    }
}
