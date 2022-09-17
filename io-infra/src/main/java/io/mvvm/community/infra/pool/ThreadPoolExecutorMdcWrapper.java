package io.mvvm.community.infra.pool;

import io.mvvm.community.infra.trace.ThreadMdcUtil;
import org.slf4j.MDC;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * 解决 日志链路的id在多线程中丢失
 */
@SuppressWarnings("all")
public class ThreadPoolExecutorMdcWrapper extends ThreadPoolTaskExecutor {
    private static final long serialVersionUID = 3940722618853093830L;

    @Override
    public void execute(Runnable task) {
        super.execute(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return super.submit(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
    }
}