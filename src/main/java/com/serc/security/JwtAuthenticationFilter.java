package com.serc.security;

import com.serc.common.constant.JwtConstant;
import com.serc.entity.CheckResult;
import com.serc.service.SysUserService;
import com.serc.util.JwtUtils;
import com.serc.util.StringUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.jar.JarException;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("token");
        System.out.println("请求url："+request.getRequestURI());
        if (StringUtil.isEmpty(token) || new ArrayList<String>(Arrays.asList(URL_WHITELIST)).contains(request.getRequestURI())){
            chain.doFilter(request,response);
        }
        CheckResult checkResult = JwtUtils.validateJWT(token);
        if (!checkResult.isSuccess()){
            switch (checkResult.getErrCode()){
                case JwtConstant
                        .JWT_ERRCODE_NULL:throw new JwtException("Token不存在");
                case JwtConstant
                        .JWT_ERRCODE_FAIL:throw new JwtException("Token验证失败");
                case JwtConstant
                        .JWT_ERRCODE_EXPIRE:throw new JwtException("Token过期");
            }
        }
        Claims claims = JwtUtils.parseJWT(token);
        String username = claims.getSubject();
        sysUserService.getByUsername(username);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,null, myUserDetailService.getUserAuthority());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        chain.doFilter(request,response);
    }

    private static final String[] URL_WHITELIST = {
            "/login","logout","/captcha","password","/image/**","/test/**"
    };

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager){
        super(authenticationManager);
    }
}
