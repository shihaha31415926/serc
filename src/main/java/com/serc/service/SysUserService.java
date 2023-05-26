package com.serc.service;

import com.serc.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 史哈哈
* @description 针对表【sys_user】的数据库操作Service
* @createDate 2023-05-17 18:18:44
*/
public interface SysUserService extends IService<SysUser> {

    SysUser getByUsername(String username);
}
