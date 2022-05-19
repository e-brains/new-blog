package com.example.newblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    /**
     * 회원가입 화면
     */
    @GetMapping("/auth/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    /**
     * 로그인 화면
     */
    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    /**
     * 회원수정 화면
     */
    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }

    @GetMapping("/user")
    public @ResponseBody String user(){
        return "user test 페이지";
    }

    @GetMapping("/manager")
    public @ResponseBody String manager(){
        return "manager test 페이지";
    }

    @GetMapping("/admin")
    public @ResponseBody String admin(){
        return "admin test 페이지";
    }
}
