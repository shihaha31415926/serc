package com.serc.exception;


import com.serc.entity.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public R handler(RuntimeException runtimeException){
        log.error("运行时异常：***"+runtimeException.getMessage());
        return R.error(runtimeException.getMessage());
    }
}
