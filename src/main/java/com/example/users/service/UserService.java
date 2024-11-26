package com.example.users.service;

import com.example.users.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private List<User> userList;

    public UserService() {
        userList = new ArrayList<User>();

    }

    public Optional<User> getUserById(Integer Id){
        Optional<User> optional = Optional.empty();

        for (User user : userList) {
            if(user.getId() == Id){
                optional = Optional.of(user);
                return optional;
            }
        }
        return optional;
    }

    public Optional<User> createUser(User user){
      Optional<User> optional = Optional.empty();
      optional= Optional.of(new User(user.getId(), user.getName(), user.getIdade(), user.getEmail()));
      userList.add(optional.get());
      return optional;
    };


    public List<User> getAllUsers() {
        return userList;
    };


    public boolean removeUserById(Integer id) {
        Optional<User> userToRemove = getUserById(id);

        if (userToRemove.isPresent()) {
            userList.remove(userToRemove.get());
            return true;
        }

        return false;
    }

    public boolean updateUser(Integer id, User user) {
        // Encontra o usuário na lista pela ID
        for (User userL : userList) {
            if (userL.getId() == id) {
                // Atualiza os campos do usuário com os novos dados
                userL.setName(user.getName());
                userL.setIdade(user.getIdade());
                userL.setEmail(user.getEmail());
                // Se houver outros campos a serem atualizados, adicione-os aqui.

                return true;  // Retorna true quando a atualização for bem-sucedida
            }
        }
        // Retorna false se o usuário não for encontrado
        return false;
    }

    public boolean userExists(Integer id) {
        // Verifica se existe algum usuário na lista com o ID fornecido
        return userList.stream().anyMatch(user -> user.getId() == id);
    }


};
