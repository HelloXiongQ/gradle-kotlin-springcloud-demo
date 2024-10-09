package org.atxiong.cloud.config

import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.ObjectProvider
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.result.view.ViewResolver

/**
 * Gateway 网关集成 sentinel 配置
 */
@Configuration
class GatewayConfiguration(
    viewResolversProvider: ObjectProvider<List<ViewResolver>>,
    private val serverCodecConfigurer: ServerCodecConfigurer
) {
    private val viewResolvers =
        viewResolversProvider.getIfAvailable { emptyList() }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    fun sentinelGatewayBlockExceptionHandler(): SentinelGatewayBlockExceptionHandler {
        return SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer)
    }

    @Bean
    @Order(-1)
    fun sentinelGatewayFilter(): GlobalFilter {
        return SentinelGatewayFilter()
    }

    @PostConstruct
    fun doInit() {
        initGatewayRules()
    }

    private fun initGatewayRules() {
        val rules: MutableSet<GatewayFlowRule> = HashSet()

        // 流控规则
        rules.add (
            GatewayFlowRule("route1")
                .setCount(2.0)
                .setIntervalSec(1)
        )

        GatewayRuleManager.loadRules(rules)

        // gateway 流控处理器
        GatewayCallbackManager.setBlockHandler { exchange, t ->
            val map = mutableMapOf<String, String>()

            map["errorCode"] = HttpStatus.TOO_MANY_REQUESTS.reasonPhrase
            map["errorMessage"] = "请求太繁忙，触发限流"

            return@setBlockHandler ServerResponse
                .status(HttpStatus.TOO_MANY_REQUESTS)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(map))
        }
    }
}