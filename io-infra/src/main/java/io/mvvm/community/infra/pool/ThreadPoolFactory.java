package io.mvvm.community.infra.pool;


import io.mvvm.community.infra.SpringContextHolder;

import java.util.concurrent.Future;

/**
 * @author: Pan
 **/
public class ThreadPoolFactory {

    public static final ThreadPoolExecutorMdcWrapper THREAD_POOL = SpringContextHolder.getBean(ThreadPoolExecutorMdcWrapper.class);

    public static void run(Runnable runnable) {
        THREAD_POOL.execute(runnable);
    }

    public static Future<?> submit(Runnable runnable) {
        return THREAD_POOL.submit(runnable);
    }

}