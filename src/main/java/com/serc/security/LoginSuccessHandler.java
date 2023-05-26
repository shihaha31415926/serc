package com.serc.security;

import cn.hutool.json.JSONUtil;
import com.serc.entity.R;
import com.serc.util.JwtUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();

        String username = authentication.getName();
        String token = JwtUtils.genJwtToken(username);
        R put = R.ok("登录成功").put("authorization", token);


        outputStream.write(JSONUtil.toJsonStr(put).getBytes());
        outputStream.flush();
        outputStream.close();

    }
}
