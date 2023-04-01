package ru.netology.security1.coniguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true,jsr250Enabled = true)
public class SecurityConiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder encoder() { return PasswordEncoderFactories.createDelegatingPasswordEncoder();}

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Ivan").password("password").authorities("READ")
                .and()
                .withUser("Anton").password("password").authorities("WRITE")
                .and()
                .withUser("Vova").password("password").authorities("DELETE");
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin()
//                .and()
//                .authorizeRequests().antMatchers("/products").permitAll()
//                .and()
//                .authorizeRequests().antMatchers("/products/fetch-product").hasAuthority("read")
//                .and()
//                .authorizeRequests().anyRequest().authenticated();
//    }
}
