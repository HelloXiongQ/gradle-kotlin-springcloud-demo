package org.atxiong.cloud.config

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate
import org.springframework.cloud.gateway.support.ShortcutConfigurable.ShortcutType
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated
import org.springframework.web.server.ServerWebExchange
import java.util.function.Predicate

/**
 * gateway 通过类名称匹配，来调用对应的predicate My RoutePredicateFactory
 *
 * 判断 请求头是否携带了 userType，用于过滤用户等级
 */
@Component
class MyRoutePredicateFactory: AbstractRoutePredicateFactory<MyRoutePredicateFactory.Config>(Config::class.java) {

    private val validList = listOf("gold", "sliver", "copper")

    @Validated
    data class Config(
        var patterns: List<String> = mutableListOf(),
    ) {
        fun setPatterns(patterns: List<String>): Config {
            this.patterns = patterns
            return this
        }
    }

    /**
     * 用来告诉 gateway 参数获取填充顺序
     */
    override fun shortcutFieldOrder(): List<String> {
        return listOf("patterns")
    }

    /**
     * 参数策略
     */
    override fun shortcutType(): ShortcutType {
        return ShortcutType.GATHER_LIST
    }


    /**
     * 参数校验
     */
    override fun apply(config: Config?): Predicate<ServerWebExchange> {
        return GatewayPredicate {

            // 判断路由条件是否符合正常值
            if (config!!.patterns.isEmpty()) throw Exception("The patterns param don't empty")
            config.patterns.forEach {
               if (it !in validList) throw Exception("The patterns param number error")
            }

            // 判断 split
            val userTypeParams = it.request.headers["userType"]?.get(0)?.split("-")
                ?: throw Exception("split param error")

            // 判断是否 满足条件
            return@GatewayPredicate config.patterns.count { configItem ->
                return@count userTypeParams.contains(configItem)
            } == config.patterns.size

        }
    }
}
