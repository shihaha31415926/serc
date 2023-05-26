package com.serc.security;

import com.serc.entity.SysUser;
import com.serc.exception.UserCountLockException;
import com.serc.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    SysUserService sysUserService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getByUsername(username);
        if (sysUser==null){
            throw new UsernameNotFoundException("用户名或密码错误");
        }else if ("1".equals(sysUser.getStatus())){
            throw new UserCountLockException("用户锁定");
        }
        return new User(sysUser.getUsername(),sysUser.getPassword(),getUserAuthority());
    }

    public List<GrantedAuthority> getUserAuthority() {
        return new ArrayList<>();
    }
}
