package com.cyw.info_admin.config;

import com.cyw.info_admin.entity.User;
import com.cyw.info_admin.entity.security.SecurityUser;
import com.cyw.info_admin.enumerate.UserType;
import com.cyw.info_admin.exception.LoginException;
import com.cyw.info_admin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 配置策略
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // iframe可以访问(如果不添加此配置，jsp页面弹出iframe框时无法请求)
        http.headers().frameOptions().disable();
        http.csrf().disable()
                .authorizeRequests()
                // 拦截所有请求
                .antMatchers("/**").authenticated()
                .and()
                // 配置登录页面
                .formLogin().loginPage("/").permitAll()
                // 登录成功后的处理
                .successHandler(loginSuccessHandler())
                .failureUrl("/sys/error?error=true");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 放行静态资源
        web.ignoring().
                antMatchers("/css/**","/font/**", "/image/**", "/js/**", "/layui/**");
    }

    /**
     * 全局配置
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
        auth.eraseCredentials(false);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() { //密码加密
        return new BCryptPasswordEncoder(4);
    }


    /**
     * 登录成功后的处理器
     * @return
     */
    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler loginSuccessHandler() {
        return new SavedRequestAwareAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                User userDetails = (User) authentication.getPrincipal();
                log.info("username: {} 登录成功！", userDetails.getName());
                // 用户登录成功后跳转的链接
                response.sendRedirect("/tup/pendingList");
            }
        };
    }


    /**
     * 用户信息对比
     * @return
     */
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Resource
            private UserService userService;

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                User user = userService.findByName(username);
                // 比对前对数据库中的获取到的密码进行加密(注意：这里的加密方式必须和对页面传递密码的加密方式一致)
                user.setPassword(new BCryptPasswordEncoder(4).encode(user.getPassword()));
                if (user == null)
                    throw new UsernameNotFoundException("Username：" + username + "没有查询到用户信息！");
                if (!UserType.AMDIN.getType().equals(user.getUserType()))
                    throw new LoginException("Username：" + username + "不是管理员，请联系授权工程师！");
                return new SecurityUser(user);
            }
        };
    }


}
