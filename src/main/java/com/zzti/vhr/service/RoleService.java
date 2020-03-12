package com.zzti.vhr.service;

import com.zzti.vhr.mapper.RoleMapper;
import com.zzti.vhr.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName RoleService
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/9 19:08
 **/
@Service
public class RoleService {

    @Autowired
    RoleMapper roleMapper;

    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }


    public Integer addRole(Role role) {
        if(role.getName().startsWith("ROLE_")){
            role.setName("ROLE_"+role.getName());
        }
        return  roleMapper.insert(role);
    }

    public Integer deleteRoleById(Integer rid) {
        return roleMapper.deleteByPrimaryKey(rid);
    }
}
