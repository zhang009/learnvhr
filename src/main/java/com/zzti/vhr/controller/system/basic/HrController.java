package com.zzti.vhr.controller.system.basic;

import com.zzti.vhr.model.Hr;
import com.zzti.vhr.model.RespBean;
import com.zzti.vhr.model.Role;
import com.zzti.vhr.service.HrService;
import com.zzti.vhr.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName HrController
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/10 18:41
 **/
@RestController
@RequestMapping("/system/hr")
public class HrController {
    @Autowired
    HrService hrService;

    @Autowired
    RoleService roleService;
    @GetMapping("/")
    public List<Hr> getAllHrs(String keywords){
        return hrService.getAllHrs(keywords);
    }

    @PutMapping("/")
    public RespBean updateHr(@RequestBody Hr hr){
        if(hrService.updateHr(hr)==1){
            return RespBean.ok("修改成功！");
        }
        return RespBean.ok("修改成功！");
    }
    //根据id查询所有的角色
    @GetMapping("/roles")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }
    //更新hr的角色
    @PutMapping("/role")
    public RespBean updateHrRole(Integer hrid,Integer[] rids){
        if(hrService.updateHrRole(hrid,rids)){
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败！");
    }
    @DeleteMapping("/{id}")
    public RespBean deleteHrById(@PathVariable Integer id){
        if(hrService.deleteHrById(id)==1){
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

}
