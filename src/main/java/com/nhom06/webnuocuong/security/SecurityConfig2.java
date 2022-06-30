//package com.nhom06.webnuocuong.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.access.AccessDeniedHandler;
//
//public class SecurityConfig2 {
//
//	 @Autowired
//	    private AccessDeniedHandler accessDeniedHandler;
//
//	 @Bean
//		public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//	        http.antMatcher("/admin*")
//	          .authorizeRequests()
//	          .anyRequest()
//	          .hasAuthority("ROLE_ADMIN")
//
//	          .and()
//	          .formLogin()
//	          .loginPage("/loginAdmin")
//	          .loginProcessingUrl("/admin_login")
//	          .failureUrl("/loginAdmin?error=loginError")
//	          .defaultSuccessUrl("/admin")
//
//	          .and()
//	          .logout()
//	          .logoutUrl("/admin_logout")
//	          .logoutSuccessUrl("/protectedLinks")
//	          .deleteCookies("JSESSIONID")
//
//	          .and()
//	          .exceptionHandling()
//	          .accessDeniedPage("/403")
//
//	          .and()
//	          .csrf().disable();
//	        
//	        return http.build();
//	    }
//	}
//
