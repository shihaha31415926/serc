package com.serc.security;

import cn.hutool.json.JSONUtil;
import com.serc.entity.R;
import com.serc.util.JwtUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();

        String message = exception.getMessage();
        if(exception instanceof BadCredentialsException){
            message = "用户名或密码错误";
        }



        outputStream.write(JSONUtil.toJsonStr(R.error(message)).getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();

    }
}
