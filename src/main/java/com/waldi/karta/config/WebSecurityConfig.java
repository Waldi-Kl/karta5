package com.waldi.karta.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.waldi.karta.authentication.KardDBAuthenticationService;
 
@Configuration
// @EnableWebSecurity = @EnableWebMVCSecurity + Extra features
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
    
    @Autowired
    KardDBAuthenticationService kardDBAauthenticationService;
 
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
 
        // Users in memory.
        auth.inMemoryAuthentication().withUser("user1").password("12345").roles("USER");
        auth.inMemoryAuthentication().withUser("admin1").password("12345").roles("USER, ADMIN");
        auth.inMemoryAuthentication().withUser("admin2").password("123").roles("ADMIN");
 
        // For User in database.
      //  auth.userDetailsService(kardDBAauthenticationService);	Tak by�o
        auth.userDetailsService(kardDBAauthenticationService).passwordEncoder(passwordEncoder());	// A tak ma by�
 
    }
    
    @Bean
   	public PasswordEncoder passwordEncoder(){
   		PasswordEncoder encoder = new BCryptPasswordEncoder();
   		return encoder;
   	}
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
 
    //.out.println("WebSecurityConfig i http: " + http.authorizeRequests().and().formLogin().usernameParameter("username"));
        http.csrf().disable();
 
        // The pages does not require login
        http.authorizeRequests().antMatchers("/", "/homepage", "/login", "/logout", "/newpass/*").permitAll();
 
        // /cardInfo page requires login as USER or ADMIN.
        // If no login, it will redirect to /login page.
        http.authorizeRequests().antMatchers("/cardInfo/*").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
 
        // For ADMIN only.
        http.authorizeRequests().antMatchers("/admin","/userInfo/*").access("hasRole('ROLE_ADMIN')");
 
        // When the user has logged in as XX.
        // But access a page that requires role YY,
        // AccessDeniedException will throw.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
 
        // Config for Login Form
        http.authorizeRequests().and().formLogin()//
                // Submit URL of login page.
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")//
                .defaultSuccessUrl("/userInfo")//
                .failureUrl("/login?error=true")//
                .usernameParameter("userlogin")//	Parametr z formularza logowania
                .passwordParameter("password")//	Parametr z formularza logowania
                // Config for Logout Page
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful"); 
    }
}