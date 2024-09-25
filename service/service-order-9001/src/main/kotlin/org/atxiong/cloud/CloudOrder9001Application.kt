package org.atxiong.cloud

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope // 开启动态刷新
@EnableFeignClients
class CloudOrder9001Application

fun main(args: Array<String>) {
    runApplication<CloudOrder9001Application>(*args)
}
