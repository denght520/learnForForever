package com.learn.forever.common.exception;


import com.bluewhale.base.enums.BaseExceptionEnum;
import com.learn.forever.common.enums.IExceptionEnum;

/**
 * @projectName: learnForForever
 * @className: BaseException
 * @author: denghaitao
 * @date: 2020/4/28
 * @version: 1.0
 * @E-mail: denghaitao@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
public class BaseException extends Exception {
    private static final long serialVersionUID = 1L;
    private String errMsg;
    private Integer errCode;
    private final IExceptionEnum exceptionEnum;

    public BaseException(IExceptionEnum exceptionEnum, String userErrorMsg) {
        super(exceptionEnum.getMessage());
        this.errMsg = userErrorMsg;
        this.errCode = exceptionEnum.getCode();
        this.exceptionEnum = exceptionEnum;
    }

    public BaseException(IExceptionEnum exceptionEnum, String userErrorMsg, Throwable cause) {
        super(exceptionEnum.getMessage(), cause);
        this.errMsg = userErrorMsg;
        this.errCode = exceptionEnum.getCode();
        this.exceptionEnum = exceptionEnum;
    }

    public BaseException(IExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.errMsg = exceptionEnum.getMessage();
        this.errCode = exceptionEnum.getCode();
        this.exceptionEnum = exceptionEnum;
    }

    public IExceptionEnum getErrorCode() {
        return this.exceptionEnum;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public String getSysErrMsg() {
        return this.getMessage();
    }

    public Integer getErrCode() {
        return this.errCode;
    }

    public static void main(String[] args) {
        try {
            throw new com.bluewhale.base.exception.BaseException(BaseExceptionEnum.BW_SYS_1000001);
        } catch (Exception var2) {
            System.err.println(var2.getMessage());
        }
    }
}
