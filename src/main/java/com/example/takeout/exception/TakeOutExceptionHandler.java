package com.example.takeout.exception;

import com.example.takeout.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理
 * @author xiaoning
 * @date 2022/07/04
 */
@Slf4j
@RestControllerAdvice
public class TakeOutExceptionHandler {

    /**
     * 处理自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(TakeOutException.class)
    public Result<?> handlerTakeOutException(TakeOutException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }

}
