package org.atxiong.cloud

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("org.atxiong.cloud.mapper")
class CloudOrderSeata2001Application

fun main(args: Array<String>) {
    runApplication<CloudOrderSeata2001Application>(*args)
}
