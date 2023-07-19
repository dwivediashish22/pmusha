package org.nic.pmusha.security;

import org.nic.pmusha.userdao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity 	// Enable security config. This annotation denotes config for spring security.
public class SecurityCredentialsConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtConfig jwtConfig;
	
	@Autowired
	BCryptPasswordEncoder bcrypt;
	
	@Autowired
	UserDao userDao;
   
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println(1);
		http.csrf().disable()
	            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        //.and()
	        //    .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
	        .and()
		    .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig,userDao))	
		    .authorizeRequests()
			.antMatchers("/actuator/*").permitAll().antMatchers("/auth/users/slider").permitAll()
			.antMatchers("/auth/users/header").permitAll().antMatchers("/auth/users/hasaccess/*").permitAll()
			.antMatchers("/auth/users/verifyotp").permitAll().antMatchers("/auth/users/usercredentials").permitAll()
			.antMatchers("/auth/users/user").permitAll().antMatchers("/auth/users/isusernameexist/*").permitAll()
			.antMatchers("/auth/users/userdetailsbytoken").permitAll().antMatchers("/auth/users/userdetailsbytoken/*").permitAll()
			.antMatchers("/auth/users/user/**").permitAll()
			.antMatchers("/msheservice/auth/users/**").permitAll()
			.antMatchers("/nicexam/exam/examcount").permitAll()

			.antMatchers("/user/**").permitAll().antMatchers("/user/swagger-ui.html/**").permitAll()
			.antMatchers(HttpMethod.GET, "/user/swagger-ui.html/**").permitAll()
			.antMatchers(HttpMethod.GET, "/user/webjars/**").permitAll()
			.antMatchers(HttpMethod.GET, "/user/swagger-resources/**").permitAll()
			.antMatchers(HttpMethod.GET, "/user/v2/api-docs/**").permitAll()
			
			.antMatchers("/msheservice/**").permitAll().antMatchers("/msheservice/swagger-ui.html/**").permitAll()
			.antMatchers(HttpMethod.GET, "/msheservice/swagger-ui.html/**").permitAll()
			.antMatchers(HttpMethod.GET, "/msheservice/webjars/**").permitAll()
			.antMatchers(HttpMethod.GET, "/msheservice/swagger-resources/**").permitAll()
			.antMatchers(HttpMethod.GET, "/msheservice/v2/api-docs/**").permitAll()
		
		.antMatchers(HttpMethod.GET, "/swagger-ui.html/**").permitAll()
	    .antMatchers(HttpMethod.GET, "/webjars/**").permitAll()
	    .antMatchers(HttpMethod.GET, "/swagger-resources/**").permitAll()
	    .antMatchers(HttpMethod.GET, "/v2/api-docs/**").permitAll()
	   
	   .antMatchers(HttpMethod.POST, jwtConfig.getUri()).permitAll()  
	   .antMatchers(HttpMethod.POST, "/auth/users/changelogs").permitAll()
	   .antMatchers(HttpMethod.POST, "/auth/**").permitAll()
	   .antMatchers(HttpMethod.POST, "/logout/**").permitAll();

	   //.anyRequest().authenticated().and().cors();
		    
		    
		    
		    
//		    .authorizeRequests().anyRequest().permitAll()
//		 .antMatchers("/auth/users/**").permitAll()
//		 .antMatchers("/msheservice/auth/users/**").permitAll()
//		 .antMatchers("/auth/login/**").permitAll()
//		 .antMatchers("/logout/**").permitAll()
//		 .and()
// 		 .logout().logoutSuccessHandler(logoutSuccessHandler).permitAll();
	
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println(2);
		auth.userDetailsService(userDetailsService).passwordEncoder(bcrypt);
	}
	
}