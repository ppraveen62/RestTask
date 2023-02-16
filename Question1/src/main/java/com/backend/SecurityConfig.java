package com.backend;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;



@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	 @Autowired
	    DataSource dataSource;
	 
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("select email_address as username, password, enabled from Users where email_address=?")
		.authoritiesByUsernameQuery("select email_address, role from Users where email_address=?");
		
		}
	
	@Bean
	public PasswordEncoder getpassword() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/api/user/create").permitAll()
		.antMatchers("/api/user/update/**").fullyAuthenticated().and().httpBasic();
		http.authorizeRequests()
		.antMatchers("/api/user/get/**").fullyAuthenticated().and().httpBasic();
		http.csrf().disable();
		
		
		
        
	}
	

	

	}
