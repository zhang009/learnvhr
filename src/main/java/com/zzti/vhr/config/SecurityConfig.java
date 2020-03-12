package com.zzti.vhr.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzti.vhr.model.Hr;
import com.zzti.vhr.model.RespBean;
import com.zzti.vhr.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName SecurityConfig
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/29 9:37
 **/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    HrService hrService;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrService);
    }
    @Autowired
    CustomFilterInvocationSecurityMetadataSource customFilterInvocationSecurityMetadataSource;
    @Autowired
    CustomUrlDecisionManager myDecisionManager;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login");//如果要访问login登录页面，不用经过spirngSecurity拦截
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .anyRequest().authenticated()//任何请求在认证之后才能访问
                //这里加入动态权限
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setAccessDecisionManager(myDecisionManager);
                        object.setSecurityMetadataSource(customFilterInvocationSecurityMetadataSource);
                        return object;
                    }
                })

                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/doLogin")
                .loginPage("/login")//本来默认是有登录页，但你这在里配置后，默认的就失效了
                                    //注意：但是我们后端不应该有页面跳转的，只应该返回json
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        //登录成功，前提工作-》在model里面封装自定义返回实体类RespBean
                        //resp.setContentType("application/json:charset=utf-8");
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out=resp.getWriter();
                        Hr hr=(Hr)authentication.getPrincipal();//获取登录成功的hr对象
                       // System.out.println("hr:"+hr.toString());
                        hr.setPassword(null);//避免密码泄露
                        RespBean ok = RespBean.ok("登录成功！", hr);
                        String s = new ObjectMapper().writeValueAsString(ok);
                        out.write(s);
                        out.flush();
                        out.close();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                        //登录失败
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out=resp.getWriter();
                        RespBean respBean =RespBean.error("登录失败！");
                        if(e instanceof LockedException){
                            respBean.setMsg("账户被锁定，请联系管理员");
                        }else if(e instanceof CredentialsExpiredException){
                            respBean.setMsg("账户密码过期");
                        }else if(e instanceof AccountExpiredException){
                            respBean.setMsg("账户过期");
                        }else if(e instanceof DisabledException){
                            respBean.setMsg("账户被禁用");
                        }else if(e instanceof BadCredentialsException){
                            respBean.setMsg("用户名或密码输入错误,请重新输入");
                        }
                        String s = new ObjectMapper().writeValueAsString(respBean);
                        out.write(s);
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()//跟登录相关的接口不需要认证即可访问
                .and()
                .logout()//注销登录，如果想配置，这里有.logouturl
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        out.write(new ObjectMapper().writeValueAsString(RespBean.ok("注销成功!")));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                //.csrf().disable();//postman测试需关闭，如果有疑问看第十章
                .csrf().disable().exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                        //在这里你可以选择请求失败了之后，是重定向还是直接返回结果
                        //没有登录认证，在这里处理结果，不要重定向
                        resp.setContentType("application/json;charset=utf-8");
                        resp.setStatus(401); //401未认证，403未授权

                        PrintWriter out=resp.getWriter();
                        RespBean respBean =RespBean.error("访问失败！");
                        if(e instanceof InsufficientAuthenticationException){
                            respBean.setMsg("请求失败，请联系管理员！");
                        }
                        String s = new ObjectMapper().writeValueAsString(respBean);
                        out.write(s);
                        out.flush();
                        out.close();
                    }
                });

    }
}
