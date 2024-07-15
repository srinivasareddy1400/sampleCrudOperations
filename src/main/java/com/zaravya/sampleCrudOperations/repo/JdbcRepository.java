package com.zaravya.sampleCrudOperations.repo;

import com.zaravya.sampleCrudOperations.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> findAll() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new User(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("address"),
                rs.getString("email"),
                rs.getDate("date").toLocalDate()
        ));
    }

    public User findById(Long id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setAddress(rs.getString("address"));
            Optional.ofNullable(rs.getDate("date"))
                    .ifPresent(date -> user.setDate(date.toLocalDate()));

            return user;
      });
    }



    public User save(User user) {
        String sql = "INSERT INTO user (id, name, address, email, date) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getId(), user.getName(), user.getAddress(), user.getEmail(), user.getDate());
        return user;
    }

    public User update(Long id, User user) {
        String sql = "UPDATE user SET name = ?, address = ?, email = ?, date = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getName(), user.getAddress(), user.getEmail(), user.getDate(), id);
        return user;
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM user WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<User> findByNames(String name) {
        String sql = "SELECT * FROM user WHERE name LIKE ?";
        return jdbcTemplate.query(sql, new Object[]{"%" + name + "%"}, (rs, rowNum) -> new User(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("address"),
                rs.getString("email"),
                rs.getDate("date").toLocalDate()
        ));
    }

    public List<User> findByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email LIKE ?";
        return jdbcTemplate.query(sql, new Object[]{"%" + email + "%"}, (rs, rowNum) -> new User(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("address"),
                rs.getString("email"),
                rs.getDate("date").toLocalDate()
        ));
    }

    public List<User> findUsersByDateRange(LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT * FROM user WHERE date BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, new Object[]{startDate, endDate}, (rs, rowNum) -> new User(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("address"),
                rs.getString("email"),
                rs.getDate("date").toLocalDate()
        ));
    }
    public List<User> findAll(int page, int size) {
        int offset = (page - 1) * size;
        String sql = "SELECT * FROM user LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, new Object[]{size, offset}, (rs, rowNum) -> new User(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("address"),
                rs.getString("email"),
                rs.getDate("date").toLocalDate()
        ));

    }
    }

