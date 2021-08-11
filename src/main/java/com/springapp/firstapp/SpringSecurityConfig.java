package com.springapp.firstapp;

import com.springapp.firstapp.Filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity//declare that is a web security configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;
    @Autowired
    JwtRequestFilter jwtRequestFilter;


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws  Exception{
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder(10);
}


    @Override
    protected void configure(HttpSecurity http) throws Exception {
                 http.cors().and().csrf().disable()
                .authorizeRequests()
                         .antMatchers("/api/auth/**").permitAll()
                         .antMatchers("/api/basket/**").permitAll()
                         .antMatchers("/api/basketArticle/**").permitAll()


                         .anyRequest().authenticated().and().sessionManagement()
                         .sessionCreationPolicy(SessionCreationPolicy.STATELESS);//ne sauvegarde pas session
                 http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//        http.csrf().disable()
//                .authorizeRequests()
//                        .antMatchers("/**").permitAll();
    }
}
