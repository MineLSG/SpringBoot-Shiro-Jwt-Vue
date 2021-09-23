package com.SpringBoot_Jwt_Shiro.Config;

import com.SpringBoot_Jwt_Shiro.Token.JwtToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author RenZetong
 */
public class JwtFilter extends BasicHttpAuthenticationFilter {
    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    /**
     * 判断用户是否想要登录
     * 检测header里面是否包含token请求头即可
     * App_id:用户名密文
     * Auth_id:密码密文
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        //检测token头
        String toKen = req.getHeader("token");
        return toKen != null;
    }

    /**
     * 如果用户要登录，则执行登录方法
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        try {
            executeLogin(request, response);
            return true;
        } catch (Exception e) {
            System.out.println("非法请求");
        }
        return false;
    }

    /**
     * 登录过滤器，每次登录的时候，都从Header中的App_id字段和Auth_id字段中中获取token值，然后执行登录的操作
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        //将token请求头塞入自定义Token构造器,实际传入token值有两个,使用&符号来拆开
        String[] appId = ((HttpServletRequest) request).getHeader("token").split("&");
        //获取浏览器唯一标识符,用来进行设备认证
        String browser = ((HttpServletRequest) request).getHeader("user-agent");
        //将拆开后的两个值塞入自定义Token构造器
        JwtToken jwtToken = new JwtToken(appId[0], appId[1]);
        //定位url
        System.out.println(((HttpServletRequest) request).getRequestURI());
        //提交给Realm进行登入,如果错误会抛出异常并捕获
        getSubject(request, response).login(jwtToken);
        return true;
    }
}
