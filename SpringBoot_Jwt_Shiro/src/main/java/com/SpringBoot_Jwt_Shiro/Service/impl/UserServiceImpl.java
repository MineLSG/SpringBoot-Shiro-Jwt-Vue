package com.SpringBoot_Jwt_Shiro.Service.impl;

import com.SpringBoot_Jwt_Shiro.Entity.User;
import com.SpringBoot_Jwt_Shiro.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author RenZetong
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User getByName(String username, String password) {
        String sql = "select * from user where username = ? and password = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username, password);
    }

    @Override
    public User getByPermission(String username) {
        String sql = "select * from user where username = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
    }

    @Override
    public List<Map<String, Object>> getList(String role) {
        String sql = "select * from user where role = ?";
        return jdbcTemplate.queryForList(sql, role);
    }
}
