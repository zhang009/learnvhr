package com.zzti.vhr.config;

import com.zzti.vhr.model.Menu;
import com.zzti.vhr.model.Role;
import com.zzti.vhr.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @ClassName MyFilter
 * @Description 根据用户的请求地址，分析出请求的角色
 * @Author Administrator
 * @Date 2020/3/4 19:55
 **/
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    MenuService menuService;

    AntPathMatcher antPathMatcher=new AntPathMatcher();//比较的工具

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation)o).getRequestUrl();
        List<Menu> menus=menuService.getAllMenusWithRole();//返回的方法不是经常变化，所以可以在此方法上加一个缓存
        for (Menu menu: menus){
            if(antPathMatcher.match(menu.getUrl(),requestUrl)){//第一个参数是规则
                List<Role> roles=menu.getRoles();
                String [] str=new String[roles.size()];
                for (int i=0;i<roles.size();i++){
                    str[i]=roles.get(i).getName();
                }
                return SecurityConfig.createList(str);//org.springframework.security.access.SecurityConfig;
                //当然，有一个都没匹配上的情况
            }
        }
        //没配上的，统一登录之后都能访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
