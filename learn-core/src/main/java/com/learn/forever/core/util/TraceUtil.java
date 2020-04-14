package com.learn.forever.core.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

/**
 * @author : gejianhua
 * @date 2020/2/26 11:56
 */
@Slf4j
public class TraceUtil {

    private static final String TRACE_ID_KEY = "traceId";

    /**
     * get or generate trace id
     *
     * @return traceId
     */
    public static String getOrNewTraceId() {
        try {
            String traceId = MDC.get(TRACE_ID_KEY);
            if (StringUtils.isBlank(traceId)) {
                traceId = new ObjectId().toString();
            }
            return traceId;
        } catch (Exception e) {
            log.error("get or new trace id exception", e);
            return new ObjectId().toString();
        }
    }

    /**
     * get trace id from mdc
     * @return
     */
    public static String getTraceId() {
        try {
            return MDC.get(TRACE_ID_KEY);
        } catch (Exception e) {
            log.error("get trace id exception", e);
            return "NULL";
        }
    }

    /**
     * generate new trace id
     *
     * @return traceId
     */
    public static String newTraceId() {
        try {
            return new ObjectId().toString();
        } catch (Exception e) {
            log.error("new trace id exception", e);
            return "NULL";
        }
    }


    /**
     * put trace id
     *
     * @param traceId
     * @return traceId
     */
    public static void putTraceId(String traceId) {
        try {
            MDC.put(TRACE_ID_KEY, traceId);
        } catch (Exception e) {
            log.error("put trace id exception, traceId:{}", traceId, e);
        }

    }

    /**
     * generate new traceId and put.
     */
    public static String putNewTraceId() {
        try {
            String traceId = new ObjectId().toString();
            MDC.put(TRACE_ID_KEY, traceId);
            return traceId;
        } catch (Exception e) {
            log.error("put new trace id exception", e);
            return "NULL";
        }
    }

    /**
     * put traceId if absent
     *
     * @param traceId
     */
    public static String putTraceIdIfAbsent(String traceId) {
        try {
            if (StringUtils.isBlank(MDC.get(TRACE_ID_KEY))) {
                MDC.put(TRACE_ID_KEY, traceId);
                return traceId;
            } else {
                return MDC.get(TRACE_ID_KEY);
            }
        } catch (Exception e) {
            log.error("put trace id if absent exception, traceId:{}", traceId, e);
            return "NULL";
        }
    }

    /**
     * generate new traceId and put if absent.
     *
     * @return traceId
     */
    public static String putNewTraceIdIfAbsent() {
        try {
            String traceId = MDC.get(TRACE_ID_KEY);
            if (StringUtils.isBlank(traceId)) {
                traceId = new ObjectId().toString();
                MDC.put(TRACE_ID_KEY, traceId);
            }

            return traceId;
        } catch (Exception e) {
            log.error("put new trace id if absent exception", e);
            return "NULL";
        }
    }

    /**
     * remove traceId
     */
    public static void removeTraceId() {
        try {
            MDC.remove(TRACE_ID_KEY);
        } catch (Exception e) {
            log.error("remove trace id exception", e);
        }
    }

    /**
     * run with trace 一般用于新线程执行方法
     *
     * @param runnable
     */
    public static void run(Runnable runnable) {
        try {
            putNewTraceIdIfAbsent();
            runnable.run();
        } finally {
            removeTraceId();
        }
    }
}































