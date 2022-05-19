package com.example.newblog.config.auth;

import com.example.newblog.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 수행하고 완료되면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션 저장소에 저장하게 된다.
// 최종적으로는 내가 정의한 PrincipalDetail 가 저장되게 된다.
@Data
public class PrincipalDetail implements UserDetails {

    private User user;  // 객체를 포함하는 경우 이를 콤포지션 이라고 한다.

    public PrincipalDetail(User user){  // user객체에 데이터를 넣기 위한 생성자, principalDetailService에서 넣어준다.
        this.user = user;
    }

    // 계정의 권한을 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();
       /** collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole().toString(); // 반드시 ROLE_가 붙어야 하지만 enum 설정시 이미 붙여서 정의했기 때문에 붙이는 작업 필요 없음
            }
        });*/

        // 함수안에 메서드가 하나밖에 없으면 람다식으로 표현 가능하다.
        collection.add(()->{
            return user.getRole().toString();});

        return collection;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 계정 완료 여부 리턴 (true:만료안됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겨있는지 여부 (true:안잠김)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호 만료 여부 (true:만료안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 활성화(사용가능) 여부 (true:활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
