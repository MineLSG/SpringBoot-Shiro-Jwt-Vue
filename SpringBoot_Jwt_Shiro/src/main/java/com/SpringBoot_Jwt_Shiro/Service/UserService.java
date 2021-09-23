package com.SpringBoot_Jwt_Shiro.Service;

import com.SpringBoot_Jwt_Shiro.Entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author RenZetong
 */
public interface UserService {
    /**
     * 根据用户名和密码进行用查询
     */
    User getByName(String username, String password);

    /**
     * 根据用户名来进行权限的获取
     */
    User getByPermission(String username);

    /**
     * 查找拥有相同权限的用户
     */
    List<Map<String, Object>> getList(String role);
}
