package com.zzti.vhr.controller.config;

import com.zzti.vhr.model.Menu;
import com.zzti.vhr.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName SystemConfigController
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/3 9:34
 **/
@RestController
@RequestMapping("/system/config")
public class SystemConfigController {

    @Autowired
    MenuService menuService;
    @GetMapping("/menu")
    public List<Menu> getMenusByHrId(){//这里不要用前端传回来的数据，因为前端的数据是不可信的,比如邮箱地址，前端页面校验，后端也要校验
                                        //所以用户的id,这里用后端的id
        return menuService.getMenusByHrId();

    }

}
