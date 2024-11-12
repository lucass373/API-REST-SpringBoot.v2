package com.example.users.api;

import com.example.users.model.User;
import com.example.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET /user - Recupera um usuário pelo ID
    @GetMapping(value = "/user")
    public ResponseEntity<User> getUser(@RequestParam Integer id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok) // Se o usuário for encontrado, retorna 200 OK
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND) // Se não encontrado, retorna 404 NOT FOUND
                        .body(null));
    }

    // POST /createuser - Cria um novo usuário
    @PostMapping(value = "/createuser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // Verificar se já existe um usuário com o mesmo ID
        Optional<User> existingUser = userService.getUserById(user.getId());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT) // Retorna 409 CONFLICT se o ID já existir
                    .body(null); // Não retorna corpo pois o ID já está em uso
        }

        // Caso o ID seja único, cria o novo usuário
        Optional<User> createdUser = userService.createUser(user);
        return createdUser.map(u -> ResponseEntity.status(HttpStatus.CREATED).body(u)) // Se criado, retorna 201 CREATED
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build()); // Se não criado, retorna 400 BAD REQUEST
    }

    // GET /listusers - Lista todos os usuários
    @GetMapping(value = "/listusers")
    public ResponseEntity<List<User>> listUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users); // Sempre retorna 200 OK com a lista de usuários
    }

    // DELETE /removeuser - Remove um usuário pelo ID
    @DeleteMapping(value = "/removeuser")
    public ResponseEntity<String> removeUser(@RequestParam Integer id) {
        boolean isRemoved = userService.removeUserById(id);
        if (isRemoved) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Usuário com ID " + id + " removido com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuário com ID " + id + " não encontrado.");
        }
    }

    // PUT /updateuser - Atualiza os dados de um usuário
    @PutMapping(value = "/updateuser")
    public ResponseEntity<String> updateUser(@RequestParam Integer id, @RequestBody User user) {
        boolean isUpdated = userService.updateUser(id, user);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Usuário com ID " + id + " atualizado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuário com ID " + id + " não encontrado.");
        }
    }
}
