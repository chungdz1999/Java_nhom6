package com.nhom06.webnuocuong.security;

import com.nhom06.webnuocuong.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@Order(2)
public class SecurityConfig {

	@Autowired
	private UserService userService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests()
		
		.antMatchers("/sapxepspg/page/{pageNumber}","/sapxepspt/page/{pageNumber}","/sapxepspg","/sapxepspt","/loginAdmin","/error","/danhmucsp/{id}","/timsp/{s}/page/{pageNumber}","/danhmucsp/{id}/page/{int}","/trangchu/page/{id}","/reset_password","/forgot_password","/timsanpham/search","/dang-ky-khach-hang", "/dang-nhap-khach-hang", "/", "/ChiTietSanPham/{id}")
		.permitAll()
		
		.antMatchers("/KvghDelete/{id}","/updateKvgh","/saveKvgh","/Kvgh-update/{id}","/sua-san-pham/{id}", "/ThemSanPham", "/dang-ky-admin","/Kvgh-update/{id}")
		.hasAnyAuthority("ROLE_ADMIN")
		
		.antMatchers("/KhuVucGiaoHang","/cap-nhat-don-hang","/cap-nhat-shipper","/danhmucDHAdmin/{id}","DonHangAdmin","/DanhSachSP_admin","/admin")
		.hasAnyAuthority("ROLE_ADMIN","ROLE_NHANVIEN")
		
		.antMatchers("/GioHang","/ThongTinKhachHang","/DonHangKhachHang")
		.hasAuthority("ROLE_KHACHHANG")
		
		
		
			//	.anyRequest().hasAuthority("ROLE_KHACHHANG")
				.and().exceptionHandling().accessDeniedPage("/error403")
				
				.and()
				.formLogin()
				.loginPage("/dang-nhap-khach-hang").permitAll()

				.and().logout().invalidateHttpSession(true)

				.clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/dangxuat"))
				.logoutSuccessUrl("/dang-nhap-khach-hang").deleteCookies("JSESSIONID").permitAll().and()
	            .rememberMe().key("uniqueAndSecret").tokenValiditySeconds(86400);

		return http.build();
	}
	
	
	
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/static/**", "/assets_admin/**", "/**/assets/**/**/**/**", "/css/**",
				"/pictures/**/**");
	}
}
