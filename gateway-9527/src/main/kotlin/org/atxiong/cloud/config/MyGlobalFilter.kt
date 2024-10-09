package org.atxiong.cloud.config

import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.core.Ordered
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

/**
 * 自定义全局 filter
 */
@Component
class MyGlobalFilter: GlobalFilter, Ordered {
    private val globalStartTimeField = "GLOBAL_START_TIME"

    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
        exchange.attributes[globalStartTimeField] = System.currentTimeMillis()

        return chain.filter(exchange).then(Mono.fromRunnable {
            with(exchange.request) {
                println("$uri 接口耗时: " +
                        "${System.currentTimeMillis() - exchange.attributes[globalStartTimeField] as Long} ms")
                println("============================================================")
            }

        })
    }

    override fun getOrder(): Int = 0
}