package com.schoolofnet.Helpdesk.config;

import com.schoolofnet.Helpdesk.models.User;
import com.schoolofnet.Helpdesk.service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
/*
    @Autowired
    private DataSource dataSource;
    */
/*
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
*/

    @Autowired
    private UserService userService;


   /* private static final String[] AUTH_LIST = {
            "/",
            "/users/new",
            "/users",
            "/user",
            "users/create",
            "user/create",
            "users/index",
            "user/index",
            "user/edit",
            "redirect:/users",
            "user/edit"
    };*/


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers().permitAll()//.antMatchers(AUTH_LIST).permitAll()
                .anyRequest().authenticated()
                .and().formLogin().permitAll()
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("rlouzano").password("{noop}123").roles("ADMIN");//"{noop}123456"
    }

    @Override
    // Este metodo sé é necessario quando não inserimos os docs do css bootstrap (Quando tem as url para download não precisa)
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/bootstrap/**");
//        web.ignoring().antMatchers("/bootstrap/**", "/style/**");
    }
}
