package com.zzti.vhr.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @ClassName MyDecisionManager
 * @Description 判断当前用户是否具备这些角色
 * @Author Administrator
 * @Date 2020/3/4 22:10
 **/
@Component
public class CustomUrlDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        for(ConfigAttribute configAttribute :configAttributes){
            String needRole=configAttribute.getAttribute();//需要的角色
            if("ROLE_LOGIN".equals(needRole)){
                //只需要判断有没有登录
                if(authentication instanceof AnonymousAuthenticationToken){
                    //如果为匿名用户，则没有登录
                    throw new AccessDeniedException("尚未登录，请登录！");
                }else {
                    return;//请求合法
                }
            }
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();//获取当前登录用户的角色
            //AB都有才能访问，只要有一个就能访问？，集合的判断，是否包含这个集合的任意一项
            for (GrantedAuthority authority : authorities) {
                if(authority.getAuthority().equals(needRole)){
                    return;
                }
            }

        }
        throw new AccessDeniedException("权限不足，请联系管理员！");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
