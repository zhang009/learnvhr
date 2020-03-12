package com.zzti.vhr.controller;

import com.zzti.vhr.model.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/29 14:16
 **/
@RestController
public class LoginController {
    @GetMapping("/login")//@CrossOrigin("*")
    public RespBean login(){
        return RespBean.error("尚未登录请登录！");
    }
}
