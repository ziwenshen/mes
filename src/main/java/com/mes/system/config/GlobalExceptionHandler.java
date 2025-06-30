package com.mes.system.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mes.system.utils.AjaxResult;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 处理所有自定义异常并返回HTTP状态码
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<AjaxResult> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity
                .status(400)
                .body(AjaxResult.error(400, ex.getMessage()));
    }

    // 处理所有未捕获的异常
    @ExceptionHandler(Exception.class)
    public AjaxResult handleException(Exception ex) {
        // 生产环境建议只返回简要信息
        return AjaxResult.error("服务器内部错误: " + ex.getMessage());
    }

}