package com.learn.forever.common.entry;

import com.bluewhale.util.TraceUtil;
import com.learn.forever.common.enums.IExceptionEnum;

import java.io.Serializable;

/**
 * @projectName: learnForForever
 * @className: ResultDTO
 * @author: denghaitao
 * @date: 2020/4/28
 * @version: 1.0
 * @E-mail: denghaitao@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
public class ResultDTO<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int code;
    private String message;
    private T module;
    private boolean success;
    private String traceId;

    public static <T> ResultDTO<T> buildError(IExceptionEnum exceptionEnum) {
        ResultDTO<T> resultDO = new ResultDTO();
        resultDO.setSuccess(false);
        resultDO.setCode(exceptionEnum.getCode());
        resultDO.setMessage(exceptionEnum.getMessage());
        resultDO.setTraceId(TraceUtil.getTraceId());
        return resultDO;
    }

    public static <T> ResultDTO<T> buildError(int code, String message) {
        ResultDTO<T> resultDO = new ResultDTO();
        resultDO.setSuccess(false);
        resultDO.setCode(code);
        resultDO.setMessage(message);
        resultDO.setTraceId(TraceUtil.getTraceId());
        return resultDO;
    }

    public static <T> ResultDTO<T> buildSuccess(T module) {
        ResultDTO<T> resultDO = new ResultDTO();
        resultDO.setSuccess(true);
        resultDO.setModule(module);
        resultDO.setCode(1);
        resultDO.setTraceId(TraceUtil.getTraceId());
        return resultDO;
    }

    public static <T> ResultDTO<T> buildSuccess() {
        ResultDTO<T> resultDO = new ResultDTO();
        resultDO.setSuccess(true);
        resultDO.setCode(1);
        resultDO.setTraceId(TraceUtil.getTraceId());
        return resultDO;
    }

    public static boolean isCallSuccess(ResultDTO resultDO) {
        return resultDO != null && resultDO.isSuccess();
    }

    public static boolean isDataSuccess(ResultDTO resultDO) {
        return resultDO != null && resultDO.isSuccess() && resultDO.getModule() != null;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getModule() {
        return this.module;
    }

    public void setModule(T module) {
        this.module = module;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTraceId() {
        return this.traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public ResultDTO() {
    }
}

