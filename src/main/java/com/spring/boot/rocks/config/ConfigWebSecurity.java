package com.spring.boot.rocks.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.spring.boot.rocks")
public class ConfigWebSecurity extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	DataSource dataSource;

	@Autowired
	AppRoleConverter roleToUserProfileConverter;

	@Autowired
	@Qualifier("persistentTokenRepository")
	private PersistentTokenRepository persistentTokenRepository;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests().antMatchers("/webjars/**", "/resources/**", "/css/**", "/css/theme/**")
				.permitAll();
		httpSecurity.authorizeRequests().antMatchers("/css/**", "/js/**", "/img/**").permitAll();
		httpSecurity.authorizeRequests().antMatchers("/registration", "/login").permitAll()
				.antMatchers("/delete-user-**").access("hasAuthority('ADMIN')").antMatchers("/edit-user-**")
				.access("hasAuthority('ADMIN') or hasAuthority('EDITOR')").antMatchers("/view-user-**").permitAll()
				.antMatchers("/api/listappusers/**").access("hasAuthority('ADMIN')")// .hasRole("ADMIN")
				.antMatchers("/api/findappuser/**").access("hasAuthority('ADMIN')")// .hasRole("ADMIN")
				.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()

				.and().logout().permitAll().and().rememberMe().rememberMeParameter("remember-me")
				.tokenRepository(persistentTokenRepository).userDetailsService(userDetailsService).and().csrf()
				.disable().exceptionHandling().accessDeniedPage("/Access_Denied");
	}

	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(roleToUserProfileConverter);
	}

	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
		return authenticationManager();
	}

//	@Bean
//	public SimpleUrlHandlerMapping faviconHandlerMapping() {
//		SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
//		mapping.setOrder(Integer.MIN_VALUE);
//		mapping.setUrlMap(Collections.singletonMap("favicon.ico", faviconRequestHandler()));
//		return mapping;
//	}

//	@Bean
//	protected ResourceHttpRequestHandler faviconRequestHandler() {
//		ResourceHttpRequestHandler requestHandler = new ResourceHttpRequestHandler();
//		requestHandler.setLocations(Arrays.<Resource>asList(new ClassPathResource("/")));
//		return requestHandler;
//	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
		web.ignoring().antMatchers("/css/**");
		web.ignoring().antMatchers("/js/**");
		web.ignoring().antMatchers("/img/**");
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		return tokenRepository;
	}

}