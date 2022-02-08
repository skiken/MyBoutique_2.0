package com.training.MyBoutique_20.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.training.MyBoutique_20.security.filters.JwtRequestFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userService;

	@Autowired
	JwtRequestFilter jwtRequestFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
				authorizeRequests().
				antMatchers("/h2-console/**").permitAll().
				antMatchers("/swagger-ui.html/**").permitAll().
				antMatchers("/time").hasRole("ADMIN").
				antMatchers("/user").hasRole("ADMIN").
				antMatchers("/api/**").permitAll().
				antMatchers("/authenticate").permitAll().
				//anyRequest().authenticated().
				and().formLogin()
				//.loginPage("/authenticate")  // to specify personal login form
				// redirection
				.successHandler(myAuthenticationSuccessHandler()).loginProcessingUrl("/login")
				.failureUrl("/login.html?error=true")
				
				// session
				.and().sessionManagement().sessionFixation().migrateSession()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				//remember_me
				 .and()
		         .rememberMe().key("uniqueAndSecret").tokenValiditySeconds(86400);

		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		http.csrf().disable();
		http.headers().frameOptions().disable();

	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
		return new UrlAuthenticationSuccessHandler();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {

		return super.authenticationManagerBean();
	}

}
