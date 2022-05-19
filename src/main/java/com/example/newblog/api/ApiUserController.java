package com.example.newblog.api;

import com.example.newblog.dto.DtoResponse;
import com.example.newblog.model.User;
import com.example.newblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ApiUserController {

    @Autowired
    public UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**** 회원가입 ****/
    @PostMapping("/api/auth/joinProc")
    public DtoResponse<String> save(@RequestBody User user) {

        System.out.println("joinProc => User.getUsername() : " + user.getUsername());
        userService.회원가입(user);

        return new DtoResponse<String>(HttpStatus.OK.value(), "success");
    }

    /**** 로그인 처리  ****/
    /** "/auth/loginProc" 로 호출되는 uri는 SecurityConfig의 .loginProcessingUrl("/auth/loginProc")에서
     * 가로채기 해서 처리되기 때문에 여기서 별도로 만들지 않아도 된다. 그래서 loginForm에서 ajax를 사용하지 않음*/

    /**** 회원정보 수정 ****/
    @PutMapping("/api/user/updateProc")
    public DtoResponse<String> updateProc(@RequestBody User user){

        System.out.println("updateProc => User.getUsername() : " + user.getUsername());

        userService.회원정보수정(user);

        // 여기서는 트랜잭션이 종료되었기 때문에 DB에 값은 변경이 되었겠지만 세션값은 변경되지 않은 상태이다.
        // 그래서 UI에는 변경되지 않은 기존 세션을 읽어서 개인정보를 보여주기 때문에
        // 여기서 우리가 직접 센션값을 변경해 줘야 한다.
        // 세션생성 시 시큐리티 컨텍스트에 Authentication 객체를 직접 만들어서 넣는다.
        // 반드시 principal.getAuthorities()로 권한을 가져와서 넣어준다.
        // 현재는 직접 세션에 넣는 부분이 막혀서 안됨
       /* Authentication authentication = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
        SecurityContext securityContext = SecurityContextHolder.getContext(); // 홀더에서 시큐리티 컨텍스트를 가져온다.
        securityContext.setAuthentication(authentication);   // 시큐리티 컨텍스트에 authenticaion을 생성한다.
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext); // authentication이 담겨진 securityContext를 세션에 담는다.*/

        // 세션을 새로 만드는 부분 다른 방법
        // Authentication을 세션에 생성하는 처리를 위해 SecurityConfig에서 먼저 authenticationManagerBean()을 override한다.
        // 상단에서 AuthenticationManager를 Autowired한다.
        // 토큰을 만들어서 매니저에게 넘겨서 authentication이 생성되도록 하고 이를 시큐리티 컨텍스트 안에 컨텍스트에 authentication을 넣으면 된다.
        // 이렇게 하면 직접 세션에 넣어 줄 필요없이 스프링이 알아서 생성한다. (자동 세션 생성 전에 여기서 가로채서 변경된 유저 정보로 생성하도록 한다.)
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);


        return new DtoResponse<>(HttpStatus.OK.value(), "success");
    }


}
