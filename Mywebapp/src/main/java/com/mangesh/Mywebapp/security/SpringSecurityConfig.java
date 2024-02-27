package com.mangesh.Mywebapp.security;
import static org.springframework.security.config.Customizer.withDefaults;
import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {
	@Bean
	public InMemoryUserDetailsManager AllUserDetails() {
		
		UserDetails Details1 = createNewUser("Mangesh", "Meera@75");
		UserDetails Details2 = createNewUser("Tejas", "Dhokla");
		return new InMemoryUserDetailsManager(Details1,Details2);
	}

	private UserDetails createNewUser(String username, String password) {
		Function<String, String> encoder = input -> encoder().encode(input);
		UserDetails Details = User.builder().passwordEncoder(encoder)
				.username(username).password(password).roles("USER","ADMIN").build();
		return Details;
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterchain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
		http.formLogin(withDefaults());
		http.csrf().disable();
		http.headers().frameOptions().disable();
		return http.build();
	}
	
	

}
