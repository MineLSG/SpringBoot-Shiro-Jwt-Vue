package com.SpringBoot_Jwt_Shiro.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author RenZetong
 */
public class JwtUtils {
    /**
     * 密钥值
     */
    private static final String JWK_SOLT = "RenZetong";

    /**
     * 超时时间为 8h
     */
    public static final long TIME_LIMIT = 8 * 6000 * 1000;


    /**
     * 添加签名
     *
     * @param username 用户名
     * @return 返回加密后的token信息
     */
    public static String signUser(String username) {
        JwtBuilder builder = Jwts.builder()
                //签发人
                .setIssuer("https://www.cnblogs.com/MineLSG/")
                //接收人
                .claim("username", username)
                //签发时间
                .setIssuedAt(new Date())
                //过期时间
                .setExpiration(new Date(System.currentTimeMillis() + TIME_LIMIT))
                //签名(密钥)
                .signWith(SignatureAlgorithm.HS512, "Admin123!@#");
        return builder.compact();
    }

    /**
     * 添加签名密钥
     */
    public static String signPass(String password) {
        JwtBuilder builder = Jwts.builder()
                //签发人
                .setIssuer("https://www.cnblogs.com/MineLSG/")
                //接收人
                .claim("password", password)
                //签发时间
                .setIssuedAt(new Date())
                //过期时间
                .setExpiration(new Date(System.currentTimeMillis() + TIME_LIMIT))
                //签名(密钥)
                .signWith(SignatureAlgorithm.HS512, "Admin123!@#");
        return builder.compact();
    }

    /**
     * 验证Token是否有效，并获取内容
     *
     * @param token 需要验证的内容
     * @return map<>
     */
    public static Map<String, Object> valData(String token) {
        try {
            //解析token信息
            Claims claims = Jwts.parser()
                    //签名(密钥)
                    .setSigningKey("Admin123!@#")
                    // 解析的token
                    .parseClaimsJws(token)
                    .getBody();
            String username = (String) claims.get("username");
            String password = (String) claims.get("password");
            Map<String, Object> objs = new HashMap<String, Object>(1);
            objs.put("username", username);
            objs.put("password", password);
            objs.put("expiration", claims.getExpiration());
            return objs;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 验证token是否有效，并获取内容
     *
     * @param token 需要验证的token
     * @return username
     */
    public static String getUsername(String token) {
        try {
            //解析token信息
            Claims claims = Jwts.parser()
                    //签名(密钥)
                    .setSigningKey("Admin123!@#")
                    //解密的字符串
                    .parseClaimsJws(token)
                    .getBody();
            //获取订阅者
            return (String) claims.get("username");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 验证token是否有效，并获取内容
     *
     * @param token 需要验证的token
     * @return password
     */
    public static String getPassword(String token) {
        try {
            //解析token信息
            Claims claims = Jwts.parser()
                    //签名(密钥)
                    .setSigningKey("Admin123!@#")
                    //解密的字符串
                    .parseClaimsJws(token)
                    .getBody();
            //获取订阅者
            return (String) claims.get("password");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
