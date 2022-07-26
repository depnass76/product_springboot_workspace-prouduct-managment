package com.redasp.pma.security;

//import javax.activation.DataSource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		  auth.jdbcAuthentication().dataSource(dataSource)
		 .usersByUsernameQuery("select username,password, enabled from user_accounts where username=?")
         .authoritiesByUsernameQuery("select username, role from user_accounts where username=?")
		 .passwordEncoder( bCryptEncoder);  // we use passwordEncoder encrypt using bCryptEncoder

		
		
		
		
		//		.withDefaultSchema()
		//		.withUser("myuser").password("passuser").roles("USER")
		//		.and().withUser("usermanager").password("passmanager").roles("ADMIN");

	}

//	@Bean
//	public PasswordEncoder getPasswordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
//	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests()
//		  .antMatchers("/projects/new").hasRole("ADMIN") // priorities comes here first
//		  .antMatchers("/projects/update").hasRole("ADMIN")
//		  .antMatchers("/projects/save").hasRole("ADMIN")
//		  .antMatchers("/projects/delete").hasRole("ADMIN")
//		  .antMatchers("/employees/new").hasRole("ADMIN")// then priorities comes here second
//		  .antMatchers("/employees/update").hasRole("ADMIN")
//		  .antMatchers("/employees/save").hasRole("ADMIN")
//		  .antMatchers("/employees/delete").hasRole("ADMIN")
//		  .antMatchers("/employees/new").hasAuthority("ADMIN")// then priorities comes here second
//		  .antMatchers("/employees/updateEmployee").hasAuthority("ADMIN")
//		  .antMatchers("/employees/save").hasAuthority("ADMIN")
		  //.antMatchers("/h2_console/**").permitAll()
		  .antMatchers("/").authenticated().and().formLogin();
		// .antMatchers("/","/**").permitAll();    // then priorities comes here third
		//  .antMatchers("/").permitAll();
		 // .and()
		//  .formLogin(); // default login page
		
		  //.formLogin().loginPage("/login-page");    //you can customize login page in controller
		
		//http.csrf().disable(); //cross side request forgery bad idea in postgreSQL we used only in h2 database never do in production
		//http.headers().frameOptions().disable(); // this only need to console to work
		   
	}
	
	
	
	/*this case is using H2 in memory database
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("myuser1").password("pass1").roles("USER")
		.and().withUser("myuser2").password("pass2").roles("USER")
		.and().withUser("userManager").password("pass3").roles("ADMIN");

	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	protected void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests()
		  .antMatchers("/projects/new").hasRole("ADMIN")
		  .antMatchers("/employees/new").hasRole("ADMIN")
		  .antMatchers("/").authenticated().and().formLogin();
	}*/

}
