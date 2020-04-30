package com.learn.forever.expection;

import com.bluewhale.base.enums.IExceptionEnum;
import lombok.Getter;

/**
 * 校验异常类
 * Created by angus on 15/9/28.
 */
public class BizException extends RuntimeException {

    private static final long serialVersionUID = -2096728164166219382L;

    @Getter
    private Integer errCode;
    @Getter
    private String errMsg;
    @Getter
    private final IExceptionEnum exceptionEnum;


    public BizException(IExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.errCode = exceptionEnum.getCode();
        this.errMsg = exceptionEnum.getMessage();
        this.exceptionEnum = exceptionEnum;
    }

    public BizException(IExceptionEnum exceptionEnum, String errMsg) {
        super(errMsg);
        this.errCode = exceptionEnum.getCode();
        this.errMsg = errMsg;
        this.exceptionEnum = exceptionEnum;
    }

}
