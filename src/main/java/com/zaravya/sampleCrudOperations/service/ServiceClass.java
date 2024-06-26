package com.zaravya.sampleCrudOperations.service;

import com.zaravya.sampleCrudOperations.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceClass {

//     private  RepoInterface repoInterface;
    @Autowired
private JdbcTemplate jdbcTemplate;
    public User Savename( User user) {
        String sql ="INSERT INTO User(id,name,address,email) VALUES(?,?,?,?)";
        jdbcTemplate.update(sql,user.getId(),user.getName(),user.getEmail(),user.getAddress());
        return user;
    }

    public Optional<User> getnamebyid(Long id) {
        String sql="SELECT * FROM user WHERE id=?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (rs, rowNum) -> new User(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("address")
                )
        ));
    }

    public List<User> getnames() {String sql = "SELECT * FROM user WHERE id = ?";
        return jdbcTemplate.query("SELECT * FROM user",
                (rs, rowNum) ->
                        new User(rs.getLong("id"),
                                rs.getString("name"), rs.getString("email"),rs.getString("address")));


    }

    public void deletebyid(Long id) {

        String sql="DELETE FROM USER WHERE ID=?";
        jdbcTemplate.update(sql,id);

    }
    public User  updateUser(Long id,  User user) {

        String sql = "UPDATE User SET name=?, address=?, email=? WHERE id=?";
        jdbcTemplate.update(sql, user.getName(), user.getAddress(), user.getEmail(), user.getId());
        return user;
    }

}

