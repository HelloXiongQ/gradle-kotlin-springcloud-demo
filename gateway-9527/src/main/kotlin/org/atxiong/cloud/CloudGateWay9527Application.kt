package org.atxiong.cloud

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient   // 开启consul服务发现
class CloudGateWay9527Application

fun main(args: Array<String>) {
    runApplication<CloudGateWay9527Application> (*args)
}