package com.SpringBoot_Jwt_Shiro.Controller;

import com.SpringBoot_Jwt_Shiro.Entity.User;
import com.SpringBoot_Jwt_Shiro.Service.UserService;
import com.SpringBoot_Jwt_Shiro.Token.JwtToken;
import com.SpringBoot_Jwt_Shiro.Utils.JwtUtils;
import com.SpringBoot_Jwt_Shiro.Utils.ResultMap;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * @author RenZetong
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @CrossOrigin(value = "*")
    @RequestMapping("/login")
    @ResponseBody
    public ResultMap login(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            if ("".equals(username) || "".equals(password)) {
                return new ResultMap().code(500).fail();
            }
            Subject subject = SecurityUtils.getSubject();
            //用户登录(将用户名和密码传入自定义Token构造器)
            JwtToken token = new JwtToken(JwtUtils.signUser(username), JwtUtils.signPass(password));
            subject.login(token);
            //生成密文 {App_id:'密文',Auth_id:'密文'},以后每次请求都加上请求头App_id和Auth_id
            //App_id->用户名,Auth_id->密码
            return new ResultMap().code(200).success().appid(JwtUtils.signUser(username)).authid(JwtUtils.signPass(password));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMap().code(500).fail();
        }
    }

    @CrossOrigin(value = "*")
    @RequestMapping("/msg")
    public ResultMap hello() {
        Subject subject = SecurityUtils.getSubject();
        User user = userService.getByPermission(JwtUtils.getUsername((String) subject.getPrincipal()));
        try {
            //验证用户是否具有admin角色
            subject.checkRole("admin");
        } catch (AuthorizationException e) {
            //抛出无权限
            return new ResultMap().code(501).fail().message("NotPermission");
        }
        return new ResultMap().code(200).success().message(JSON.toJSON(user));
    }

    /**
     * 用户退出登录
     */
    @CrossOrigin(value = "*")
    @RequestMapping("/logout")
    public ResultMap logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new ResultMap().code(200).success().message("logout");
    }

    @CrossOrigin(value = "*")
    @RequestMapping("/roles")
    public List<Map<String, Object>> queryRole(@RequestParam("role") String role) {
        return userService.getList(role);
    }

    @CrossOrigin(value = "*")
    @RequestMapping("/fail")

    public ResultMap error() {
        return new ResultMap().code(500).fail().message("404");
    }
}
