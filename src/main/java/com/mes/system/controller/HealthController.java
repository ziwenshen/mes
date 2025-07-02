package com.mes.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mes.system.utils.AjaxResult;

@RestController
public class HealthController {

    @GetMapping("/")
    public AjaxResult home() {
        return AjaxResult.success("MES系统API服务正常运行");
    }

    @GetMapping("/health")
    public AjaxResult health() {
        return AjaxResult.success("健康检查通过");
    }

    @GetMapping("/api/status")
    public AjaxResult apiStatus() {
        return AjaxResult.success("API服务正常");
    }
}