package webhxh.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import webhxh.repository.UserInfoRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	UserInfoRepository repository;
	// authentication
	@Bean
	UserDetailsService userDetailsService() {
		return new UserInfoService(repository);
	}
	@Bean
	PasswordEncoder passwordEncoder () {
		return new BCryptPasswordEncoder ();
	}
	@Bean
	AuthenticationProvider authenticationProvider (){
	DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider ();
	authenticationProvider. setUserDetailsService(userDetailsService());
	authenticationProvider. setPasswordEncoder ( passwordEncoder ()) ;
	return authenticationProvider;
	//security 6.1+
	}
	
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                		.requestMatchers("/user/new") .permitAll()
                        .requestMatchers("/hello").permitAll()
                        .requestMatchers("/customer/**").authenticated()
                        //.anyRequest().authenticated() // Nếu muốn các yêu cầu khác phải xác thực
                )
                .formLogin(Customizer.withDefaults()) // Sử dụng form login mặc định của Spring Security
                .build();
    }

}

