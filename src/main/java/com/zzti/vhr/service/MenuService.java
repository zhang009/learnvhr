package com.zzti.vhr.service;

import com.zzti.vhr.mapper.MenuMapper;
import com.zzti.vhr.mapper.MenuRoleMapper;
import com.zzti.vhr.model.Hr;
import com.zzti.vhr.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName MenuService
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/3 9:38
 **/
@Service
public class MenuService {
    @Autowired
    MenuMapper menuMapper;
    @Autowired
    MenuRoleMapper menuRoleMapper;
    public List<Menu> getMenusByHrId() {//根据用户的id，获取能够访问的资源（菜单）
        Hr hr= ((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        return menuMapper.getMenusByHrId(hr.getId());
    }

    //@Cacheable这里需要配置Redis
    public List<Menu> getAllMenusWithRole(){
        return menuMapper.getAllMenusWithRole();
    }

    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }

    public List<Integer> getMidsByRid(Integer rid) {
        return menuMapper.getMidsByRid(rid);
    }

    @Transactional//事物注解
    public boolean updateMenuRole(Integer rid, Integer[] mids) {
        //这里要做两件事，第一件事要根据rid,删除所有的mid
        //                第二件事情要根据rid,添加mids

        menuRoleMapper.deleteByRid(rid);
        Integer result=menuRoleMapper.insertRecord(rid,mids);
        return result==mids.length;
    }
}
