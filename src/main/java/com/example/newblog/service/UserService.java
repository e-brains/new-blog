package com.example.newblog.service;

import com.example.newblog.model.RoleType;
import com.example.newblog.model.User;
import com.example.newblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌 : Ioc 해줌
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Transactional
    public void 회원가입(User user) {

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(RoleType.ROLE_USER);
        userRepository.save(user);
    }

    @Transactional
    public void 회원정보수정(User requestUser){

        // 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고 영속화된 User 오브젝트를 수정하는 것이 좋다.
        // select를 해서 User 오브젝트를 DB로 부터 가져오는 이유는 영속화를 하기 위해서임
        // 영속화된 오브젝트를 변경하면 트랜잭션이 끝날 때 더티 체킹이 일어나 실제 DB에 flush한다.
        User user = userRepository.findById(requestUser.getId()).orElseThrow(()->{
            return new IllegalArgumentException("회원정보를 찾을 수 없습니다.");
        });

        user.setPassword(encoder.encode(requestUser.getPassword()));
        user.setEmail(requestUser.getEmail());

        userRepository.save(user); // 더티 체킹에 의해 자동으로 update되기 때문에 없어도 됨


    }


}
