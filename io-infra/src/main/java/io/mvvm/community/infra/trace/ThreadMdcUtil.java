package io.mvvm.community.infra.trace;

import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author: Pan
 **/
public class ThreadMdcUtil {

    public static void setTraceIdIfAbsent() {
        if (MDC.get(TraceIdUtil.TRACE_ID) == null) {
            MDC.put(TraceIdUtil.TRACE_ID, TraceIdUtil.generateTraceId());
        }
    }

    public static <T> Callable<T> wrap(final Callable<T> callable, final Map<String, String> context) {
        return () -> {
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            setTraceIdIfAbsent();
            try {
                return callable.call();
            } finally {
                MDC.clear();
            }
        };
    }

    public static Runnable wrap(final Runnable runnable, final Map<String, String> context) {
        return () -> {
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            //设置traceId
            setTraceIdIfAbsent();
            try {
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }
}