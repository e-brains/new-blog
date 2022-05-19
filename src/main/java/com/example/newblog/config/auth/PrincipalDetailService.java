package com.example.newblog.config.auth;

import com.example.newblog.model.User;
import com.example.newblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // 빈 등록
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // 스프링이 로그인 요청을 가로챌 때 username, password 변수 2개를 가로채는데
    // password 부분 처리는 스프링이 알아서 비교처리 함
    // 우리는 해당 username이 DB에 있는지 여기서 Override를 통해서 확인하고 user데이터를 PrincipalDetail에 넘겨서
    // PrincipalDetail의 user에 우리 user정보가 들어가게 한다.
    // 여기서 우리가 가로채서(Override해서) 우리 유저값을 넣어 주지 않으면 default username/password가 들어감
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User principal = userRepository.findByUsername(username).orElseThrow(() -> {
            return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다."+username);
        });
        return new PrincipalDetail(principal);  // 시큐리티의 고유세션에 UserDetails타입으로 PrincipalDetail로 감싸져서 user정보가 생성된다.

    }
}
