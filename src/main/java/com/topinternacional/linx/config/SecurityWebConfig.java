package com.topinternacional.linx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/static/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin().loginPage("/login").permitAll()
			.and()
			.logout().permitAll()
			.and().httpBasic();
	}
	
   	@Override
   	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
   		auth.inMemoryAuthentication()
   			.passwordEncoder(passwordEncoder())
   			.withUser("inv")
   			.password(passwordEncoder().encode("inv"))
   			.roles("USER")
   			.and()
   			.withUser("admin")
   			.password(passwordEncoder().encode("TopSuporte"))
   			.roles("USER");
   }

   @Bean
   public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
   } 
}