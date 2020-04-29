package com.learn.forever.common.enums;

public enum BaseExceptionEnum implements IExceptionEnum {
    BW_SYS_1000001(1000001, "系统异常"),
    BW_SYS_1000002(1000002, "请求参数不完整"),
    BW_SYS_1000003(1000003, "请求参数错误"),
    BW_SYS_1000004(1000004, "登录失效"),
    BW_CLIENT_1000005(1000005, "前面排队有点长，等会再试叭!");

    private Integer code;
    private String message;

    private BaseExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
