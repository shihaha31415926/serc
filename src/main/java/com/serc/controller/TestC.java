package com.serc.controller;

import com.serc.entity.R;
import com.serc.entity.SysUser;
import com.serc.service.SysUserService;
import com.serc.util.JwtUtils;
import com.serc.util.StringUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/test")
@CrossOrigin
public class TestC {
    @Resource
    private SysUserService sysUserService;

    @RequestMapping("/user")
    public R test(@RequestHeader(required = false) String token){
        System.out.println(token);
        if(StringUtil.isNotEmpty(token)){
        HashMap<String,Object> hashMap = new HashMap<>();
        List<SysUser> list = sysUserService.list();
        hashMap.put("list",list);
        return R.ok(hashMap);}
        else {
            return R.error(401,"没有访问权限");
        }
    }


    @RequestMapping("/login")
    public R login(){
        String token = JwtUtils.genJwtToken("java1234");
        return R.ok().put("token",token);
    }


}
