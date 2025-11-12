package com.briup.exception;

import com.briup.response.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
//@ControllerAdvice + @ResponseBody
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        e.printStackTrace();
        Result<String> result = null;
        if (e instanceof ServiceException) {
            result = Result.error(((ServiceException) e).getResultCode());
        } else {
            result = Result.error(500, "服务器意外错误：" + e.getMessage());
        }
        return result;
    }
}
