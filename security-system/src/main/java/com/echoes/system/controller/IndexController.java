package com.echoes.system.controller;

import com.echoes.common.result.Result;
import com.echoes.common.utils.JwtHelper;
import com.echoes.common.utils.MD5;
import com.echoes.system.exception.EchoesException;
import com.echoes.system.entity.LoginVo;
import com.echoes.system.entity.SysUser;
import com.echoes.system.service.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lao
 * @version 1.0
 * @description: TODO
 * @date 2022/11/28 7:45 PM
 */

@Api(tags = "用户登录的接口")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;


    //login
    //{"code":20000,"data":{"token":"admin-token"}}
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo) {
        //根据username查询数据
        SysUser sysUser = sysUserService.getUserInfoByUserName(loginVo.getUsername());

        //如果查询为空
        if(sysUser == null) {
            throw new EchoesException(20001,"用户不存在");
        }

        //判断密码是否一致
        String password = loginVo.getPassword();
        String md5Password = MD5.encrypt(password);
        if(!sysUser.getPassword().equals(md5Password)) {
            throw new EchoesException(20001,"密码不正确");
        }

        //判断用户是否可用
        if(sysUser.getStatus().intValue()==0) {
            throw new EchoesException(20001,"用户已经被禁用");
        }

        //根据userid和username生成token字符串，通过map返回
        String token = JwtHelper.createToken(sysUser.getId().toString(), sysUser.getUsername());

        Map<String,Object> map = new HashMap<>();
        map.put("token",token);

        return Result.ok(map);
    }


    @GetMapping("info")
    public Result info(HttpServletRequest request) {
        //获取请求头token字符串
        String token = request.getHeader("token");

        //从token字符串获取用户名称（id）
        String username = JwtHelper.getUsername(token);

        //根据用户名称获取用户信息（基本信息 和 菜单权限 和 按钮权限数据）
        Map<String,Object> map = sysUserService.getUserInfo(username);

        return Result.ok(map);
    }


}
