package com.SpringBoot_Jwt_Shiro.Token;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author RenZetong
 */
public class JwtToken implements AuthenticationToken {


    /**
     * App_id密文
     */
    private String username;

    /**
     * Auth_id密文
     */
    private String password;

    public String getToken() {
        return username;
    }

    public JwtToken(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return password;
    }
}
