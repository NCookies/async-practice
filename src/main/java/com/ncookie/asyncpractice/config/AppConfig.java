package com.ncookie.asyncpractice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AppConfig {

    // ThreadPool을 정리했을 때, 의도치 않게 Thread Pool들이 정리가 되지 않을 수도 있다.
    // 이러한 상황을 방지하고자 Destroy Method라는 값을 선언해줄 수 있다.
    // 이 옵션을 올바르게 활용하면 애플리케이션 리소스 관리와 정리 작업이 보다 안전하게 이루어진다.
    @Bean(name = "defaultTaskExecutor", destroyMethod = "shutdown")
    public ThreadPoolTaskExecutor defaultTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(200);
        executor.setMaxPoolSize(300);

        return executor;
    }

    @Bean(name = "messagingTaskExecutor", destroyMethod = "shutdown")
    public ThreadPoolTaskExecutor messagingTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(200);
        executor.setMaxPoolSize(300);

        return executor;
    }

}
