package com.SpringBoot_Jwt_Shiro.Realm;

import com.SpringBoot_Jwt_Shiro.Entity.User;
import com.SpringBoot_Jwt_Shiro.Service.UserService;
import com.SpringBoot_Jwt_Shiro.Token.JwtToken;
import com.SpringBoot_Jwt_Shiro.Utils.JwtUtils;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author RenZetong
 */
public class JwtRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 授权管理
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取原始appId密文
        String username = (String) principalCollection.getPrimaryPrincipal();
        //解密后得出用户主身份
        String principal = JwtUtils.getUsername(username);
        //使用主身份进行权限查询
        User user = userService.getByPermission(principal);
        System.out.println("权限:" + JSON.toJSON(user));
        info.addRole(user.getRole());
        return info;
    }

    /**
     * 认证管理
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        JwtToken jwtToken = (JwtToken) authenticationToken;
        //获取原始appId密文
        String userToken = (String) jwtToken.getPrincipal();
        //获取原始authId密文
        String passToken = (String) jwtToken.getCredentials();
        //将解密后的appId,authId传入service进行用户数据检索,查看是否有该用户
        User user = userService.getByName(JwtUtils.getUsername(userToken), JwtUtils.getPassword(passToken));

        if (user == null) {
            System.out.println("用户名或密码错误");
            throw new AuthenticationException("用户名或密码错误!");
        }
        return new SimpleAuthenticationInfo(userToken, passToken, getName());
    }
}