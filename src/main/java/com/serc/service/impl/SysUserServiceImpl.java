package com.serc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.serc.entity.SysUser;
import com.serc.service.SysUserService;
import com.serc.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

/**
* @author 史哈哈
* @description 针对表【sys_user】的数据库操作Service实现
* @createDate 2023-05-17 18:18:44
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{
    @Override
    public SysUser getByUsername(String username) {
        return getOne(new QueryWrapper<SysUser>().eq("username",username));
    }
}




