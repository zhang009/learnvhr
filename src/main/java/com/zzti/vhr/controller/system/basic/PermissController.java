package com.zzti.vhr.controller.system.basic;

import com.zzti.vhr.model.Menu;
import com.zzti.vhr.model.RespBean;
import com.zzti.vhr.model.Role;
import com.zzti.vhr.service.MenuService;
import com.zzti.vhr.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName PermissController
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/9 19:06
 **/
@RestController
@RequestMapping("/system/basic/permiss")
public class PermissController {

    @Autowired
    RoleService roleService;
    @Autowired
    MenuService menuService;

    @GetMapping("/")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

    @GetMapping("/menus")
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }

    @GetMapping("/mids/{rid}")
    public List<Integer> getMidsByRid(@PathVariable Integer rid){
        return menuService.getMidsByRid(rid);
    }

    @PutMapping("/")
    public RespBean updateMenuRole(Integer rid,Integer[] mids){
        if(menuService.updateMenuRole(rid,mids)){
          return   RespBean.ok("修改成功！");
        }
        return   RespBean.error("修改失败！");
    }

    @PostMapping("/role")
    public RespBean addRole(@RequestBody  Role role){

        if(roleService.addRole(role)==1){
            return   RespBean.ok("添加成功！");
        }
        return   RespBean.error("添加失败！");
    }
    @DeleteMapping("/role/{rid}")
    public RespBean deleteRoleById(@PathVariable Integer rid){
        if(roleService.deleteRoleById(rid)==1){
            return   RespBean.ok("删除成功！");
        }
        return   RespBean.error("删除失败！");
    }

}
