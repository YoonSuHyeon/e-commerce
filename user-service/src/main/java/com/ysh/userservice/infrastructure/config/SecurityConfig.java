package com.ysh.userservice.infrastructure.config;

import com.ysh.userservice.infrastructure.security.JwtAuthenticationFilter;
import com.ysh.userservice.infrastructure.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * TODO 추후 filterChain 정리가 필요함
 */
@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtProvider jwtProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    // TODO 추후 에러 핸들링 추가
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                //CORS 설정 (TODO 추후 확인 필요)
                .cors(AbstractHttpConfigurer::disable)

                //Spring Security 세션 정책 : 세션을 생성 및 사용하지 않음 (TODO 추후 확인 필요)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((securitySessionManagementConfigurer) -> {
                    securitySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                // 회원가입과 로그인은 모두 승인
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/users", "/api/users/login").permitAll()
                        .anyRequest().authenticated()
                )
                // JWT 인증 필터 적용
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

}
