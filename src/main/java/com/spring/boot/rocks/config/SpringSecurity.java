package com.spring.boot.rocks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Bean
	public SecurityFilterChain secFilterChain(HttpSecurity http) throws Exception {
		
//		HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
//	    requestCache.setMatchingRequestParameterName("somecustomparameter");

		http
//		.requestCache((cache) -> cache
//	            .requestCache(requestCache))
		.csrf(AbstractHttpConfigurer::disable)
        .cors(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests(auth -> auth
		.requestMatchers("/register/**").permitAll()
		.requestMatchers("/downloadhelpfile/**").permitAll()
		.requestMatchers("/adminuseredit/**").hasAuthority("ADMIN")
		.requestMatchers("/listusers/**").hasAnyAuthority("ADMIN", "EDITOR", "USER")
		.requestMatchers("/listuser/**").hasAnyAuthority("ADMIN", "EDITOR", "USER")
		.requestMatchers("/home/**").permitAll()
		.requestMatchers("/login").permitAll()
		.requestMatchers("/signup/**").permitAll()
		.requestMatchers("/webjars/**").permitAll()
		.requestMatchers("/images/**").permitAll()
		.requestMatchers("/css/**").permitAll()
		.requestMatchers("/fonts/**").permitAll()
		.requestMatchers("/js/**").permitAll()
		.requestMatchers("/swagger-ui/**").permitAll()
		.anyRequest().authenticated())
		.formLogin(fL -> fL.loginPage("/login").permitAll().defaultSuccessUrl("/"))
        .exceptionHandling(exception-> exception.accessDeniedPage("/403"))
        .logout(lOut -> {
            lOut.invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll();
          });

		return http.build();
	}
	

	@Bean
	static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
    SpringSecurityDialect springSecurityDialect(){
        return new SpringSecurityDialect();
    }
	
	@Bean
	WebSecurityCustomizer ignoringCustomizer() {
	  return (web) -> web.ignoring().requestMatchers("/h2-console/**");
	}
	
}