package com.learn.forever.core.template;

import com.alibaba.fastjson.JSON;
import com.bluewhale.base.exception.BaseRuntimeException;
import com.learn.forever.common.entry.ResultDTO;
import com.learn.forever.common.entry.ResultEntity;
import com.learn.forever.common.enums.BaseExceptionEnum;
import com.learn.forever.common.exception.LearnException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author : Guava
 * @version 1.0
 * @projectName：bw-scm
 * @className：AbstractResultTemplate
 * @date 2019-11-04 19:37
 * @E-mail:gongdexing@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
@Slf4j
public abstract class AbstractResultTemplate<T> {

    public ResultDTO<T> execute(Object... params) {
        long start = System.currentTimeMillis();
        boolean result = true;
        try {
            this.checkParam();
            return ResultDTO.buildSuccess(bizExecute());
        } catch (LearnException baseException) {
            log.error("level0@scmException params {}", getParam(params), baseException);
            return ResultDTO.buildError(baseException.getErrCode(), StringUtils.isNotEmpty(baseException.getErrMsg()) ? baseException.getErrMsg() : baseException.getMessage());
        } catch (Exception e) {
            log.error("level0@execute:", e);
            return ResultDTO.buildError(BaseExceptionEnum.BW_CLIENT_1000005);
        } finally {
            int threadLength = 2;
            if (Thread.currentThread().getStackTrace().length < threadLength) {
                log.info("info@execute,success={},cost:{},param:{}", result, System.currentTimeMillis() - start, getParam(params));
            } else {
                log.info("info@{}.{},success={},cost:{},param:{}", Thread.currentThread().getStackTrace()[2].getClassName(), Thread.currentThread().getStackTrace()[2].getMethodName(), result,
                        System.currentTimeMillis() - start, getParam(params));
            }
        }
    }

    public ResultEntity<T> executeApi(Object... params) {
        long start = System.currentTimeMillis();
        boolean result = true;
        try {
            this.checkParam();
            return ResultEntity.buildSuccess(bizExecute());
        } catch (LearnException baseException) {
            log.error("level0@scmException params {}", getParam(params), baseException);
            return ResultEntity.buildError(baseException.getErrCode(), StringUtils.isNotEmpty(baseException.getErrMsg()) ? baseException.getErrMsg() : baseException.getMessage());
        } catch (BaseRuntimeException runtimeException) {
            return ResultEntity.buildError(runtimeException.getErrCode(), runtimeException.getMessage());
        } catch (Exception e) {
            log.error("level0@execute:", e);
            return ResultEntity.buildError(BaseExceptionEnum.BW_CLIENT_1000005);
        } finally {
            int threadLength = 2;
            if (Thread.currentThread().getStackTrace().length < threadLength) {
                log.info("info@execute,success={},cost:{},param:{}", result, System.currentTimeMillis() - start, getParam(params));
            } else {
                log.info("info@{}.{},success={},cost:{},param:{}", Thread.currentThread().getStackTrace()[2].getClassName(), Thread.currentThread().getStackTrace()[2].getMethodName(), result,
                        System.currentTimeMillis() - start, getParam(params));
            }
        }
    }

    public T executeThrowException(Object... params) throws LearnException {
        long start = System.currentTimeMillis();
        boolean result = true;
        try {
            this.checkParam();
            return bizExecute();
        } catch (LearnException baseException) {
            throw baseException;
        } catch (Exception e) {
            log.error("level0@execute: params {}", getParam(params), e);
            throw new LearnException(BaseExceptionEnum.BW_CLIENT_1000005);
        } finally {
            int threadLength = 2;
            if (Thread.currentThread().getStackTrace().length < threadLength) {
                log.info("info@execute,success={},cost:{}, param:{}", result, System.currentTimeMillis() - start, getParam(params));
            } else {
                log.info("info@{}.{},success={},cost:{},param:{}", Thread.currentThread().getStackTrace()[2].getClassName(), Thread.currentThread().getStackTrace()[2].getMethodName(), result,
                        System.currentTimeMillis() - start, getParam(params));
            }
        }
    }

    /**
     * 强制参数校验
     */
    public abstract void checkParam() throws LearnException;

    /**
     * 业务处理
     */
    public abstract T bizExecute() throws Exception;

    private String getParam(Object... params) {
        StringBuilder sb = new StringBuilder();
        if (params == null || params.length == 0) {
            return sb.append("null").toString();
        }
        int length = params.length;
        for (int i = 0; i < length; i++) {
            sb.append(JSON.toJSONString(params[i]));
            if (i < length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
