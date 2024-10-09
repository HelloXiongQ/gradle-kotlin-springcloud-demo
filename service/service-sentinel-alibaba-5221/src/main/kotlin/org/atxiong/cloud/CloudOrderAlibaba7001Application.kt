package org.atxiong.cloud

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableDiscoveryClient
class CloudSentinelAlibaba5221Application

fun main(args: Array<String>) {
    runApplication<CloudSentinelAlibaba5221Application>(*args)
}
