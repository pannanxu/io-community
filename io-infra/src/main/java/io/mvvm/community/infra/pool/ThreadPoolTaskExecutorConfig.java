package io.mvvm.community.infra.pool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置
 */
@Configuration
public class ThreadPoolTaskExecutorConfig {

    //最大可用的CPU核数
    public static final int PROCESSORS = Runtime.getRuntime().availableProcessors();

    @Bean
    public ThreadPoolExecutorMdcWrapper getExecutor() {
        ThreadPoolExecutorMdcWrapper executor = new ThreadPoolExecutorMdcWrapper();
        executor.setCorePoolSize(PROCESSORS * 2);
        executor.setMaxPoolSize(PROCESSORS * 4);
        executor.setQueueCapacity(50);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("App-Task");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        executor.initialize();
        return executor;
    }
}