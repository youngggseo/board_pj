package study.board_pj;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/users/join", "/users/login").permitAll() // 로그인, 회원가입 페이지는 인증 없이 접근 가능
                                .anyRequest().authenticated() // 그 외의 모든 요청은 인증 필요
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/users/login") // 사용자 정의 로그인 페이지 경로 설정
                                .loginProcessingUrl("/users/login") // 로그인 폼의 action URL
                                .usernameParameter("loginId") // 사용자 이름 파라미터 이름 설정
                                .passwordParameter("password") // 비밀번호 파라미터 이름 설정
                                .defaultSuccessUrl("/", true) // 로그인 성공 시 이동할 기본 URL
                                .permitAll() // 로그인 페이지는 누구나 접근 가능
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/users/logout") // 로그아웃 요청 URL
                                .logoutSuccessUrl("/users/login?logout") // 로그아웃 성공 시 이동할 URL
                                .permitAll() // 로그아웃도 누구나 접근 가능
                );

        return http.build();
    }
}

