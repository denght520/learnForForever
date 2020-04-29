package com.learn.forever.common.entry;

import com.bluewhale.base.exception.BaseRuntimeException;
import com.bluewhale.dto.ResultDTO;
import com.bluewhale.util.TraceUtil;
import com.learn.forever.common.enums.IExceptionEnum;
import com.learn.forever.common.exception.BaseException;

import java.io.Serializable;

/**
 * @projectName: learnForForever
 * @className: ResultEntity
 * @author: denghaitao
 * @date: 2020/4/28
 * @version: 1.0
 * @E-mail: denghaitao@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */

public class ResultEntity<T> implements Serializable {
    public static final int SUCCESS_CODE = 1;
    private static final long serialVersionUID = 1L;
    private int code;
    private String msg;
    private T data;
    private boolean success;
    private String traceId;

    public static <T> ResultEntity<T> buildError(int code, String message) {
        ResultEntity<T> resultDO = new ResultEntity();
        resultDO.setSuccess(false);
        resultDO.setCode(code);
        resultDO.setMsg(message);
        return resultDO;
    }

    public static <T> ResultEntity<T> buildSuccess(T module) {
        ResultEntity<T> resultDO = new ResultEntity();
        resultDO.setSuccess(true);
        resultDO.setCode(1);
        resultDO.setData(module);
        resultDO.setTraceId(TraceUtil.getTraceId());
        return resultDO;
    }

    public static <T> ResultEntity<T> buildSuccess() {
        ResultEntity<T> resultDO = new ResultEntity();
        resultDO.setCode(1);
        resultDO.setSuccess(true);
        resultDO.setTraceId(TraceUtil.getTraceId());
        return resultDO;
    }

    public static <T> ResultEntity<T> convertResultDTO(ResultDTO<T> resultDTO) {
        return resultDTO.isSuccess() ? buildSuccess(resultDTO.getModule()) : buildError(resultDTO.getCode(), resultDTO.getMessage());
    }

    public static ResultEntity buildError(IExceptionEnum e) {
        return buildError(e.getCode(), e.getMessage());
    }

    public static ResultEntity buildError(BaseException e) {
        return buildError(e.getErrCode(), e.getErrMsg());
    }

    public static ResultEntity buildError(BaseRuntimeException e) {
        return buildError(e.getErrCode(), e.getErrMsg());
    }

    public static boolean isCallSuccess(com.bluewhale.response.entity.ResultEntity resultDO) {
        return resultDO != null && resultDO.isSuccess();
    }

    public static boolean isDataSuccess(com.bluewhale.response.entity.ResultEntity resultDO) {
        return resultDO != null && resultDO.isSuccess() && resultDO.getData() != null;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
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

    public ResultEntity() {
    }

    public ResultEntity(Integer code2, String message) {
        this.code = code2;
        this.msg = message;
    }

    public ResultEntity(Integer code2, String message, T data2) {
        this.code = code2;
        this.msg = message;
        this.data = data2;
    }
}
