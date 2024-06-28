package com.zaravya.sampleCrudOperations.controller;

import com.zaravya.sampleCrudOperations.Entity.User;
import com.zaravya.sampleCrudOperations.service.ServiceClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController

public class UserController {

    @Autowired
    private ServiceClass serviceClass;

    @GetMapping("/getbyiduser")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = Optional.ofNullable(serviceClass.findById(id));
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = serviceClass.findAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User savedUser = serviceClass.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
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
}
