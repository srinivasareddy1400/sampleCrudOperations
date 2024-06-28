package com.zaravya.sampleCrudOperations.service;

import com.zaravya.sampleCrudOperations.Entity.User;
import com.zaravya.sampleCrudOperations.repo.JdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ServiceClass {

    @Autowired
    private JdbcRepository jdbcRepository;

    public User save(User user) {
        return jdbcRepository.save(user);
    }

    public User findById(Long id) {
        return jdbcRepository.findById(id);
    }

    public List<User> findAll() {
        return jdbcRepository.findAll();
    }

    public void deleteById(Long id) {
        jdbcRepository.deleteById(id);
    }

    public User update(Long id, User user) {
        return jdbcRepository.update(id, user);
    }

    public List<User> findByNames(String name) {
        return jdbcRepository.findByNames(name);
    }

    public List<User> findByEmail(String email) {
        return jdbcRepository.findByEmail(email);
    }

    public List<User> findUsersByDateRange(LocalDate startDate, LocalDate endDate) {
        return jdbcRepository.findUsersByDateRange(startDate, endDate);
    }
}
