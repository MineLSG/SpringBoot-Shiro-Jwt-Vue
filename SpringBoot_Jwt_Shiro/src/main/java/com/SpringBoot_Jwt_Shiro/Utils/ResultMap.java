package com.SpringBoot_Jwt_Shiro.Utils;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author RenZetong
 */
@Component
public class ResultMap extends HashMap<String, Object> {
    public ResultMap() {
    }

    public ResultMap success() {
        this.put("result", "success");
        return this;
    }

    public ResultMap fail() {
        this.put("result", "fail");
        return this;
    }

    public ResultMap code(int code) {
        this.put("code", code);
        return this;
    }

    public ResultMap appid(Object appid) {
        this.put("app_id", appid);
        return this;
    }

    public ResultMap authid(Object authid) {
        this.put("auth_id", authid);
        return this;
    }

    public ResultMap message(Object message) {
        this.put("message", message);
        return this;
    }

    public ResultMap page(Object page) {
        this.put("page", page);
        return this;
    }
}

