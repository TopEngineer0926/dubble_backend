package com.mogree.dubble.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
import java.util.concurrent.Executor


@Configuration
@EnableAsync
@EnableScheduling
class SchedulerConfig {

    @Bean(name = ["taskExecutor"])
    fun getAsyncExecutor(): Executor? {
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = 3
        executor.maxPoolSize = 3
        executor.setQueueCapacity(100)
        executor.setThreadNamePrefix("customer-Executor-")
        executor.initialize()
        return executor
    }

    @Bean
    fun threadPoolTaskScheduler(): ThreadPoolTaskScheduler? {
        val threadPoolTaskScheduler = ThreadPoolTaskScheduler()
        threadPoolTaskScheduler.poolSize = 3
        return threadPoolTaskScheduler
    }


}