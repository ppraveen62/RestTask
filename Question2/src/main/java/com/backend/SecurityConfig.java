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
		.usersByUsernameQuery("select driver_Id as username, password, enabled from driver_Details where driver_Id=?")
		.authoritiesByUsernameQuery("select driver_Id, role from driver_Details where driver_Id=?");
		
		}
	
	@Bean
	public PasswordEncoder getpassword() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/api/createAccount").permitAll()
		.antMatchers("/api/shippingRequest/**").permitAll()
		.antMatchers("/api/verification/**").permitAll()
		.antMatchers("/api/updateStatus/**").fullyAuthenticated().and().httpBasic();
		http.authorizeRequests()
		.antMatchers("/api/uploadDocument/**").fullyAuthenticated().and().httpBasic();
		http.csrf().disable();
		
		
		
        
	}
	

	

	}
