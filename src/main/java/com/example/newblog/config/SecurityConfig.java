package com.example.newblog.config;

import com.example.newblog.config.auth.PrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration // 빈 등록:스프링 컨테이너에서 객체를 관리함 (IoC관리)
@EnableWebSecurity // 스프링 시큐리티 필터에 추가된다 = 여기서 설정된 값이 적용된다는 의미
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근 시 권한 및 인증을 미리 체크
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean // 빈등록하면 IoC에서 관리
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    private PrincipalDetailService principalDetailService;

    // 시큐리티가 로그인을 수행하면서 password를 가로챌 때
    // 해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
    // 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있기 때문에
    // 여기서 principalDetailService에게 bCryptPasswordEncoder()로 암호화 했다고 알려줘야 한다.
    // 그러면 principalDetailService에서는 패스워드 비교 자동처리와 username 존재여부 체크하게 된다.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // bCryptPasswordEncoder로 해쉬되었음을 principalDetailService에게 알려준다.
        auth.userDetailsService(principalDetailService).passwordEncoder((bCryptPasswordEncoder()));
    }

    @Override 
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()// csrf토큰 비활성화 (테스트 시에는 걸어주는 것이 좋다)
            .authorizeRequests()
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')") // manager 이하는 권한이 ROLE_ADMIN 과 ROLE_MANAGER 만 입장가능
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") // admin 이하는 권한이 ROLE_ADMIN 만 입장가능
                .antMatchers("/", "/api/auth/**", "/auth/**", "/js/**", "/css/**", "/image/**").permitAll() // 인증필요없음
                .anyRequest().authenticated() // 이외 모든 요청은 인증 필요 (인증만 되면 입장가능)
            .and()
                .formLogin()
                .loginPage("/auth/loginForm")  // UserController의 GetMapping호출 (로그인 화면 호출 )
                .loginProcessingUrl("/auth/loginProc")  // 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인 처리 (그래서 Controller에서 처리하지 않음)
                .defaultSuccessUrl("/");   // 정상적으로 요청이 완료되면 루트로 이동
    }
}
