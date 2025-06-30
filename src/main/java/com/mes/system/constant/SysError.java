package com.mes.system.constant;

public enum SysError {

    /**
     * 系统通用的错误代码描述
     */
    NOT_FOUND(404, "请求资源不存在!"),
    ERROR(500, "系统异常"),
    MISSING_PARAM(100, "缺少参数!"),
    PARAM_ERROR(101, "参数错误!"),
    DATA_REPEAT(102, "数据重复!"),
    // 主要用于登录用户判断
    UNAUTHORIZED(103, "未登录!"),
    // 主要用于系统间调用判断
    UNIDENTIFIED(1031, "不明身份!"),
    SUCCESS(200, "操作成功"),
    RESOURCE_NOT_OWNER(104, "未拥有该资源"),
    REMOTE_ERROR(105, "远程服务异常!"),
    ILLEGAL_REQUEST(107, "非法请求"),
    ILLEGAL_INSTRUCTION(603, "非法指令");

    private int code;
    private String message;

    SysError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据code获得对应的枚举
     * 
     * @author yanwei
     * @date 14/4/2024 上午11:57
     */
    public static SysError get(int code) {
        for (SysError error : SysError.values()) {
            if (error.getCode() == code) {
                return error;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        if (message == null) {
            message = "";
        }
        this.message = message;
    }
}
