package com.example.newblog.handler;

import com.example.newblog.dto.DtoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    // IllegalArgumentException 이 발생했을때 메시지 처리
    /*@ExceptionHandler(value = IllegalArgumentException.class)
    public String handleAIllegalArgumentException(IllegalArgumentException e){
        return "<h1>"+e.getMessage()+"</h1>";
    }*/

    // DtoResponse를 이용하기 위해 수정
    @ExceptionHandler(value = IllegalArgumentException.class)
    public DtoResponse<String> handleAIllegalArgumentException(IllegalArgumentException e){
        return new DtoResponse<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    // 모든 Exception 처리
/*    @ExceptionHandler(value = Exception.class)
    public String handleException(IllegalArgumentException e){
        return "<h1>"+e.getMessage()+"</h1>";
    }
    */




}
