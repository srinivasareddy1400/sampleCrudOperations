package com.zaravya.sampleCrudOperations.repo;

import com.zaravya.sampleCrudOperations.Entity.User;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcRepository {
    @Autowired
    private final JdbcTemplate jdbcTemplate;
    public JdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
public List<User> findAll(){
        return jdbcTemplate.query("SELECT * FROM user",
                (rs, rowNum) ->
                new User(rs.getLong("id"),
                rs.getString("name"), rs.getString("email"),rs.getString("address")));


}
public User findById(Long id){
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id=?",new Object[]{id},(rs,rowNum)->new User(rs.getLong("id"),rs.getString("name"),rs.getString("address"),rs.getString("email")));
}
//    public User findById(Long id) {
//        String sql = "SELECT * FROM user WHERE id = ?";
//        return jdbcTemplate.queryForObject(
//                sql,
//                new Object[]{id},
//                (rs, rowNum) -> new User(
//                        rs.getLong("id"),
//                        rs.getString("name"),
//                        rs.getString("email"),
//                        rs.getString("address")
//                )
//        );
//    }
    public User save(  @NotNull User user){
        String sql ="INSERT INTO User(id,name,address,email)values(?,?,?,?)";
         jdbcTemplate.update(sql,user.getId(),user.getName(),user.getAddress(),user.getEmail());
         return user;

    }
    public User update(@NotNull User user) {
        String sql = "UPDATE User SET name=?, address=?, email=? WHERE id=?";
        jdbcTemplate.update(sql, user.getName(), user.getAddress(), user.getEmail(), user.getId());
        return user;
    }

    public void delete(Long id){
     String sql="DELETE FROM USER WHERE ID=?";
        jdbcTemplate.update(sql,id);
    }

    }

