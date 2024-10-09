package org.atxiong.cloud.config

import com.alibaba.fastjson2.toJSONString
import org.atxiong.cloud.common.entity.HttpResult
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.filters
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.core.publisher.Mono

@Configuration
class GatewayFilterConfiguration {

    /**
     * api的方式配置 gateway 三大件 （route，predicate，filter）
     */
    @Bean
    fun routes(builder: RouteLocatorBuilder): RouteLocator = builder.routes {

//        route ("route2") {
//            uri("lb://cloud-payment-service")
//            path("/pay/**")
//
//            // 过滤器
//            filters {
//                // 添加前缀
////                prefixPath("/pay")
//                // 修改请求头，创建新请求头并用老请求头值填充
////                mapRequestHeader("Blue", "X-Request-Red")
//                // 移除json 响应体的字段
////                removeJsonAttributes("id", "name")
//
//
//                // 修改 请求体
////                modifyResponseBody (Any::class.java, String::class.java) { exchange, s ->
////                    println(s)
////                    Mono.just("你被我修改了")
////                }
//            }
//        }
    }
}