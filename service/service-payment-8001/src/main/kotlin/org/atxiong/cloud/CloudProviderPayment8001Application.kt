package org.atxiong.cloud

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@MapperScan("org.atxiong.cloud.payment.mapper")
@EnableDiscoveryClient   // 开启consul服务发现
class CloudProviderPayment8001Application

fun main(args: Array<String>) {
    runApplication<CloudProviderPayment8001Application> (*args)
}