package com.mes.system.exception;

import com.mes.system.constant.SysError;

public class ApiResultException extends RuntimeException {

    private int code;

    public ApiResultException(SysError error) {
        super(error.getMessage());
        this.code = error.getCode();
    }

    public ApiResultException(String message) {
        super(message);
    }

    public ApiResultException(SysError error, String message) {
        super(message);
        error = error == null ? SysError.ERROR : error;
        this.code = error.getCode();
    }

    public int getCode() {
        return code;
    }
}
