package org.atxiong.cloud.config

import feign.Logger
import feign.Retryer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FeignConfig {

    @Bean
    fun myRetryer(): Retryer {
        // 设置feign 的 每次间隔重试时间1000ms, 最大间隔时间2s, 最多重试1次(1+2)
        return Retryer.Default(1000, 2,2)
    }

    @Bean
    fun feignLoggerLevel(): Logger.Level = Logger.Level.FULL
}