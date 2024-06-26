package com.zaravya.sampleCrudOperations.controller;

import com.zaravya.sampleCrudOperations.Entity.User;
import com.zaravya.sampleCrudOperations.service.ServiceClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class UserController {


    @Autowired
    private ServiceClass serviceClass;

    @GetMapping("/names/{id}")
    public Optional<User> getname(@PathVariable Long id) {
        return serviceClass.getnamebyid(id);
    }

    @GetMapping("/name")
    public List<User> getuserss() {
        return serviceClass.getnames();
    }
@PostMapping("/saving")
    public User savename(@RequestBody User user) {
    return serviceClass.Savename( user);
    }
   @PutMapping("/{id}")
   public User updateEntity(@PathVariable Long id,@RequestBody User user) {
      return serviceClass.updateUser(id,user);
  }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        serviceClass.deletebyid(id);
        return ResponseEntity.ok().body("User deleted successfully");
    }
}
