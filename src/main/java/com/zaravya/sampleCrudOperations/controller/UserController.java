package com.zaravya.sampleCrudOperations.controller;

import com.zaravya.sampleCrudOperations.Entity.User;
import com.zaravya.sampleCrudOperations.service.ServiceClass;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController

public class UserController {

    @Autowired
    private ServiceClass serviceClass;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        User user = serviceClass.findById(id);
        return user;

    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = serviceClass.findAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
        User savedUser = serviceClass.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,@Valid @RequestBody User user) {
        User updatedUser = serviceClass.update(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        serviceClass.deleteById(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping("/filter")
    public ResponseEntity<List<User>> getUsersByName(@RequestParam String name) {
        List<User> users = serviceClass.findByNames(name);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/byemail")
    public ResponseEntity<List<User>> getUsersByEmail(@RequestParam String email) {
        List<User> users = serviceClass.findByEmail(email);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/dateRange")
    public ResponseEntity<List<User>> getUsersByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        List<User> users = serviceClass.findUsersByDateRange(startDate, endDate);
        return ResponseEntity.ok(users);
    }
    @GetMapping("/pagination")
    public List<User> findAll(int page, int size) {
        return serviceClass.findAll(page, size);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> exceptionHadler(MethodArgumentNotValidException ex){
        StringBuilder errorMessage=new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach(error ->{
            errorMessage.append(error.getDefaultMessage()).append("\n");

        });
       String  finalMessage =errorMessage.toString().trim();
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(finalMessage);
    }
}
