package com.niuma.easyexceldemo.exception;


import com.niuma.easyexceldemo.common.BaseResponse;
import com.niuma.easyexceldemo.common.ErrorCode;
import com.niuma.easyexceldemo.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * 利用 @RestControllerAdvice 和 @ExceptionHandler 注解配合，用于全局处理控制器里的异常
 *
 * @author niumazlb
 * @create 2022-11-19 21:53
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("businessException: " + e.getMessage(), e);
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException: " + e.getMessage(), e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, e.getMessage());
    }


}
