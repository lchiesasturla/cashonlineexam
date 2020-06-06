package com.cashonline.controller;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cashonline.repository.LoanRepository;
import com.cashonline.repository.UserRepository;
import com.cashonline.models.entity.User;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoanRepository loanRepository;

    @GetMapping("/{id}")
    public HashMap<String,Object> findOneById(@PathVariable(value = "id") Long idUser) {
        HashMap<String, Object> response = new HashMap<>();
        Optional<User> userFind = userRepository.findById(idUser);
        if(!userFind.equals(Optional.empty())){
            response.put("response", userFind);
        }else{
            response.put("error", "The user doesn't exist.");
        }
        return response;
    }

    @PostMapping("")
    public HashMap<String,Object> createUser(@Validated @RequestBody User user) {
        HashMap<String, Object> response = new HashMap<>();
        if(user.getFirstName() != null && user.getLastName() != null && user.getEmail() != null){
            userRepository.save(user);
            response.put("response", user);
        }else{
            response.put("error","All fields are required");
        }
        
        return response;
    }

    @DeleteMapping("/{id}")
    public HashMap<String, Object> deleteUser(@Validated @PathVariable(value = "id") Long idUser) {
        HashMap<String, Object> response = new HashMap<>();
        if (idUser instanceof Long) {
            if (userRepository.existsById(idUser)) {
                loanRepository.deleteByIdUser(idUser);
                userRepository.deleteById(idUser);
                response.put("deletedUser", idUser);
            } else {
                response.put("error", "The user doesn't exist.");
            }
        } else {
            response.put("error", "The id must be numeric.");
        }

        return response;
    }

}
